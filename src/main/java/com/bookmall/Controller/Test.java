package com.bookmall.Controller;

import com.bookmall.Controller.Response.Code;
import com.bookmall.Controller.Response.Message;
import com.bookmall.CusException.AutoUpdateTimeException;
import com.bookmall.Dao.Mapper.TestMapper;
import com.bookmall.Dao.Pojo.TestPojo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.annotations.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.bookmall.Controller.Response.Result;
import java.util.List;
import java.util.Map;

@Controller
@Tag(name = "测试接口")
@RestController
public class Test {
    @Autowired
    TestMapper testMapper;


    @GetMapping("/test")
    @Operation(summary = "测试接口")
    public Result test(){
        TestPojo test = testMapper.getTest();
        System.out.println(test.getId() + test.getName());
        return new Result(Code.GET_SUCCESS, Message.GET_SUCCESS_MSG);
    }

    @PostMapping("/testpost")
    @Operation(summary = "测试POST接口")
    public Result testpost(){
        TestPojo test = testMapper.getTest();
        System.out.println(test.getId() + test.getName());
        return new Result(Code.POST_SUCCESS, Message.POST_SUCCESS_MSG);
    }

    @GetMapping("/test/{choice}")
    @Operation(summary = "异常测试接口")
    public Result exceptiontest(@PathVariable int choice) throws AutoUpdateTimeException {
        if (choice == 0){
            throw new AutoUpdateTimeException();
        }
        return new Result(Code.GET_SUCCESS, Message.GET_SUCCESS_MSG);
    }


}
