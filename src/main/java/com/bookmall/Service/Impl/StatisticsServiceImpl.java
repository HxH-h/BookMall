package com.bookmall.Service.Impl;

import com.bookmall.Constant.OrderConstant;
import com.bookmall.Dao.Mapper.StatisticsMapper;
import com.bookmall.Dao.Pojo.BookRank;
import com.bookmall.Dao.Pojo.TurnOverStat;
import com.bookmall.Service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    StatisticsMapper statisticsMapper;


    @Override
    public List<TurnOverStat> getTurnOverStat(String begin,String end) {
        return statisticsMapper.getTurnOverStat(begin,end, OrderConstant.COMPLETED);
    }

    @Override
    public List<Integer> getOrderStat(String begin, String end, int status) {
        return statisticsMapper.getOrderStat(begin,end,status);
    }

    @Override
    public List<BookRank> getBookRank(String begin, String end, int rank) {
        return statisticsMapper.getBookRank(begin,end,OrderConstant.COMPLETED,rank);
    }
}
