package com.bookmall.Controller.ControllerPojo;

import java.util.List;

public class AdminOrderVO {

    String id;
    String order_time;
    float amount;
    String phone;
    String address;
    String consignee;
    List<BookVO> books;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
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

    public List<BookVO> getBooks() {
        return books;
    }

    public void setBooks(List<BookVO> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "AdminOrderVO{" +
                "id=" + id +
                ", order_time='" + order_time + '\'' +
                ", amount=" + amount +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", consignee='" + consignee + '\'' +
                ", books=" + books +
                '}';
    }
}


