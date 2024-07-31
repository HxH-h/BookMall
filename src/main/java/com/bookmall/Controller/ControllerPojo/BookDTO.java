package com.bookmall.Controller.ControllerPojo;

import lombok.Builder;


public class BookDTO {
    String title;
    String author;
    String time;
    String press;
    float price;
    String detail;
    String uuid;
    int cnt;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public BookDTO(String title, String author, String time, String press, float price, String detail, int cnt) {
        this.title = title;
        this.author = author;
        this.time = time;
        this.press = press;
        this.price = price;
        this.detail = detail;
        this.cnt = cnt;
    }
}
