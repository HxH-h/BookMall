package com.bookmall.Controller;

import com.bookmall.Controller.Response.Code;
import com.bookmall.Controller.Response.Message;
import com.bookmall.Dao.Mapper.TestMapper;
import com.bookmall.Dao.Pojo.TestPojo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.annotations.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
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
        return new Result(Code.TEST_SUCCESS, Message.TEST_SUCCESS_MSG);
    }
}
