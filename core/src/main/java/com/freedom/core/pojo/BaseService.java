package com.freedom.core.pojo;

import cn.hutool.core.lang.Snowflake;
import com.freedom.core.config.Constant;

public class BaseService {

    public static Long getNextId(){
        return new Snowflake(Constant.WORKER_ID,Constant.DATACENTER_ID,Constant.IS_USESYSTEM_CLOCK).nextId();
    }
}
