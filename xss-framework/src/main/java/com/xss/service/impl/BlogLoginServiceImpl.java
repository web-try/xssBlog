package com.xss.service.impl;

import com.xss.domain.ResponseResult;
import com.xss.domain.entity.LoginUser;
import com.xss.domain.entity.User;
import com.xss.domain.vo.BlogUserLoginVo;
import com.xss.domain.vo.UserInfoVo;
import com.xss.service.BlogLoginService;
import com.xss.utils.BeanCopyUtils;
import com.xss.utils.JwtUtil;
import com.xss.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

/*
 * 登录模块
 */
@Service
public class BlogLoginServiceImpl implements BlogLoginService {

    //注入需要调用SecurityConfig将authenticationManager放入spring中
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        // 创建一个UsernamePasswordAuthenticationToken对象，用于存储用户名和密码
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        /**
         * 使用authenticationManager对authenticationToken进行认证认证时
         * 会默认调用实现了UserDetailsService的UserDetailsServiceImpl中
         * 的方法在方法里进行校验逻辑处理验证结束之后会进行后续代码
         */
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //判断是否认证通过
        if(Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或者密码错误");
        }

        /**
        * 获取userid生成token这里因为UserDetailsServiceImpl实现类中方法返回的是
        * 实现了UserDetails这个接口的LoginUser所以可以进行强制转换
        */
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        //把用户信息存入redis
        String jwt = JwtUtil.createJWT(userId);
        redisCache.setCacheObject("bloglogin"+userId,loginUser);
        //把token和userinfo封装返回
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        BlogUserLoginVo blogUserLoginVo = new BlogUserLoginVo(jwt, userInfoVo);
        return ResponseResult.okResult(blogUserLoginVo);
    }
}
