package com.xss.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xss.domain.ResponseResult;
import com.xss.domain.constants.SystemConstants;
import com.xss.domain.entity.Article;
import com.xss.domain.vo.HotArticleVo;
import com.xss.mapper.ArticleMapper;
import com.xss.service.ArticleService;
import com.xss.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    /**
     * 热门列表功能
     */
    @Override
    public ResponseResult hotArticleList() {

        // 查询条件，状态为已发布的
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        queryWrapper.orderByDesc(Article::getViewCount);

        // 分页查询
        Page<Article> articlePage = new Page<>(1,10);
        page(articlePage, queryWrapper);
        List<Article> records = articlePage.getRecords();
        List<HotArticleVo> hotArticleVos = BeanCopyUtils.copyBeanList(records, HotArticleVo.class);
        return ResponseResult.okResult(hotArticleVos);
    }
}
