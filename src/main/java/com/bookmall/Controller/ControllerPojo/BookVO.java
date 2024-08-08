package com.bookmall.Controller.ControllerPojo;

public class BookVO {
    String title;
    String author;
    int cnt;

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

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    @Override
    public String toString() {
        return "BookVO{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", cnt=" + cnt +
                '}';
    }
}
