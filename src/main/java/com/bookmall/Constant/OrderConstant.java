package com.bookmall.Constant;

public class OrderConstant {
    //订单状态
    public static final Integer PENDING_PAYMENT = 1;
    public static final Integer PENDING_CONFIREM = 2;
    public static final Integer CONFIRMED = 3;
    public static final Integer DELIVERYING = 4;
    public static final Integer COMPLETED = 5;
    public static final Integer CANCELED = 6;

    //支付状态
    public static final Integer UNPAID = 0;
    public static final Integer PAID = 1;
    public static final Integer REFUND = 2;

    //快递状态
    public static final Integer UNSEND = 0;
    public static final Integer SEND = 1;

    public static final Integer ORDER_TIMEOUT = 60*1000;


}
