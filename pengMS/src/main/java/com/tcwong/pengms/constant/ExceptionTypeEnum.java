package com.tcwong.pengms.constant;

/**
 * Description 异常类型
 *
 * @author tcwong
 * @date 2020/8/31 Since 1.8
 */
public enum ExceptionTypeEnum {
    NORMAL((byte) 0, "正常"),
    EXCEPTION((byte) 1, "异常");

    ExceptionTypeEnum(Byte type, String message) {
        this.type = type;
        this.message = message;
    }

    private Byte type;
    private String message;

    public Byte getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
