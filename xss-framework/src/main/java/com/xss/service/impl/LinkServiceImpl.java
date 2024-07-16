package com.xss.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xss.domain.ResponseResult;
import com.xss.domain.constants.SystemConstants;
import com.xss.domain.entity.Link;
import com.xss.domain.vo.LinkVo;
import com.xss.mapper.LinkMapper;
import com.xss.service.LinkService;
import com.xss.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    /**
     * 友情链接的内容的实现
     */
    @Override
    public ResponseResult getAllLink() {
        //查询所有审核通过的
        LambdaQueryWrapper<Link> lambdaWrapper = new LambdaQueryWrapper();
        lambdaWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);

        List<Link> list = list(lambdaWrapper);
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(list, LinkVo.class);
        return ResponseResult.okResult(linkVos);
    }
}
