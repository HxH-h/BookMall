package com.bookmall.Controller.ControllerPojo;

public class WeChatDTO {
    String indentifiedCode;
    String userCode;
    String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getIndentifiedCode() {
        return indentifiedCode;
    }

    public void setIndentifiedCode(String indentifiedCode) {
        this.indentifiedCode = indentifiedCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }


}
