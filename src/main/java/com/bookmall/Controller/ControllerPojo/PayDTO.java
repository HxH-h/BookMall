package com.bookmall.Controller.ControllerPojo;

public class PayDTO {
    String orderid;
    int pay_method;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public int getPay_method() {
        return pay_method;
    }

    public void setPay_method(int pay_method) {
        this.pay_method = pay_method;
    }
}
