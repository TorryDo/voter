package com.newhope.trido.voter.model;

public class cmt_home {

    private String mkey;
    private String mupload;
    private String mtext;
    private String mtime;
    private String mlike;
    private String mrep;

    public cmt_home() {
    }

    public cmt_home(String mkey, String mtext, String mtime, String mlike, String mrep,String mupload) {
        this.mkey = mkey;
        this.mtext = mtext;
        this.mtime = mtime;
        this.mlike = mlike;
        this.mrep = mrep;
        this.mupload = mupload;
    }

    public cmt_home(String mupload, String mtext, String mtime, String mlike, String mrep) {
        this.mupload = mupload;
        this.mtext = mtext;
        this.mtime = mtime;
        this.mlike = mlike;
        this.mrep = mrep;
    }

    public cmt_home(String mkey, String mtext, String mupload ) {
        this.mkey = mkey;
        this.mupload = mupload;
        this.mtext = mtext;
    }



    public String getMupload() {
        return mupload;
    }

    public void setMupload(String mupload) {
        this.mupload = mupload;
    }

    public String getMkey() {
        return mkey;
    }

    public void setMkey(String mkey) {
        this.mkey = mkey;
    }

    public String getMtext() {
        return mtext;
    }

    public void setMtext(String mtext) {
        this.mtext = mtext;
    }

    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }

    public String getMlike() {
        return mlike;
    }

    public void setMlike(String mlike) {
        this.mlike = mlike;
    }

    public String getMrep() {
        return mrep;
    }

    public void setMrep(String mcmt) {
        this.mrep = mrep;
    }
}
