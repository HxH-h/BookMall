package com.bookmall.Constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WeChatConstant {
     @Value("${Wechat.appid}")
     public String appid;

     @Value("${Wechat.appsecret}")
     public String appsecret;

     @Value("${Wechat.granttype}")
     public String granttype;

     @Value("${Wechat.url}")
     public String url;
}
