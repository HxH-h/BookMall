package com.bookmall.Dao.Mapper;

import com.bookmall.Dao.Pojo.TestPojo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {
    TestPojo getTest();
}
