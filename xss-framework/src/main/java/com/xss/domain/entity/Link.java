package com.xss.domain.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
* 友链
*/
@TableName("xss_link")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Link {

    private Long id;

    private String name;

    private String logo;

    private String description;
    /**
    * 网站地址
    */
    private String address;
    /**
    * 审核状态 (0代表审核通过，1代表审核未通过，2代表未审核)
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
