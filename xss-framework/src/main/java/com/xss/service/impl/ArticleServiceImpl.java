package com.xss.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xss.domain.entity.Article;
import com.xss.mapper.ArticleMapper;
import com.xss.service.ArticleService;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

}
