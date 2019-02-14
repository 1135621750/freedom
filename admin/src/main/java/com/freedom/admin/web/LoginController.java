package com.freedom.admin.web;

import com.freedom.admin.model.SysUser;
import com.freedom.admin.service.SysUserService;
import com.freedom.admin.vo.LoginForm;
import com.freedom.core.jwt.JWTUtil;
import com.freedom.core.result.JsonResult;
import com.freedom.core.result.R;
import com.freedom.core.utils.CaptchaUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录注销
 */
@RestController
public class LoginController {

    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public JsonResult<?> login(@Validated @RequestBody LoginForm form, BindingResult result)throws Exception{
        // 判断验证码是否正确
        Session session = SecurityUtils.getSubject().getSession();
        String code = (String) session.getAttribute("captcha");
        if (StringUtils.isEmpty(form.getCaptcha()) || StringUtils.isEmpty(code)
                || !form.getCaptcha().toUpperCase().equals(code.toUpperCase())) {
            return JsonResult.error(R.USER_CAPTCHA_ERROR);
        }
        session.removeAttribute("captcha");
        //获取用户数据
        SysUser user = sysUserService.queryUser(form.getUserName());
        //账号密码错误
        if(user == null || !user.getPassword().equals(new Sha256Hash(form.getPassword(), user.getSalt()).toHex())) {
            return JsonResult.error(R.LONGIN_ERROR);
        }

        //账号锁定
        if(!user.getStatus()){
            return JsonResult.error(R.ACCOUNT_LOCKOUT);
        }

        //生成token保存到....
        Map map = new HashMap();
        map.put("id",user.getId());
        map.put("userName",user.getUserName());
        String sign = jwtUtil.sign(map);
        return JsonResult.success(sign);
    }
    @RequestMapping(value = "/loginOut",method = RequestMethod.GET)
    public JsonResult<?> loginOut(){
        return JsonResult.success();
    }
    /**
     * 验证码图片
     */
    @GetMapping("/captcha.jpg")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置响应头信息，通知浏览器不要缓存
        response.setHeader("Expires", "-1");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "-1");
        response.setContentType("image/jpeg");

        // 获取验证码
        String code = CaptchaUtil.getRandomCode();
        // 将验证码输入到session中，用来验证
        request.getSession().setAttribute("captcha", code);
        // 输出到web页面
        ImageIO.write(CaptchaUtil.genCaptcha(code), "jpg", response.getOutputStream());

    }
    
}
