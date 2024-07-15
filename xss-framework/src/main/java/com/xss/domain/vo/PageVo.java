package com.xss.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//分页查询文章列表返回数据封装
@Data
@AllArgsConstructor
@NoArgsConstructor
public class  PageVo {

    private List rows;

    private Long total;
}
