package com.bookmall.Controller;

import com.bookmall.Controller.ControllerPojo.LoginDTO;
import com.bookmall.Controller.ControllerPojo.WeChatDTO;
import com.bookmall.Controller.Response.Code;
import com.bookmall.Controller.Response.Message;
import com.bookmall.Controller.Response.Result;
import com.bookmall.Service.Impl.UserServiceImpl;
import com.bookmall.Utils.JWTUtils;
import com.bookmall.Service.Impl.LoginServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Tag(name = "管理员 和 用户登录接口")
@CrossOrigin
public class LoginController {
    @Autowired
    LoginServiceImpl loginServiceImpl;
    @Autowired
    UserServiceImpl userServiceImpl;

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
    /*
    *   用户登录逻辑
    *       1.网页生成二维码 携带临时uuid 跳转小程序
    *       2.小程序获取用户微信openid 生成验证码 发请求后端在redis存储，将uuid，验证码，用户微信三者绑定
    *       3.网页输入二维码，自动附带uuid
    *       4.uuid + 验证码 唯一标识一个验证用户
    *       5.未注册则自动注册
    *
    * */
    //网页端输入 小程序端的验证码  + 二维码的uuid
    @Operation(summary = "用户登录接口")
    @PostMapping("/user/login")
    public Result UserLoginCtroller(@RequestBody WeChatDTO weChatDTO){
        //验证验证码正确
        String s = userServiceImpl.checkUser(weChatDTO);
        if ( s != null){
            //根据openid 生成token
            String userToken = JWTUtils.getUserToken(s);
            HashMap<String, String> token = new HashMap<>();
            token.put("token",userToken);
            return new Result<Map>(Code.USER_LOGIN_SUCCESS,Message.USER_LOGIN_SUCCESS,token);
        }else {
            //返回验证码错误
            return new Result(Code.INDENTIFIED_CODE_INCORRECT,Message.INDENTIFIED_CODE_INCORRECT);
        }
    }
}
