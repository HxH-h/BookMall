package com.bookmall.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.bookmall.Constant.ExceptionMsg;
import com.bookmall.Constant.RedisConstant;
import com.bookmall.Controller.ControllerPojo.GoodsDTO;
import com.bookmall.Controller.UserInfoThread;
import com.bookmall.CusException.BookNotFoundException;
import com.bookmall.Dao.Mapper.BookMapper;
import com.bookmall.Dao.Pojo.Book;
import com.bookmall.Service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    BookMapper bookMapper;

    //添加购物车
    @Override
    public void addGoods(GoodsDTO goodsDTO) throws BookNotFoundException {
        String openid = UserInfoThread.getInfo();
        //判断购物车是否存在
        Set keys = redisTemplate.opsForHash().keys(RedisConstant.SHOPCART + openid);
        if (keys.isEmpty()){
            Book book = bookMapper.selectByuuid(goodsDTO.getUuid());
            if (book == null){
                throw new BookNotFoundException(ExceptionMsg.BOOK_NOTFOUND);
            }
            //设置购买数量
            book.setCnt(goodsDTO.getCnt());
            redisTemplate.opsForHash().put(RedisConstant.SHOPCART+openid,goodsDTO.getUuid(),JSONObject.toJSONString(book));
        }else {
            //判断图书是否已经存在
            if (redisTemplate.opsForHash().hasKey(RedisConstant.SHOPCART+openid,goodsDTO.getUuid())){
                // 存在则数量 +cnt
                String s = (String) redisTemplate.opsForHash().get(RedisConstant.SHOPCART + openid, goodsDTO.getUuid());
                Book book = JSONObject.parseObject(s, Book.class);
                int cnt = book.getCnt() + goodsDTO.getCnt();
                if (cnt <= 0){
                    redisTemplate.opsForHash().delete(RedisConstant.SHOPCART + openid, goodsDTO.getUuid());
                }else {
                    book.setCnt(cnt);
                    redisTemplate.opsForHash().put(RedisConstant.SHOPCART+openid,goodsDTO.getUuid(),JSONObject.toJSONString(book));
                }

            }else {
                // 不存在则 添加信息
                Book book = bookMapper.selectByuuid(goodsDTO.getUuid());
                //设置购买数量
                book.setCnt(goodsDTO.getCnt());
                redisTemplate.opsForHash().put(RedisConstant.SHOPCART+openid,goodsDTO.getUuid(),JSONObject.toJSONString(book));

            }

        }

    }


    @Override
    public void clearShopCart() {
        String openid = UserInfoThread.getInfo();
        Set keys = redisTemplate.opsForHash().keys(RedisConstant.SHOPCART + openid);
        if (keys == null){
            return ;
        }
        redisTemplate.delete(RedisConstant.SHOPCART +openid);
    }

    @Override
    public List<Book> showCart() {
        String openid = UserInfoThread.getInfo();
        Set keys = redisTemplate.opsForHash().keys(RedisConstant.SHOPCART + openid);
        if (keys == null){
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
}
