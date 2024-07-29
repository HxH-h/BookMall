package com.bookmall.Controller.Response;


import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Map;


public class Result {
    @Schema(name = "状态码")
    Integer code;
    @Schema(name = "状态信息")
    String msg;
    @Schema(name = "响应数据")
    Map<String,Object> data = null;


    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code, String msg, Map<String, Object> data) {
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

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
