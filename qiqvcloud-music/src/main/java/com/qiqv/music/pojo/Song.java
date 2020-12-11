package com.qiqv.music.pojo;

import java.util.Date;

public class Song {
    private Integer id;

    private Integer singerId;

    private String songName;

    private String songPic;

    private String songUrl;

    private String songLyric;

    private String songIntroduction;

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

    public Integer getSingerId() {
        return singerId;
    }

    public void setSingerId(Integer singerId) {
        this.singerId = singerId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName == null ? null : songName.trim();
    }

    public String getSongPic() {
        return songPic;
    }

    public void setSongPic(String songPic) {
        this.songPic = songPic == null ? null : songPic.trim();
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl == null ? null : songUrl.trim();
    }

    public String getSongLyric() {
        return songLyric;
    }

    public void setSongLyric(String songLyric) {
        this.songLyric = songLyric == null ? null : songLyric.trim();
    }

    public String getSongIntroduction() {
        return songIntroduction;
    }

    public void setSongIntroduction(String songIntroduction) {
        this.songIntroduction = songIntroduction == null ? null : songIntroduction.trim();
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
