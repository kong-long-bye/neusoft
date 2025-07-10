package com.neusoft.medical.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

/**
 * 分页响应结果封装类
 * @author Neusoft
 * @date 2025-07-10
 */
@ApiModel(description = "分页响应结果")
public class PageResult<T> {

    @ApiModelProperty(value = "总记录数", example = "100")
    private Long total;

    @ApiModelProperty(value = "当前页码", example = "1")
    private Integer pageNum;

    @ApiModelProperty(value = "每页记录数", example = "10")
    private Integer pageSize;

    @ApiModelProperty(value = "总页数", example = "10")
    private Integer pages;

    @ApiModelProperty(value = "数据列表")
    private List<T> list;

    public PageResult() {}

    public PageResult(Long total, Integer pageNum, Integer pageSize, List<T> list) {
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.list = list;
        // 计算总页数
        this.pages = (int) Math.ceil((double) total / pageSize);
    }

    /**
     * 创建分页结果
     */
    public static <T> PageResult<T> of(Long total, Integer pageNum, Integer pageSize, List<T> list) {
        return new PageResult<>(total, pageNum, pageSize, list);
    }

    /**
     * 创建空分页结果
     */
    public static <T> PageResult<T> empty(Integer pageNum, Integer pageSize) {
        return new PageResult<>(0L, pageNum, pageSize, null);
    }

    // Getter and Setter methods
    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
        // 重新计算总页数
        if (this.pageSize != null && this.pageSize > 0) {
            this.pages = (int) Math.ceil((double) total / this.pageSize);
        }
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        // 重新计算总页数
        if (this.total != null && pageSize > 0) {
            this.pages = (int) Math.ceil((double) this.total / pageSize);
        }
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "total=" + total +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", pages=" + pages +
                ", list=" + list +
                '}';
    }
}