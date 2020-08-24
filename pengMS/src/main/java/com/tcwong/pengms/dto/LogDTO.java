package com.tcwong.pengms.dto;

/**
 * Description
 *
 * @author tcwong
 * @date 2020/8/24
 * Since 1.8
 */
public class LogDTO {
    private String logInfo;

    public LogDTO(String logInfo) {
        this.logInfo = logInfo;
    }

    @Override
    public String toString() {
        return "LogDTO{" +
                "logInfo='" + logInfo + '\'' +
                '}';
    }
}
