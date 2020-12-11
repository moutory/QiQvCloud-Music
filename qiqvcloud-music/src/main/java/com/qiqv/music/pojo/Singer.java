package com.qiqv.music.pojo;

import java.util.Date;

public class Singer {
    private Integer id;

    private String singerName;

    private Integer singerSex;

    private String singerPic;

    private Date singerBirth;

    private String singerLocaltion;

    private String singerIntroduction;

    private Date insertAt;

    private String insertBy;

    private Date updateAt;

    private String updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName == null ? null : singerName.trim();
    }

    public Integer getSingerSex() {
        return singerSex;
    }

    public void setSingerSex(Integer singerSex) {
        this.singerSex = singerSex;
    }

    public String getSingerPic() {
        return singerPic;
    }

    public void setSingerPic(String singerPic) {
        this.singerPic = singerPic == null ? null : singerPic.trim();
    }

    public Date getSingerBirth() {
        return singerBirth;
    }

    public void setSingerBirth(Date singerBirth) {
        this.singerBirth = singerBirth;
    }

    public String getSingerLocaltion() {
        return singerLocaltion;
    }

    public void setSingerLocaltion(String singerLocaltion) {
        this.singerLocaltion = singerLocaltion == null ? null : singerLocaltion.trim();
    }

    public String getSingerIntroduction() {
        return singerIntroduction;
    }

    public void setSingerIntroduction(String singerIntroduction) {
        this.singerIntroduction = singerIntroduction == null ? null : singerIntroduction.trim();
    }

    public Date getInsertAt() {
        return insertAt;
    }

    public void setInsertAt(Date insertAt) {
        this.insertAt = insertAt;
    }

    public String getInsertBy() {
        return insertBy;
    }

    public void setInsertBy(String insertBy) {
        this.insertBy = insertBy == null ? null : insertBy.trim();
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }
}
