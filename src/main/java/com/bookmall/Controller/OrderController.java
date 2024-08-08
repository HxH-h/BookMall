package com.bookmall.Controller;

import com.alibaba.fastjson.JSONObject;
import com.bookmall.Constant.OrderConstant;
import com.bookmall.Controller.ControllerPojo.AdminOrderVO;
import com.bookmall.Controller.Response.Code;
import com.bookmall.Controller.Response.Message;
import com.bookmall.Controller.Response.Result;
import com.bookmall.Dao.Pojo.Order;
import com.bookmall.Service.Impl.OrderServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "后台管理订单")
@RequestMapping("/admin/order")
@CrossOrigin
public class OrderController {

    @Autowired
    OrderServiceImpl orderServiceImpl;

    @GetMapping("/needconfirm")
    @ResponseBody
    public Result<List> getPendingConfirmOrder(){
        List<AdminOrderVO> pendingConfirmOrders = orderServiceImpl.getPendingConfirmOrders();
        return new Result<List>(Code.GET_ORDER_SUCCESS, Message.GET_ORDER_SUCCESS,pendingConfirmOrders);
    }

    @GetMapping("/needdeliver")
    @ResponseBody
    public Result<List> getDeliveryOrders(){
        List<Order> Orders = orderServiceImpl.getDeliveryOrders();
        return new Result<List>(Code.GET_ORDER_SUCCESS, Message.GET_ORDER_SUCCESS,Orders);
    }

    @GetMapping("/needcomplete")
    @ResponseBody
    public Result<List> getCompleteOrders(){
        List<Order> Orders = orderServiceImpl.getCompleteOrders();
        return new Result<List>(Code.GET_ORDER_SUCCESS, Message.GET_ORDER_SUCCESS,Orders);
    }

    @PostMapping("/confirm")
    @ResponseBody
    public Result Confirm(@RequestBody String data){
        String id = JSONObject.parseObject(data).getString("id");
        orderServiceImpl.updateOrderStatus(id, OrderConstant.CONFIRMED);
        return new Result(Code.CONFIRM_ORDER_SUCCESS,Message.CONFIRM_ORDER_SUCCESS);
    }

    @PostMapping("/cancel")
    @ResponseBody
    public Result Cancel(@RequestBody String data){
        String id = JSONObject.parseObject(data).getString("id");
        String reason = JSONObject.parseObject(data).getString("reason");
        orderServiceImpl.cancelOrder(id,reason);
        return new Result(Code.CANCEL_ORDER_SUCCESS,Message.CANCEL_ORDER_SUCCESS);
    }

    @PostMapping("/updateStatus")
    public Result updateOrderStatus(@RequestBody String data){
        String id = JSONObject.parseObject(data).getString("id");
        int status = JSONObject.parseObject(data).getInteger("status");
        orderServiceImpl.updateOrderStatus(id,status);
        return new Result(Code.UPDATE_ORDERSTATUS_SUCCESS,Message.UPDATE_ORDERSTATUS_SUCCESS);
    }


}
