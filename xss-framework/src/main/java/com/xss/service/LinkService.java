package com.xss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xss.domain.ResponseResult;
import com.xss.domain.entity.Link;

public interface LinkService extends IService<Link> {
    ResponseResult getAllLink();
}
