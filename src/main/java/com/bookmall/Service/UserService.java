package com.bookmall.Service;

import com.bookmall.Controller.ControllerPojo.AddressDTO;
import com.bookmall.Controller.ControllerPojo.WeChatDTO;
import com.bookmall.CusException.IndentifiedException;
import com.bookmall.CusException.OrderNotFoundException;
import com.bookmall.Dao.Pojo.Address;

import java.io.IOException;
import java.util.List;

public interface UserService {

    String checkUser(WeChatDTO weChatDTO);
    void CodeCache(WeChatDTO weChatDTO) throws IndentifiedException;
    void UserRegiste(String openid);
    String getAccessToken() throws IndentifiedException;
    String getQRcode(String accesstoken,String uuid) throws IOException;
    void addAddress(AddressDTO addressDTO);
    void updateDefault(int id);
    void delAddress(int id);
    List<Address> showAddress();
    void remind(String orderid) throws OrderNotFoundException, IOException;

}
