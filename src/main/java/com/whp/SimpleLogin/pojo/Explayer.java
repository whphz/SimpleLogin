package com.whp.SimpleLogin.pojo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Explayer{
    Integer pid=null;
    String name;
    String nickname;
    String password;
    String pip;
    String lastlogin;
    String islogin;
    String token="";

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLastlogin() {
        return lastlogin;
    }

    public String getIslogin() {
        return islogin;
    }

    public void setIslogin(String islogin) {
        this.islogin = islogin;
    }
    public Explayer(String name, String nickname, String password, String pip,String lastlogin,String islogin) {
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.pip = pip;
        this.islogin=islogin;
    }

    public Explayer(String name, String nickname, String password, String pip,String islogin) {
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.pip = pip;
        this.islogin=islogin;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPip() {
        return pip;
    }

    public void setPip(String pip) {
        this.pip = pip;
    }

    public void setLastlogin() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());
        this.lastlogin=format;
    }

    @Override
    public String toString() {
        return "Explayer{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", pip='" + pip + '\'' +
                ", lastlogin='" + lastlogin + '\'' +
                ", islogin='" + islogin + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
