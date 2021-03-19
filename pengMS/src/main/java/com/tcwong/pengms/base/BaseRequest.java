package com.tcwong.pengms.base;

/**
 * Description 请求分页
 *
 * @author tcwong
 * @date 2020/8/22 Since 1.8
 */
public class BaseRequest {
    private int pageNum = 0;
    private int pageSize = 10;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
