package com.tcwong.pengms.constant;

/**
 * Description 操作类型
 *
 * @author tcwong
 * @date 2020/8/24
 * Since 1.8
 */
public enum LogOperationType {
    LOGIN("登录"),
    ADD("添加数据"),
    DELETE("删除数据"),
    UPDATE("更新数据"),
    OTHER("其他");

    private String description;

    LogOperationType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
