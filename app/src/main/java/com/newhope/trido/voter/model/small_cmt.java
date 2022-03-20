package com.newhope.trido.voter.model;

public class small_cmt {
    private String msid;
    private String mskey;
    private String mstime;
    private String mslike;
    private String mstraloi;

    public small_cmt(String msid, String mskey, String mstime, String mslike, String mstraloi) {
        this.msid = msid;

        this.mskey = mskey;
        this.mstime = mstime;
        this.mslike = mslike;
        this.mstraloi = mstraloi;
    }

    public small_cmt(String mstraloi) {
        this.mstraloi = mstraloi;
    }

    public small_cmt(String msid, String mstime, String mslike, String mstraloi) {
        this.msid=msid;
        this.mstime = mstime;
        this.mslike = mslike;
        this.mstraloi = mstraloi;
    }

    public String getMsid() {
        return msid;
    }

    public void setMsid(String msid) {
        this.msid = msid;
    }

    public String getMskey() {
        return mskey;
    }

    public void setMskey(String mskey) {
        this.mskey = mskey;
    }

    public String getMstime() {
        return mstime;
    }

    public void setMstime(String mstime) {
        this.mstime = mstime;
    }

    public String getMslike() {
        return mslike;
    }

    public void setMslike(String mslike) {
        this.mslike = mslike;
    }

    public String getMstraloi() {
        return mstraloi;
    }

    public void setMstraloi(String mstraloi) {
        this.mstraloi = mstraloi;
    }
}
