package com.yuexun.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @apiNote 分页结果封装
 * @author zzd
 * @date 2023/5/9 13:57
 */
@Data
@NoArgsConstructor
public class PageHelper<T> {

    @ApiModelProperty(value = "总数")
    private Long totalCount;

    @ApiModelProperty(value = "总页数")
    private Long totalPage;

    @ApiModelProperty(value = "当前页")
    private Long pageNum;

    @ApiModelProperty(value = "页面大小")
    private Long pageSize;

    @ApiModelProperty(value = "返回数据")
    private List<T> list;

    public PageHelper(Long totalCount, Long totalPage, Long pageNum, Long pageSize, List<T> list) {
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.list = list;
    }

    public static Long calculateTotalPages(Long total, Long size) {
        return total % size == 0 ? total / size : total / size + 1;
    }

    public static <T> PageHelper<T> restPage(IPage<T> pageResult) {
        Long totalPage = calculateTotalPages(pageResult.getTotal(), pageResult.getSize());
        PageHelper<T> result = new PageHelper<>(pageResult.getCurrent(), pageResult.getSize(), pageResult.getTotal(), totalPage, pageResult.getRecords());
        return result;
    }

    public static <T> PageHelper<T> restPage(IPage<T> pageResult, List<T> list) {
        Long totalPage = calculateTotalPages(pageResult.getTotal(), pageResult.getSize());
        PageHelper<T> result = new PageHelper<>(pageResult.getCurrent(), pageResult.getSize(), pageResult.getTotal(), totalPage, list);
        return result;
    }


}
