package com.xss.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xss.domain.entity.User;
import com.xss.mapper.UserMapper;
import com.xss.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
