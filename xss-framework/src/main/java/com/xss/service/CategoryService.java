package com.xss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xss.domain.ResponseResult;
import com.xss.domain.entity.Category;

public interface CategoryService extends IService<Category> {

    ResponseResult getCategory();
}
