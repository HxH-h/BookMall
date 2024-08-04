package com.bookmall.Controller;


import com.bookmall.Controller.ControllerPojo.AddressDTO;
import com.bookmall.Controller.ControllerPojo.WeChatDTO;
import com.bookmall.Controller.Response.Code;
import com.bookmall.Controller.Response.Message;
import com.bookmall.Controller.Response.Result;
import com.bookmall.CusException.IndentifiedException;
import com.bookmall.Dao.Pojo.Address;
import com.bookmall.Service.Impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@Tag(name = "处理用户请求")
@RequestMapping("/user")
@CrossOrigin()
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    //生成小程序的二维码
    //返回二维码 和 其对应的uuid
    @GetMapping("/getQRcode")
    public Result<Map> getQRcode() throws IndentifiedException, IOException {
        //获取access_token
        String accessToken = userServiceImpl.getAccessToken();

        //生成uuid
        String uuid = UUID.randomUUID().toString().replace("-","");
        //获取QRcode
        String qRcode = userServiceImpl.getQRcode(accessToken, uuid);
        HashMap<String,String> map = new HashMap<>();
        map.put("qRcode",qRcode);
        map.put("uuid",uuid);
        return new Result<Map>(Code.LOGIN_GETCODE_SUCCESS, Message.LOGIN_GETCODE_SUCCESS,map);
    }

    //获取uuid 验证码 openid 三者绑定关系 缓存redis 等待验证
    @Operation(summary = "用户验证码暂存接口")
    @PostMapping("/codecache")
    public Result UserLoginCtroller(@RequestBody WeChatDTO weChatDTO) throws IndentifiedException {
        userServiceImpl.CodeCache(weChatDTO);
        // TODO 返回result
        return null;
    }

    @Operation(summary = "新增地址")
    @PostMapping("/address/add")
    public Result addAddress(@RequestBody AddressDTO addressDTO){
        userServiceImpl.addAddress(addressDTO);
        return new Result(Code.ADD_ADDRESS_SUCCESS,Message.ADD_ADDRESS_SUCCESS);
    }

    @Operation(summary = "修改默认地址")
    @GetMapping("/address/updatedef/{id}")
    public Result updateDefault(@PathVariable int id){
        userServiceImpl.updateDefault(id);
        return new Result(Code.UPDATE_DEFAULT_SUCCESS,Message.UPDATE_DEFAULT_SUCCESS);
    }

    @Operation(summary = "删除地址")
    @DeleteMapping("/address/delAddress/{id}")
    public Result delAddress(@PathVariable int id){
        userServiceImpl.delAddress(id);
        return new Result(Code.DELETE_ADDRESS_SUCCESS,Message.DELETE_ADDRESS_SUCCESS);
    }

    @Operation(summary = "查询地址")
    @GetMapping("/address/show")
    public Result<List> getAddress(){
        List<Address> addresses = userServiceImpl.showAddress();
        return new Result<List>(Code.SEARCH_ADDRESS_SUCCESS,Message.SEARCH_ADDRESS_SUCCESS,addresses);
    }


}
