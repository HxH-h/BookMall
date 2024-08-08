package com.bookmall.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.bookmall.Constant.ExceptionMsg;
import com.bookmall.Constant.OrderConstant;
import com.bookmall.Constant.RedisConstant;
import com.bookmall.Controller.ControllerPojo.GoodsDTO;
import com.bookmall.Controller.ControllerPojo.OrderDTO;
import com.bookmall.Controller.ControllerPojo.OrderVO;
import com.bookmall.Controller.ControllerPojo.PayDTO;
import com.bookmall.Controller.UserInfoThread;
import com.bookmall.Controller.WebSocketContrller;
import com.bookmall.CusException.*;
import com.bookmall.Dao.Mapper.BookMapper;
import com.bookmall.Dao.Mapper.OrderMapper;
import com.bookmall.Dao.Mapper.UserMapper;
import com.bookmall.Dao.Pojo.Address;
import com.bookmall.Dao.Pojo.Book;
import com.bookmall.Dao.Pojo.Order;
import com.bookmall.Dao.Pojo.OrderDetail;
import com.bookmall.Service.ShopService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    BookMapper bookMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    WebSocketContrller webSocketContrller;


    //添加购物车
    @Override
    public void addGoods(GoodsDTO goodsDTO) throws BookNotFoundException, BookShortageException, NumberIllegalException {
        String openid = UserInfoThread.getInfo();


        //判断购物车是否存在
        Set keys = redisTemplate.opsForHash().keys(RedisConstant.SHOPCART + openid);
        if (keys.isEmpty()){
            Book book = bookMapper.selectByuuid(goodsDTO.getUuid());

            if (book == null){
                throw new BookNotFoundException(ExceptionMsg.BOOK_NOTFOUND);
            }

            if (goodsDTO.getCnt() < 0){
                throw new NumberIllegalException("数值非法");
            }

            //粗略判断库存  不考虑超卖问题  缺货则联系购买者取消订单
            if (goodsDTO.getCnt() > book.getCnt()){
                throw new BookShortageException(ExceptionMsg.BOOK_SHORTAGE);
            }
            //设置购买数量
            book.setCnt(goodsDTO.getCnt());
            book.setDetail(null);
            redisTemplate.opsForHash().put(RedisConstant.SHOPCART+openid,goodsDTO.getUuid(),JSONObject.toJSONString(book));
        }else {
            //判断图书是否已经存在
            if (redisTemplate.opsForHash().hasKey(RedisConstant.SHOPCART+openid,goodsDTO.getUuid())){
                // 存在则数量 +cnt
                String s = (String) redisTemplate.opsForHash().get(RedisConstant.SHOPCART + openid, goodsDTO.getUuid());
                Book book = JSONObject.parseObject(s, Book.class);

                int cnt = book.getCnt() + goodsDTO.getCnt();

                if (cnt > bookMapper.getCnt(goodsDTO.getUuid())){
                    throw new BookShortageException(ExceptionMsg.BOOK_SHORTAGE);
                }

                if (cnt <= 0){
                    redisTemplate.opsForHash().delete(RedisConstant.SHOPCART + openid, goodsDTO.getUuid());
                }else {
                    book.setCnt(cnt);
                    book.setDetail(null);
                    redisTemplate.opsForHash().put(RedisConstant.SHOPCART+openid,goodsDTO.getUuid(),JSONObject.toJSONString(book));
                }

            }else {
                // 不存在则 添加信息
                Book book = bookMapper.selectByuuid(goodsDTO.getUuid());

                if (goodsDTO.getCnt() < 0){
                    throw new NumberIllegalException("数值非法");
                }

                if (goodsDTO.getCnt() > book.getCnt()){
                    throw new BookShortageException(ExceptionMsg.BOOK_SHORTAGE);
                }

                //设置购买数量
                book.setCnt(goodsDTO.getCnt());
                book.setDetail(null);
                redisTemplate.opsForHash().put(RedisConstant.SHOPCART+openid,goodsDTO.getUuid(),JSONObject.toJSONString(book));

            }

        }

    }


    @Override
    public void clearShopCart() {
        String openid = UserInfoThread.getInfo();
        Set keys = redisTemplate.opsForHash().keys(RedisConstant.SHOPCART + openid);
        if (keys.isEmpty()){
            return ;
        }
        redisTemplate.delete(RedisConstant.SHOPCART +openid);
    }

    @Override
    public List<Book> showCart() {
        String openid = UserInfoThread.getInfo();
        Set keys = redisTemplate.opsForHash().keys(RedisConstant.SHOPCART + openid);
        if (keys.isEmpty()){
            return null;
        }
        ArrayList<Book> books = new ArrayList<>();

        Map<String,String> entries = redisTemplate.opsForHash().entries(RedisConstant.SHOPCART + openid);
        for (Map.Entry <String, String>  entry : entries.entrySet()){
            Book book = JSONObject.parseObject(entry.getValue(), Book.class);
            books.add(book);
        }
        return books;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderVO order(OrderDTO orderDTO) throws ShopCartEmptyException, AddressNotFoundException, BookShortageException {
        String openid = UserInfoThread.getInfo();
        String uuid = userMapper.getUUid(openid);
        //判断异常

        Set keys = redisTemplate.opsForHash().keys(RedisConstant.SHOPCART + openid);
        if (keys.isEmpty()){

            throw new ShopCartEmptyException(ExceptionMsg.SHOPCART_EMPTY);
        }
        Address singleAddress = userMapper.getSingleAddress(uuid, orderDTO.getAddressid());
        if (singleAddress == null){

            throw new AddressNotFoundException(ExceptionMsg.ADDRESS_NOTFOUND);
        }
        //获取订单id
        Long time = System.currentTimeMillis();
        Integer cnt = (Integer) redisTemplate.opsForValue().get(RedisConstant.ORDER_COUNT);
        String id = String.valueOf(time << 32 | cnt);
        // 获取购物车数据
        List<OrderDetail> orderDetails = new ArrayList<>();
        List<Book> books = showCart();
        float amount = 0;
        for (Book book : books){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setAmount(book.getPrice());
            orderDetail.setBookid(book.getUuid());
            orderDetail.setCnt(book.getCnt());
            orderDetail.setOrderid(id);
            orderDetails.add(orderDetail);
            amount += book.getPrice();
        }
        //获取系统时间
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String format = formatter.format(date);

        //扣减库存 不考虑超卖问题  缺货则联系购买者取消订单

        orderMapper.updateBookCnt(orderDetails);


        //向订单表插入一条订单数据
        InsertIntoOrder(orderDTO,singleAddress,uuid,amount,id,format);

        //向订单明细表插入多条数据

        orderMapper.addOrderDetail(orderDetails);

        //清空购物车数据
        clearShopCart();

        //返回结果
        OrderVO orderVO = new OrderVO();
        orderVO.setOrderid(id);
        orderVO.setAmount(amount);
        orderVO.setOrdertime(format);
        return orderVO;
    }
    @Transactional
    public void InsertIntoOrder(OrderDTO orderDTO,Address singleAddress,String uuid,float amount,String id,String format){
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO,order);

        order.setId(id);
        order.setStatus(OrderConstant.PENDING_PAYMENT);
        order.setUserid(uuid);
        order.setAddressid(orderDTO.getAddressid());

        order.setOrder_time(format);
        order.setPay_method(0);
        order.setPay_status(OrderConstant.UNPAID);
        order.setAmount(amount);
        order.setPhone(singleAddress.getPhone());
        order.setAddress(singleAddress.getProvince() + singleAddress.getCity() + singleAddress.getDistrict() + singleAddress.getDetail());
        order.setConsignee(singleAddress.getConsignee());
        order.setDeliver_time(orderDTO.getDeliver_time());
        order.setDeliver_status(OrderConstant.UNSEND);
        orderMapper.addOrder(order);
        redisTemplate.opsForValue().increment(RedisConstant.ORDER_COUNT);
    }

    @Override
    public void pay(PayDTO payDTO) throws OrderNotFoundException, IOException {
        Order order = orderMapper.getOrder(payDTO.getOrderid());
        if (order == null){
            throw new OrderNotFoundException(ExceptionMsg.ORDER_NOTFOUND);
        }

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String format = formatter.format(date);
        orderMapper.pay(payDTO.getOrderid(),OrderConstant.PENDING_CONFIREM,format, payDTO.getPay_method(), OrderConstant.PAID);
        HashMap<String, String> map = new HashMap<>();
        map.put("type","1");
        map.put("orderid",order.getId());
        map.put("text",order.getId());
        webSocketContrller.broadcast(JSONObject.toJSONString(map));
    }
}
