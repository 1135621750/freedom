package com.freedom.core.pojo;

import com.freedom.core.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService {

    @Autowired
    private IdWorker idWorker;

    public Long getId(){
        return idWorker.getNextId();
    }
}
