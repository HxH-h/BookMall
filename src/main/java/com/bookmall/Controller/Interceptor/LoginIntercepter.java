package com.bookmall.Controller.Interceptor;

import com.bookmall.Controller.Response.Code;
import com.bookmall.Controller.Response.Message;
import com.bookmall.Controller.Response.Result;
import com.bookmall.Controller.UserInfoThread;
import com.bookmall.Utils.JWTUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("Authorization");
        if (token == null){
            System.out.println("拦截请求" + request.getRequestURI());
            Result result = new Result(Code.NEEDLOGIN, Message.NEEDLOGIN);
            ObjectMapper objectMapper = new ObjectMapper();
            String s = objectMapper.writeValueAsString(result);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(s);
            return false;
        }

        try {
            String requestURI = request.getRequestURI();
            if (requestURI.contains("user")){
                String openid = JWTUtils.parseJWT(token,"openid");
                UserInfoThread.setInfo(openid);
            } else if (requestURI.contains("admin")) {
                JWTUtils.parseJWT(token,"username");
            }

        }catch (Exception e){
            System.out.println("拦截请求" + request.getRequestURI());
            Result result = new Result(Code.NEEDLOGIN, Message.NEEDLOGIN);
            ObjectMapper objectMapper = new ObjectMapper();
            String s = objectMapper.writeValueAsString(result);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(s);
            return false;
        }

        return true;
    }
}
