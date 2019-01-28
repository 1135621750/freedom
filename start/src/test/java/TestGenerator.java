import com.freedom.admin.mapper.SysUserMapper;
import com.freedom.admin.model.SysUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestGenerator extends Tester {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    public void TestOne(){
        SysUser byId = sysUserMapper.findById(1L);
        System.err.println(byId);
    }
}
