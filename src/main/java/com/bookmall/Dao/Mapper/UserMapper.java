package com.bookmall.Dao.Mapper;

import com.bookmall.Dao.Pojo.Address;
import com.bookmall.Dao.Pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    void addUser(User user);
    User selectByopenid(String openid);

    void addAddress(Address address);

    String getUUid(String openid);

    void updateDefault(int id,String uuid);
    void delAddress(int id,String uuid);
    List<Address> getAddress(String uuid);
    Address getSingleAddress(String uuid,int id);


}
