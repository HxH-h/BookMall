package com.bookmall.Dao.Mapper;

import com.bookmall.Dao.Pojo.BookRank;
import com.bookmall.Dao.Pojo.TurnOverStat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StatisticsMapper {

    List<TurnOverStat> getTurnOverStat(String begin, String end,int status);
    List<Integer> getOrderStat(String begin,String end,int status);
    List<BookRank> getBookRank(String begin,String end,int status,int rank);
}
