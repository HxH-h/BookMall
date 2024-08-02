package com.bookmall.Controller;


import com.bookmall.Controller.ControllerPojo.WeChatDTO;
import com.bookmall.Controller.Response.Code;
import com.bookmall.Controller.Response.Message;
import com.bookmall.Controller.Response.Result;
import com.bookmall.CusException.IndentifiedException;
import com.bookmall.Service.Impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@Tag(name = "处理用户请求")
@RequestMapping("/user")
@CrossOrigin()
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

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

    @Operation(summary = "用户验证码暂存接口")
    @PostMapping("/codecache")
    public Result UserLoginCtroller(@RequestBody WeChatDTO weChatDTO) throws IndentifiedException {
        userServiceImpl.CodeCache(weChatDTO);
        // TODO 返回result
        return null;
    }
}
