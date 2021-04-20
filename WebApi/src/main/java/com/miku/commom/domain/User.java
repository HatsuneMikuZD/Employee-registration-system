package com.miku.commom.domain;

/**
 * @author BEJSON
 * @description user
 * @date 2021-04-16
 */

public class User {

    /**
     * id
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 姓名
     */
    private String realname;
    /**
     * 1男2女
     */
    private int sex;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 微信号
     */
    private String webchat;
    /**
     * qq号
     */
    private String qq;
    /**
     * 手机
     */
    private String phone;
    /**
     * 电话
     */
    private String tel;
    /**
     * 地址
     */
    private String address;
    /**
     * 登录次数
     */
    private Integer lognum;
    /**
     * 最近登录ip
     */
    private String ip;
    /**
     * 最近登录时间
     */
    private Integer lasted;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebchat() {
        return webchat;
    }

    public void setWebchat(String webchat) {
        this.webchat = webchat;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getLognum() {
        return lognum;
    }

    public void setLognum(Integer lognum) {
        this.lognum = lognum;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getLasted() {
        return lasted;
    }

    public void setLasted(Integer lasted) {
        this.lasted = lasted;
    }
}