import com.alibaba.fastjson.JSONObject;
import com.freedom.admin.model.SysUser;
import com.freedom.core.jwt.JWTUtil;
import com.freedom.core.utils.RedisUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class RedisTest extends Tester{

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private JWTUtil jwtUtil;

    //@Test
    public void one(){
        SysUser sysUser = new SysUser();
        sysUser.setUserName("测试redis");
        sysUser.setId(123L);
        sysUser.setIsDelete(true);
        redisUtils.set("freedom:user",sysUser);
        redisUtils.set("freedom:user1","测试一下字符串",RedisUtils.NOT_EXPIRE);
        redisUtils.set("freedom:user2",12345,RedisUtils.NOT_EXPIRE);
        String s = redisUtils.get("freedom:user1");
        System.err.println(s);
        Long aLong = redisUtils.get("freedom:user2", Long.class);
        System.err.println("----"+aLong);
        SysUser sysUser2 = redisUtils.get("freedom:user", SysUser.class, 1000L);
        System.err.println(JSONObject.toJSONString(sysUser2));
    }
    @Test
    public void token() throws Exception{

    }
}
