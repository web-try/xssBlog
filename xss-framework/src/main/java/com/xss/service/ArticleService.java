package com.xss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xss.domain.ResponseResult;
import com.xss.domain.entity.Article;

public interface ArticleService extends IService<Article> {

    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);
}
