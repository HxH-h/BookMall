package com.bookmall.Dao.Mapper;

import com.bookmall.Dao.Pojo.Order;
import com.bookmall.Dao.Pojo.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    void addOrder(Order order);
    void addOrderDetail(List<OrderDetail> orderDetails);

    int updateBookCnt(List<OrderDetail> orderDetails);
    void pay(String id,int status,String pay_time,int pay_method,int pay_status);
    Order getOrder(String id);
}
