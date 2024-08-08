package com.bookmall.Controller;

import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
@ServerEndpoint("/ws/{clientid}")
public class WebSocketContrller {
    public static Map<String , Session> map = new HashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("clientid") String client){
        map.put(client,session);
        System.out.println("已连接");
    }

    @OnMessage
    public void onMessage(String msg,Session session){

    }


    @OnError
    public void onError(Session session, Throwable t){
        t.printStackTrace();
    }
    @OnClose
    public void onClose(){
        System.out.println("已关闭");
    }
    public void broadcast(String message) throws IOException {
        Collection<Session> values = map.values();
        for (Session session : values){
            session.getBasicRemote().sendText(message);
        }
    }
}
