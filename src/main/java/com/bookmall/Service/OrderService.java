package com.bookmall.Service;

import com.bookmall.Controller.ControllerPojo.AdminOrderVO;
import com.bookmall.Dao.Pojo.Order;

import java.util.List;

public interface OrderService {

    List<AdminOrderVO> getPendingConfirmOrders();
    List<Order> getDeliveryOrders();
    List<Order> getCompleteOrders();
    void updateOrderStatus(String id,int status);
    void cancelOrder(String id,String reason);
}
