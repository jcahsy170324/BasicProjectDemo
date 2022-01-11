package com.lg.bsp.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName MyPageInfo
 * @Description 对PageInfo中信息进行封装，把关键信息返给前端
 * @Author jincheng
 * @Date 2022/1/7 9:14
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyPageInfo<T> implements Serializable {
    private Integer pageNum;
    private Integer pageSize;
    private Integer size;
    private Long total;
    private Integer pages;
    private List<T> list;

    public MyPageInfo(Integer pageNum, Integer pageSize, Long total,Integer pages,List<T> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.size = list.size();
        this.total = total;
        this.pages = pages;
        this.list = list;
    }
}
