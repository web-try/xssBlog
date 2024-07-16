package com.xss.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//封装返回的登录校验信息
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogUserLoginVo {

    //用加密生成的token
    private String token;
    //返回的具体信息
    private UserInfoVo userInfoVo;
}
