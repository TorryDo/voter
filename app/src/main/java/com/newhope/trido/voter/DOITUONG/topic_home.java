package com.newhope.trido.voter.DOITUONG;

public class topic_home {
    private int id;
    private String mpicture;
    private String mtieude;
    private String mlike;
    private int mcomment;
    private String keys;
    private boolean save;

    public topic_home(String mtieude, String mpicture, String mlike, int mcomment,String keys, boolean save) {
        this.mpicture = mpicture;
        this.mlike = mlike;
        this.mcomment = mcomment;
        this.mtieude=mtieude;
        this.save = save;
        this.keys=keys;
    }

    public topic_home(String mpicture,String keys) {
        this.mpicture = mpicture;
        this.keys=keys;
    }

    public topic_home(int id, String mtieude, String mpicture, String mlike, int mcomment,String keys, boolean save) {
        this.id = id;
        this.mpicture = mpicture;
        this.mtieude = mtieude;
        this.mlike = mlike;
        this.mcomment = mcomment;
        this.save = save;
        this.keys=keys;
    }

    public topic_home() {
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMten() {
        return mtieude;
    }

    public void setMten(String mten) {
        this.mtieude = mtieude;
    }

    public String getMpicture() {
        return mpicture;
    }

    public void setMpicture(String mpicture) {
        this.mpicture = mpicture;
    }

    public String getMlike() {
        return mlike;
    }

    public void setMlike(String mlike) {
        this.mlike = mlike;
    }

    public int getMcomment() {
        return mcomment;
    }

    public void setMcomment(int mcomment) {
        this.mcomment = mcomment;
    }

    public boolean isSave() {
        return save;
    }

    public void setSave(boolean save) {
        this.save = save;
    }
}
