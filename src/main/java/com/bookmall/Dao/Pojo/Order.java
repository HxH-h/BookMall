package com.bookmall.Dao.Pojo;

public class Order {
    String id;
    int status;
    String userid;
    int addressid;
    String order_time;
    String pay_time;
    int pay_method;
    int pay_status;
    float amount;
    String phone;
    String address;
    String consignee;
    String cancel_reason;
    String rejection_reason;
    String cancel_time;
    String deliver_time;
    int deliver_status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getAddressid() {
        return addressid;
    }

    public void setAddressid(int addressid) {
        this.addressid = addressid;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }

    public int getPay_method() {
        return pay_method;
    }

    public void setPay_method(int pay_method) {
        this.pay_method = pay_method;
    }

    public int getPay_status() {
        return pay_status;
    }

    public void setPay_status(int pay_status) {
        this.pay_status = pay_status;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getCancel_reason() {
        return cancel_reason;
    }

    public void setCancel_reason(String cancel_reason) {
        this.cancel_reason = cancel_reason;
    }

    public String getRejection_reason() {
        return rejection_reason;
    }

    public void setRejection_reason(String rejection_reason) {
        this.rejection_reason = rejection_reason;
    }

    public String getCancel_time() {
        return cancel_time;
    }

    public void setCancel_time(String cancel_time) {
        this.cancel_time = cancel_time;
    }

    public String getDeliver_time() {
        return deliver_time;
    }

    public void setDeliver_time(String deliver_time) {
        this.deliver_time = deliver_time;
    }

    public int getDeliver_status() {
        return deliver_status;
    }

    public void setDeliver_status(int deliver_status) {
        this.deliver_status = deliver_status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", status=" + status +
                ", userid='" + userid + '\'' +
                ", addressid=" + addressid +
                ", order_time='" + order_time + '\'' +
                ", pay_time='" + pay_time + '\'' +
                ", pay_method=" + pay_method +
                ", pay_status=" + pay_status +
                ", amount=" + amount +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", consignee='" + consignee + '\'' +
                ", cancel_reason='" + cancel_reason + '\'' +
                ", rejection_reason='" + rejection_reason + '\'' +
                ", cancel_time='" + cancel_time + '\'' +
                ", deliver_time='" + deliver_time + '\'' +
                ", deliver_status=" + deliver_status +
                '}';
    }
}
