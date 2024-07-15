package com.xss.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* 分类表
* xss_category
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("xss_category")
public class Category {

    @TableId
    private Long id;

    private String name;

    private Long pid;

    private String description;
    /**
    * 状态0:正常,1禁用
    */
    private String status;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;
    /**
    * 删除标志（0代表未删除，1代表已删除）
    */
    private Integer delFlag;

}
