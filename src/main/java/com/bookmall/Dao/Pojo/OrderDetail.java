package com.bookmall.Dao.Pojo;

public class OrderDetail {
    String orderid;
    String bookid;
    int cnt;
    float amount;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderid='" + orderid + '\'' +
                ", bookid='" + bookid + '\'' +
                ", cnt=" + cnt +
                ", amount=" + amount +
                '}';
    }
}
