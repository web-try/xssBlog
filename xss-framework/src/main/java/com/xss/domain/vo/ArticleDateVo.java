package com.xss.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDateVo {

    @TableId
    private Long id;

    //标题
    private String title;

    //文章摘要
    private String summary;

    //所属分类Id
    private Long categoryId;

    //所属分类Name
    private String categoryName;

    //缩略图
    private String thumbnail;

    //内容
    private String content;

    private Long viewCount;

    private Date createTime;
}
