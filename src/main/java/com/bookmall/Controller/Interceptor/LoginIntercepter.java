package com.bookmall.Controller.Interceptor;

import com.bookmall.Controller.Response.Code;
import com.bookmall.Controller.Response.Message;
import com.bookmall.Controller.Response.Result;
import com.bookmall.Controller.Utils.JWTUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截请求" + request.getRequestURI());
        String token = request.getHeader("Authorization");
        if (token == null){
            Result result = new Result(Code.NEEDLOGIN, Message.NEEDLOGIN);
            ObjectMapper objectMapper = new ObjectMapper();
            String s = objectMapper.writeValueAsString(result);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(s);
            return false;
        }

        try {
            JWTUtils.parseJWT(token);
        }catch (Exception e){
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
