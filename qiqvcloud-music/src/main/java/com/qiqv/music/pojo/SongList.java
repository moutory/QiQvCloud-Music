package com.qiqv.music.pojo;

import java.util.Date;

public class SongList {
    private Integer id;

    private String songlistTitle;

    private String songlistPic;

    private String songlistIntroduction;

    private String songlistStyle;

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

    public String getSonglistTitle() {
        return songlistTitle;
    }

    public void setSonglistTitle(String songlistTitle) {
        this.songlistTitle = songlistTitle == null ? null : songlistTitle.trim();
    }

    public String getSonglistPic() {
        return songlistPic;
    }

    public void setSonglistPic(String songlistPic) {
        this.songlistPic = songlistPic == null ? null : songlistPic.trim();
    }

    public String getSonglistIntroduction() {
        return songlistIntroduction;
    }

    public void setSonglistIntroduction(String songlistIntroduction) {
        this.songlistIntroduction = songlistIntroduction == null ? null : songlistIntroduction.trim();
    }

    public String getSonglistStyle() {
        return songlistStyle;
    }

    public void setSonglistStyle(String songlistStyle) {
        this.songlistStyle = songlistStyle == null ? null : songlistStyle.trim();
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