package com.hancoder.community.entity;

/*
封装分页相关的信息
 */


public class Page {
    //当前的页码
    private int current = 1;

    //显示的上限 最多显示多少数据
    private int limit = 10;

    //数据的总数 用于计算页数
    private int rows;

    //查询路径 复用分页的链接
    private String path;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if (current >= 1)
            this.current = current;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if (limit >= 1 && limit <= 100)
            this.limit = limit;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if (rows >= 0)
            this.rows = rows;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    //获取当页的起始行
    public int getOffSet(){
        return current * limit - limit;
    }

    public int getTotal(){
        return (rows % limit) == 0 ? rows / limit : rows / limit + 1;
    }

    // 获取起始页码
    public int getFrom(){
        return current - 2 > 1 ? current - 2 : 1;
    }

    // 获取结束页码
    public int getTo(){
        return current + 2 > getTotal() ? getTotal() : current + 2;
    }
}





