package com.bookmall.task;


import com.bookmall.Constant.OrderConstant;
import com.bookmall.Dao.Mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Component
@Slf4j
public class TimeTask {

    @Autowired
    OrderMapper orderMapper ;

    @Scheduled(cron = "0 * * * * ? ")
    public void CancelOrder(){

//        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String format = formatter.format(new Date(new Date().getTime() - OrderConstant.ORDER_TIMEOUT));
//
//        int cnt = orderMapper.cancelOrder(OrderConstant.CANCELED,
//                                "订单超时，自动取消",
//                                        formatter.format(new Date(System.currentTimeMillis())),
//                                        format);
//
//        log.info("超时订单处理:  "+ cnt + "条");
    }


}
