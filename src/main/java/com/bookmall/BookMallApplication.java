package com.bookmall;

import com.bookmall.Dao.Mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class BookMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookMallApplication.class, args);

    }

}
