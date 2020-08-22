package com.tcwong.pengms.dto;

/**
 * Description 登陆账户
 *
 * @author tcwong
 * @date 2020/8/22
 * Since 1.8
 */
public class LoginRequest {


    private String account;
    private String password;

    @Override
    public String toString() {
        return "LoginBean{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
