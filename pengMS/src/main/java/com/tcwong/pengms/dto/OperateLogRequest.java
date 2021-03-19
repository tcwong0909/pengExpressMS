package com.tcwong.pengms.dto;

import com.tcwong.pengms.base.BaseRequest;

/**
 * Description 日志查询 请求
 *
 * @author tcwong
 * @date 2020/8/31 Since 1.8
 */
public class OperateLogRequest extends BaseRequest {
    private String operationType;
    private String paramsKeyWord;
    private Byte exceptionFlag;

    public OperateLogRequest() {}

    public OperateLogRequest(String operationType, String paramsKeyWord, Byte exceptionFlag) {
        this.operationType = operationType;
        this.paramsKeyWord = paramsKeyWord;
        this.exceptionFlag = exceptionFlag;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getParamsKeyWord() {
        return paramsKeyWord;
    }

    public void setParamsKeyWord(String paramsKeyWord) {
        this.paramsKeyWord = paramsKeyWord;
    }

    public Byte getExceptionFlag() {
        return exceptionFlag;
    }

    public void setExceptionFlag(Byte exceptionFlag) {
        this.exceptionFlag = exceptionFlag;
    }

    @Override
    public String toString() {
        return "OperateLogRequest{"
                + "operationType='"
                + operationType
                + '\''
                + ", paramsKeyWord='"
                + paramsKeyWord
                + '\''
                + ", exceptionFlag="
                + exceptionFlag
                + '}';
    }
}
