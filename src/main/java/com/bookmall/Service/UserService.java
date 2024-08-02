package com.bookmall.Service;

import com.bookmall.Controller.ControllerPojo.WeChatDTO;
import com.bookmall.CusException.IndentifiedException;

import java.io.IOException;

public interface UserService {

    String checkUser(WeChatDTO weChatDTO);
    void CodeCache(WeChatDTO weChatDTO) throws IndentifiedException;
    void UserRegiste(String openid);
    String getAccessToken() throws IndentifiedException;
    String getQRcode(String accesstoken,String uuid) throws IOException;


}
