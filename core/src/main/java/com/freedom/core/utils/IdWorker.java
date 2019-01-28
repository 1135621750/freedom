package com.freedom.core.utils;

import cn.hutool.core.lang.Snowflake;
import com.freedom.core.config.MyYml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import sun.net.util.IPAddressUtil;

import java.math.BigDecimal;
import java.net.Inet4Address;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 生成id
 * @author Bai
 *
 */
@Component
public class IdWorker {
	@Autowired
	private MyYml yml;
	public Long getNextId(){
		return new Snowflake(yml.getWorkerId(),yml.getDatacenterId(),yml.getIsUseSystemClock()).nextId();
	}

}
