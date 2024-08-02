package com.bookmall.Utils;

import com.alibaba.fastjson.JSONObject;
import com.bookmall.Constant.ExceptionMsg;
import com.bookmall.CusException.IndentifiedException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class HTTPUtils {

    public static JSONObject get(String url, JSONObject params) throws IndentifiedException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String sendUrl = url;
        // 拼接参数
        if (Objects.nonNull(params) && params.size() > 0) {
            sendUrl = connectParams(url, params);
        }
        HttpGet httpGet = new HttpGet(sendUrl);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            JSONObject jsonObject = null;
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode() && null != httpEntity) {
                jsonObject = JSONObject.parseObject(EntityUtils.toString(httpEntity));
            }
            httpClient.close();
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IndentifiedException(ExceptionMsg.INDENTIFIED_INFO_EXCEPTION);
    }

    private static String connectParams(String url, JSONObject params) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(url).append("?");
        params.forEach((x, y) -> buffer.append(x).append("=").append(y).append("&"));
        buffer.deleteCharAt(buffer.length() - 1);
        return buffer.toString();
    }

    public static byte[] postQRcode(String url, JSONObject jsonObject) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);

        httpPost.setEntity(new StringEntity(jsonObject.toString(),"UTF-8"));
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpPost);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                byte[] bytes = EntityUtils.toByteArray(response.getEntity());
                return bytes;

            }
        } finally {
            if (response != null) {
                response.close();
            }
            httpClient.close();
        }
        return null;
    }

}
