package com.bookmall.Service;


import com.bookmall.Dao.Pojo.BookRank;
import com.bookmall.Dao.Pojo.TurnOverStat;

import java.util.List;

public interface StatisticsService {
    List<TurnOverStat> getTurnOverStat(String begin,String end);
    List<Integer> getOrderStat(String begin,String end,int status);
    List<BookRank> getBookRank(String begin,String end,int rank);
}
