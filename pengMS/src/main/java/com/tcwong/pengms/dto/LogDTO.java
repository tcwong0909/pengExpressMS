package com.tcwong.pengms.dto;

/**
 * Description
 *
 * @author tcwong
 * @date 2020/8/24
 * Since 1.8
 */
public class LogDTO {

    private String operationType;

    private String description;

    private String params;

    private String ip;

    private Byte exceptionFlag;

    private String exceptionError;

    public LogDTO() {
    }

    public LogDTO(String operationType, String description, String params, String ip, Byte exceptionFlag, String exceptionError) {
        this.operationType = operationType;
        this.description = description;
        this.params = params;
        this.ip = ip;
        this.exceptionFlag = exceptionFlag;
        this.exceptionError = exceptionError;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Byte getExceptionFlag() {
        return exceptionFlag;
    }

    public void setExceptionFlag(Byte exceptionFlag) {
        this.exceptionFlag = exceptionFlag;
    }

    public String getExceptionError() {
        return exceptionError;
    }

    public void setExceptionError(String exceptionError) {
        this.exceptionError = exceptionError;
    }

    @Override
    public String toString() {
        return "LogDTO{" +
                "operationType='" + operationType + '\'' +
                ", description='" + description + '\'' +
                ", params='" + params + '\'' +
                ", ip='" + ip + '\'' +
                ", exceptionFlag=" + exceptionFlag +
                ", exceptionError='" + exceptionError + '\'' +
                '}';
    }
}
