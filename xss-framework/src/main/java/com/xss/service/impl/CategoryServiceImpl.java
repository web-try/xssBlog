package com.xss.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xss.domain.ResponseResult;
import com.xss.domain.constants.SystemConstants;
import com.xss.domain.entity.Article;
import com.xss.domain.entity.Category;
import com.xss.domain.vo.CateGoryVo;
import com.xss.mapper.CategoryMapper;
import com.xss.service.ArticleService;
import com.xss.service.CategoryService;
import com.xss.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ArticleService articleService;

    /*
     * 先去article根据status查出有正式文章的所有数据用steam流转为ID集合
     * 再根据找出来的ID去Category取到数据
     * 然后用Steam流筛出有没有被禁用的分类的数据
     */
    @Override
    public ResponseResult getCategory() {

        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> list = articleService.list(lambdaQueryWrapper);
        Set<Long> collect = list.stream()
                .map(Article -> Article.getCategoryId())
                .collect(Collectors.toSet());

        List<Category> categories = listByIds(collect);
        List<Category> collect1 = categories.stream()
                .filter(category -> SystemConstants.CATEGORY_STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());

        List<CateGoryVo> cateGoryVos = BeanCopyUtils.copyBeanList(collect1, CateGoryVo.class);
        return ResponseResult.okResult(cateGoryVos);
    }
}
