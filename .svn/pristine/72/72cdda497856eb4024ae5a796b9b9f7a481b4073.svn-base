package com.website.common.page;

import java.util.List;

/**
 * 分页封装
 *
 * @author yanqb
 * @Date 2018年11月22日 下午11:06:41
 */
public class PageInfo<T> {

    // 当前页
    private Integer page = 1;

    // 页大小
    private Integer limit = 10;

    // 总记录 数
    private Integer totalRows;

    // 总页数
    private Integer totalPage;

    // 查询关键字
    private String keyWord;

    // 结果集
    private List<T> rows;


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public PageInfo() {}

    public PageInfo(Integer page, Integer limit){
        this.page = page;
        this.limit = limit;
    }

}

