package com.bookmall.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.bookmall.Constant.RedisConstant;
import com.bookmall.Constant.WeChatConstant;
import com.bookmall.Controller.ControllerPojo.WeChatDTO;
import com.bookmall.CusException.IndentifiedException;
import com.bookmall.Dao.Mapper.UserMapper;
import com.bookmall.Dao.Pojo.User;
import com.bookmall.Service.UserService;
import com.bookmall.Utils.HTTPUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    UserMapper userMapper;

    @Autowired
    WeChatConstant weChatConstant;
    @Override
    public String checkUser(WeChatDTO weChatDTO) {
        // 验证验证码

        if (redisTemplate.opsForHash().hasKey(RedisConstant.LOGIN+weChatDTO.getUuid(),weChatDTO.getIndentifiedCode())){
            String openid = (String) redisTemplate.opsForHash().get(RedisConstant.LOGIN+weChatDTO.getUuid(),weChatDTO.getIndentifiedCode());
            if (!searchUser(openid)){
                UserRegiste(openid);
            }
            return openid;
        }else {
            return null;
        }

    }


    @Override
    public void CodeCache(WeChatDTO weChatDTO) throws IndentifiedException {
        //发HTTP请求获取openid
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("appid",weChatConstant.appid);
        jsonObject.put("secret",weChatConstant.appsecret);
        jsonObject.put("js_code",weChatDTO.getUserCode());
        jsonObject.put("grant_type",weChatConstant.granttype);
        JSONObject info = HTTPUtils.get(weChatConstant.url, jsonObject);
        String openid = info.getString("openid");
        System.out.println(openid);

        // redis缓存openid 和验证码键值对
        redisTemplate.opsForHash().put(RedisConstant.LOGIN+weChatDTO.getUuid(),
                                            weChatDTO.getIndentifiedCode(),
                                            openid);
        redisTemplate.expire(RedisConstant.LOGIN+weChatDTO.getUuid(),5, TimeUnit.MINUTES);

    }
    public boolean searchUser(String openid){
        User user = userMapper.selectByopenid(openid);
        if (user == null){
            return false;
        }else {
            return true;
        }
    }
    @Override
    public void UserRegiste(String openid) {
        User user =new User();
        String uuid = UUID.randomUUID().toString();
        user.setUuid(uuid);
        user.setOpenid(openid);
        user.setUsername(uuid);
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        user.setAddtime(formatter.format(date));
        userMapper.addUser(user);
    }

    @Override
    public String getAccessToken() throws IndentifiedException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("grant_type","client_credential");
        jsonObject.put("appid",weChatConstant.appid);
        jsonObject.put("secret",weChatConstant.appsecret);
        JSONObject info = HTTPUtils.get("https://api.weixin.qq.com/cgi-bin/token", jsonObject);
        return info.getString("access_token");
    }

    @Override
    public String getQRcode(String accesstoken,String uuid) throws IOException {
        System.out.println(accesstoken + "   "+ uuid);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("scene",uuid);
        jsonObject.put("check_path",false);
        jsonObject.put("page","pages/index/index");
        jsonObject.put("env_version","trial");
        byte[] bytes = HTTPUtils.postQRcode("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+accesstoken,jsonObject);
        String base64Str = Base64.getEncoder().encodeToString(bytes);
        return base64Str;
    }

}
