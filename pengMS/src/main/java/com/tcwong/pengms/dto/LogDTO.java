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


    public LogDTO() {
    }

    public LogDTO(String operationType, String description, String params, String ip) {
        this.operationType = operationType;
        this.description = description;
        this.params = params;
        this.ip = ip;
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

    @Override
    public String toString() {
        return "LogDTO{" +
                "operationType='" + operationType + '\'' +
                ", description='" + description + '\'' +
                ", params='" + params + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}
