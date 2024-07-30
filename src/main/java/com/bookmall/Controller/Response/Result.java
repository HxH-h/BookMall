package com.bookmall.Controller.Response;


import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashMap;
import java.util.Map;


public class Result<T> {
    @Schema(name = "状态码")
    Integer code;
    @Schema(name = "状态信息")
    String msg;
    @Schema(name = "响应数据")
    HashMap<String,T> data = null;


    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code, String msg, HashMap<String,T> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public HashMap<String, T> getData() {
        return data;
    }

    public void setData(HashMap<String, T> data) {
        this.data = data;
    }
}
