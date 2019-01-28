package com.freedom.admin.service;

import com.freedom.admin.model.User;
import com.freedom.core.pojo.BaseService;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService{

    public void add(User user){
        System.err.println(user);
    }
}
