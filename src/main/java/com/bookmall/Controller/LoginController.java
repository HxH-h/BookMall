package com.bookmall.Controller;

import com.bookmall.Controller.ControllerPojo.LoginDTO;
import com.bookmall.Controller.Response.Code;
import com.bookmall.Controller.Response.Message;
import com.bookmall.Controller.Response.Result;
import com.bookmall.Controller.Utils.JWTUtils;
import com.bookmall.Service.Impl.LoginServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@Tag(name = "管理员 和 用户登录接口")
public class LoginController {
    @Autowired
    LoginServiceImpl loginServiceImpl;

    @Operation(summary = "登录接口")
    @PostMapping("/admin/login")
    public Result AdminLoginCtroller(@RequestBody LoginDTO loginDTO){
        if (loginServiceImpl.AdminExist(loginDTO.getUsername() , loginDTO.getPassword())){
            String jwt = JWTUtils.getJWT(loginDTO);
            HashMap<String, String> token = new HashMap<>();
            token.put("token",jwt);
            return new Result(Code.Login_SUCCESS,Message.LOGIN_SUCCESS,token);
        }else {
            return new Result(Code.Login_FAILURE, Message.LOGIN_FAILURE);
        }

    }

    @Operation(summary = "用户登录接口")
    @PostMapping("/user/login")
    public Result UserLoginCtroller(){
        return null;
    }
}
