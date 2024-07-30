package com.bookmall.Dao.Mapper;

import com.bookmall.Dao.Pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface AdministratorMapper {

    Admin getAdminByname(@Param("username") String username);

}
