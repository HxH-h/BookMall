package com.bookmall.Controller;

import com.bookmall.Controller.Response.Code;
import com.bookmall.Controller.Response.Message;
import com.bookmall.Controller.Response.Result;
import com.bookmall.Dao.Pojo.BookRank;
import com.bookmall.Dao.Pojo.TurnOverStat;
import com.bookmall.Service.Impl.StatisticsServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/data")
@CrossOrigin
@Tag(name = "后台数据统计")
public class adminDataController {

    @Autowired
    StatisticsServiceImpl statisticsServiceImpl;

    @GetMapping("/turnover/{begin}/{end}")
    public Result<List> MoneyStat(@PathVariable String begin, @PathVariable String end) {
        List<TurnOverStat> turnOverStat = statisticsServiceImpl.getTurnOverStat(begin, end);
        return new Result<>(Code.GET_STAT_SUCCESS, Message.GET_STAT_SUCCESS,turnOverStat);
    }

    @GetMapping("/order/{begin}/{end}/{status}")
    public Result<List> getOrderStat(@PathVariable String begin, @PathVariable String end, @PathVariable int status){
        List<Integer> orderStat = statisticsServiceImpl.getOrderStat(begin, end, status);
        return new Result<>(Code.GET_STAT_SUCCESS, Message.GET_STAT_SUCCESS,orderStat);
    }

    @GetMapping("/rank/{begin}/{end}/{rank}")
    public Result<List> getBookRank(@PathVariable String begin, @PathVariable String end, @PathVariable int rank){
        List<BookRank> bookRank = statisticsServiceImpl.getBookRank(begin, end, rank);
        return new Result(Code.GET_STAT_SUCCESS, Message.GET_STAT_SUCCESS,bookRank);
    }

}
