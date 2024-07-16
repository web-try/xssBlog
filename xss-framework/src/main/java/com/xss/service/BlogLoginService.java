package com.xss.service;

import com.xss.domain.ResponseResult;
import com.xss.domain.entity.User;

public interface BlogLoginService {
    ResponseResult login(User user);
}
