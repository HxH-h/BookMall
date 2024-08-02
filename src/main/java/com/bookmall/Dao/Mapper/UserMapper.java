package com.bookmall.Dao.Mapper;

import com.bookmall.Dao.Pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void addUser(User user);
    User selectByopenid(String openid);


}
