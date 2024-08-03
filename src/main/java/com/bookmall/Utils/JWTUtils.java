package com.bookmall.Utils;

import com.bookmall.Controller.ControllerPojo.LoginDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
@Component
//jwt工具类
public class JWTUtils {

    static String secretKey;
    static String userSecretKey;
    @Value("${jwt.secret-key}")
    public void setSecretKey(String secretKey) {
        JWTUtils.secretKey = secretKey;
    }

    @Value("${jwt.user-secret-key}")
    public static void setUserSecretKey(String userSecretKey) {
        JWTUtils.userSecretKey = userSecretKey;
    }

    static Integer Expire;
    @Value("${jwt.expire}")
    public void setExpire(Integer expire) {
        JWTUtils.Expire = expire;
    }

    public static String getJWT(LoginDTO loginDTO){
        HashMap<String, String> info = new HashMap<>();
        info.put("username", loginDTO.getUsername());
        info.put("password", loginDTO.getPassword());

        return Jwts.builder()
                .setClaims(info)     //自定义载荷
                .signWith(SignatureAlgorithm.HS256, secretKey)    //签名算法
                .setExpiration(new Date(System.currentTimeMillis() + Expire))
                .compact();
    }

    public static String getUserToken(String openid){
        HashMap<String, String> info = new HashMap<>();
        info.put("openid", openid);
        return Jwts.builder()
                .setClaims(info)     //自定义载荷
                .signWith(SignatureAlgorithm.HS256, secretKey)    //签名算法
                .setExpiration(new Date(System.currentTimeMillis() + Expire))
                .compact();
    }
    public static String parseJWT(String token ,String type){

        Claims body = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return (String) body.get(type);

    }



}
