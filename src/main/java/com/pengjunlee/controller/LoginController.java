package com.pengjunlee.controller;


import com.pengjunlee.config.jwt.JwtUtils;
import com.pengjunlee.domain.BaseResponse;
import com.pengjunlee.domain.UserAuthInfo;
import com.pengjunlee.domain.VerifyCode;
import com.pengjunlee.service.IVerifyCodeGen;
import com.pengjunlee.service.UserService;
import com.pengjunlee.service.impl.SimpleCharVerifyCodeGenImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author pengjunlee
 * @create 2019-09-03 9:27
 */
@RequestMapping("/api/v1")
@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public Object userLogin(@RequestBody Map<String, String> bodyMap, ServletResponse response) {
        // 获取用户名和密码
        String userName = bodyMap.get("username");
        String password = bodyMap.get("password");

        // 获取当前用户主体
        Subject subject = SecurityUtils.getSubject();
        // 将用户名和密码封装成 UsernamePasswordToken 对象
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);

        String msg = null;
        boolean loginSuccess = false;
        try {
            subject.login(token);
            msg = "登录成功。";
            loginSuccess = true;
        } catch (UnknownAccountException uae) { // 账号不存在
            msg = "用户名与密码不匹配！";
        } catch (IncorrectCredentialsException ice) { // 账号与密码不匹配
            msg = "用户名与密码不匹配！";
        } catch (LockedAccountException lae) { // 账号已被锁定
            msg = "该账户已被锁定！";
        } catch (AuthenticationException ae) { // 其他身份验证异常
            msg = "登录异常，请联系管理员！";
        }

        BaseResponse<Object> ret = new BaseResponse<Object>();

        if (loginSuccess) {
            // 若登录成功，签发 JWT token
            String jwtToken = JwtUtils.sign(userName, JwtUtils.SECRET);
            // 将签发的 JWT token 设置到 HttpServletResponse 的 Header 中
            ((HttpServletResponse) response).setHeader(JwtUtils.AUTH_HEADER, jwtToken);
            // 封装响应
            UserAuthInfo userAuth = userService.getUserAuthByName(userName);
            ret.setData(userAuth);
            ret.setCode(0);
            ret.setMessage(msg);
        } else {
            ret.setCode(-201);
            ret.setMessage(msg);

        }
        return ret;
    }

    @GetMapping("/logout")
    public Object logout() {
        BaseResponse<Object> ret = new BaseResponse<Object>();
        ret.setCode(0);
        ret.setMessage("退出登录");
        return ret;
    }

    @GetMapping("/verifycode")
    public Object getGerifyCode(HttpServletRequest request, HttpServletResponse response) {
        IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
        BaseResponse<Object> ret = new BaseResponse<Object>();

        try {
            //设置长宽
            VerifyCode verifyCode = iVerifyCodeGen.generate(80, 30);
            String code = verifyCode.getCode();

            //设置响应头
            response.setHeader("Pragma", "no-cache");
            //设置响应头
            response.setHeader("Cache-Control", "no-cache");
            //在代理服务器端防止缓冲
            response.setDateHeader("Expires", 0);
            ret.setCode(0);
            ret.setMessage("退出登录");
            ret.setData(verifyCode);
        } catch (IOException e) {
            ret.setCode(-1);
            ret.setMessage("获取验证码失败");
            System.out.println(e);
        }

        return ret;
    }
}
