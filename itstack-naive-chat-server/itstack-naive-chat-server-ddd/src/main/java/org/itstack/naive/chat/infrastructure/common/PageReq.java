package org.itstack.naive.chat.infrastructure.common;

import org.apache.commons.lang3.StringUtils;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 */
public class PageReq {

    private int pageStart = 0;  //开始 limit 第一个参数
    private int pageEnd = 0;    //结束 limit 第二个参数

    private int page;   //页数
    private int rows;   //行数

    public PageReq() {
    }

    public PageReq(String page, String rows) {
        this.page = StringUtils.isEmpty(page) ? 1 : Integer.parseInt(page);
        this.rows = StringUtils.isEmpty(page) ? 10 : Integer.parseInt(rows);
        if (0 == this.page) {
            this.page = 1;
        }
        this.pageStart = (this.page - 1) * this.rows;
        this.pageEnd = this.rows;
    }

    public void setPage(String page, String rows) {
        this.page = StringUtils.isEmpty(page) ? 1 : Integer.parseInt(page);
        this.rows = StringUtils.isEmpty(page) ? 10 : Integer.parseInt(rows);
        if (0 == this.page) {
            this.page = 1;
        }
        this.pageStart = (this.page - 1) * this.rows;
        this.pageEnd = this.rows;
    }

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public int getPageEnd() {
        return pageEnd;
    }

    public void setPageEnd(int pageEnd) {
        this.pageEnd = pageEnd;
    }

}
