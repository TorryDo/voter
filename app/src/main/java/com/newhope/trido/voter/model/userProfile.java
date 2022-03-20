package com.newhope.trido.voter.model;

public class userProfile {
    private String nickname;
    private String mail;
    private String sdt;

    private String realname;
    private String sex;
    private String place;
    private String avatar;
    private String background;

    public userProfile(String nickname, String mail, String sdt, String realname, String sex, String place, String avatar, String background) {
        this.nickname = nickname;
        this.mail = mail;
        this.sdt = sdt;
        this.realname = realname;
        this.sex = sex;
        this.place = place;
        this.avatar = avatar;
        this.background = background;
    }
    public userProfile() {
    }

    public userProfile(String nickname, String avatar) {
        this.nickname = nickname;
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }
}
