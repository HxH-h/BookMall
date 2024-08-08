package com.bookmall.Dao.Mapper;

import com.bookmall.Controller.ControllerPojo.AdminOrderVO;
import com.bookmall.Dao.Pojo.Order;
import com.bookmall.Dao.Pojo.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OrderMapper {

    void addOrder(Order order);
    void addOrderDetail(List<OrderDetail> orderDetails);

    int updateBookCnt(List<OrderDetail> orderDetails);
    void pay(String id,int status,String pay_time,int pay_method,int pay_status);
    Order getOrder(String id);
    void cancelOrder(int status,String cancel_reason,String cancel_time,String datetime);


    List<AdminOrderVO> getPendingConfirmOrders();
    @Select("select * from shoporder where status = 3")
    List<Order> getDeliveryOrders();
    @Select("select * from shoporder where status = 4")
    List<Order> getCompleteOrders();

    @Update("update shoporder set status = #{status} where id = #{order_id}")
    void updateOrderStatus(int status,String order_id);

    void adminCancelOrder(String id,String cancel_reason,String cancel_time);
}
