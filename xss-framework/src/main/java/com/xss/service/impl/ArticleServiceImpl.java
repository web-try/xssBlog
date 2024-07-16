package com.xss.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xss.domain.ResponseResult;
import com.xss.domain.constants.SystemConstants;
import com.xss.domain.entity.Article;
import com.xss.domain.entity.Category;
import com.xss.domain.vo.ArticleDateVo;
import com.xss.domain.vo.ArticleListVo;
import com.xss.domain.vo.HotArticleVo;
import com.xss.domain.vo.PageVo;
import com.xss.mapper.ArticleMapper;
import com.xss.service.ArticleService;
import com.xss.service.CategoryService;
import com.xss.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;

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

    /**
     * 分页查询文章列表
     */
    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {

        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //判断有无getCategoryId如果有就加入这条查询条件没有则不加
        queryWrapper.eq(Objects.nonNull(categoryId)&& categoryId > 0,Article::getCategoryId, categoryId);
        //状态是正式发布的
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        //对istop降序
        queryWrapper.orderByDesc(Article::getIsTop);

        //分页查询
        Page<Article> articlePage = new Page<>(pageNum,pageSize);
        page(articlePage,queryWrapper);
        //获取数据根据ID从CateGoryVo表中找到CateGoryName
        List<Article> records = articlePage.getRecords();
        for (Article record : records) {
            record.setCategoryName(categoryService.getById(record.getCategoryId()).getName());
        }

        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(articlePage.getRecords(), ArticleListVo.class);

        //查询出来的结果封装到pageVo中
        PageVo pageVo = new PageVo(articleListVos, articlePage.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    /**
     * 文章详情
     */
    @Override
    public ResponseResult getArticleDetail(Long id) {

        Article byId = getById(id);
//        byId.setCategoryName(categoryService.getById(byId.getCategoryId()).getName());
        ArticleDateVo articleDateVo = BeanCopyUtils.copyBean(byId, ArticleDateVo.class);
        Long categoryId = articleDateVo.getCategoryId();
        Category category = categoryService.getById(categoryId);
        if(category != null) {
            articleDateVo.setCategoryName(category.getName());
        }
        return ResponseResult.okResult(articleDateVo);
    }
}
