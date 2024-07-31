package com.bookmall.Service.Impl;

import com.bookmall.Dao.Mapper.AdministratorMapper;
import com.bookmall.Dao.Pojo.Admin;
import com.bookmall.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    AdministratorMapper administratorMapper;
    @Override
    public boolean AdminExist(String username,String password){
        Admin adminByname = administratorMapper.getAdminByname(username);
        //没查到直接返回
        if (adminByname == null){
            return false;
        }

        //查到了验证密码是否正确 将密码MD5加密与数据库比对
        if (adminByname.getPassword().equals(DigestUtils.md5DigestAsHex((password).getBytes()))){
            return true;
        }else {
            return false;
        }
    }
}
