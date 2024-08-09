package com.bookmall.Service.Impl;

import com.bookmall.Constant.OrderConstant;
import com.bookmall.Controller.ControllerPojo.AdminOrderVO;
import com.bookmall.Dao.Mapper.OrderMapper;
import com.bookmall.Dao.Pojo.Order;
import com.bookmall.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Override
    public List<AdminOrderVO> getPendingConfirmOrders() {
        return orderMapper.getPendingConfirmOrders();
    }

    @Override
    public List<Order> getDeliveryOrders() {
        return orderMapper.getDeliveryOrders();
    }

    @Override
    public List<Order> getCompleteOrders() {
        return orderMapper.getCompleteOrders();
    }

    @Override
    public void updateOrderStatus(String id, int status) {
        if (status == 1) {
            orderMapper.updateOrderStatus(OrderConstant.DELIVERYING,id);
        } else if (status == 2) {
            orderMapper.updateOrderStatus(OrderConstant.COMPLETED,id);
        } else if (status == 3) {
            orderMapper.updateOrderStatus(OrderConstant.CONFIRMED,id);
        }

    }

    @Override
    public void cancelOrder(String id, String reason) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String format = formatter.format(date);
        orderMapper.adminCancelOrder(id,reason,format);
    }
}
