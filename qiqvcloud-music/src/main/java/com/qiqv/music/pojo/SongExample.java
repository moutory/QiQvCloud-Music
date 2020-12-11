package com.qiqv.music.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SongExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SongExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSingerIdIsNull() {
            addCriterion("singer_id is null");
            return (Criteria) this;
        }

        public Criteria andSingerIdIsNotNull() {
            addCriterion("singer_id is not null");
            return (Criteria) this;
        }

        public Criteria andSingerIdEqualTo(Integer value) {
            addCriterion("singer_id =", value, "singerId");
            return (Criteria) this;
        }

        public Criteria andSingerIdNotEqualTo(Integer value) {
            addCriterion("singer_id <>", value, "singerId");
            return (Criteria) this;
        }

        public Criteria andSingerIdGreaterThan(Integer value) {
            addCriterion("singer_id >", value, "singerId");
            return (Criteria) this;
        }

        public Criteria andSingerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("singer_id >=", value, "singerId");
            return (Criteria) this;
        }

        public Criteria andSingerIdLessThan(Integer value) {
            addCriterion("singer_id <", value, "singerId");
            return (Criteria) this;
        }

        public Criteria andSingerIdLessThanOrEqualTo(Integer value) {
            addCriterion("singer_id <=", value, "singerId");
            return (Criteria) this;
        }

        public Criteria andSingerIdIn(List<Integer> values) {
            addCriterion("singer_id in", values, "singerId");
            return (Criteria) this;
        }

        public Criteria andSingerIdNotIn(List<Integer> values) {
            addCriterion("singer_id not in", values, "singerId");
            return (Criteria) this;
        }

        public Criteria andSingerIdBetween(Integer value1, Integer value2) {
            addCriterion("singer_id between", value1, value2, "singerId");
            return (Criteria) this;
        }

        public Criteria andSingerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("singer_id not between", value1, value2, "singerId");
            return (Criteria) this;
        }

        public Criteria andSongNameIsNull() {
            addCriterion("song_name is null");
            return (Criteria) this;
        }

        public Criteria andSongNameIsNotNull() {
            addCriterion("song_name is not null");
            return (Criteria) this;
        }

        public Criteria andSongNameEqualTo(String value) {
            addCriterion("song_name =", value, "songName");
            return (Criteria) this;
        }

        public Criteria andSongNameNotEqualTo(String value) {
            addCriterion("song_name <>", value, "songName");
            return (Criteria) this;
        }

        public Criteria andSongNameGreaterThan(String value) {
            addCriterion("song_name >", value, "songName");
            return (Criteria) this;
        }

        public Criteria andSongNameGreaterThanOrEqualTo(String value) {
            addCriterion("song_name >=", value, "songName");
            return (Criteria) this;
        }

        public Criteria andSongNameLessThan(String value) {
            addCriterion("song_name <", value, "songName");
            return (Criteria) this;
        }

        public Criteria andSongNameLessThanOrEqualTo(String value) {
            addCriterion("song_name <=", value, "songName");
            return (Criteria) this;
        }

        public Criteria andSongNameLike(String value) {
            addCriterion("song_name like", value, "songName");
            return (Criteria) this;
        }

        public Criteria andSongNameNotLike(String value) {
            addCriterion("song_name not like", value, "songName");
            return (Criteria) this;
        }

        public Criteria andSongNameIn(List<String> values) {
            addCriterion("song_name in", values, "songName");
            return (Criteria) this;
        }

        public Criteria andSongNameNotIn(List<String> values) {
            addCriterion("song_name not in", values, "songName");
            return (Criteria) this;
        }

        public Criteria andSongNameBetween(String value1, String value2) {
            addCriterion("song_name between", value1, value2, "songName");
            return (Criteria) this;
        }

        public Criteria andSongNameNotBetween(String value1, String value2) {
            addCriterion("song_name not between", value1, value2, "songName");
            return (Criteria) this;
        }

        public Criteria andSongPicIsNull() {
            addCriterion("song_pic is null");
            return (Criteria) this;
        }

        public Criteria andSongPicIsNotNull() {
            addCriterion("song_pic is not null");
            return (Criteria) this;
        }

        public Criteria andSongPicEqualTo(String value) {
            addCriterion("song_pic =", value, "songPic");
            return (Criteria) this;
        }

        public Criteria andSongPicNotEqualTo(String value) {
            addCriterion("song_pic <>", value, "songPic");
            return (Criteria) this;
        }

        public Criteria andSongPicGreaterThan(String value) {
            addCriterion("song_pic >", value, "songPic");
            return (Criteria) this;
        }

        public Criteria andSongPicGreaterThanOrEqualTo(String value) {
            addCriterion("song_pic >=", value, "songPic");
            return (Criteria) this;
        }

        public Criteria andSongPicLessThan(String value) {
            addCriterion("song_pic <", value, "songPic");
            return (Criteria) this;
        }

        public Criteria andSongPicLessThanOrEqualTo(String value) {
            addCriterion("song_pic <=", value, "songPic");
            return (Criteria) this;
        }

        public Criteria andSongPicLike(String value) {
            addCriterion("song_pic like", value, "songPic");
            return (Criteria) this;
        }

        public Criteria andSongPicNotLike(String value) {
            addCriterion("song_pic not like", value, "songPic");
            return (Criteria) this;
        }

        public Criteria andSongPicIn(List<String> values) {
            addCriterion("song_pic in", values, "songPic");
            return (Criteria) this;
        }

        public Criteria andSongPicNotIn(List<String> values) {
            addCriterion("song_pic not in", values, "songPic");
            return (Criteria) this;
        }

        public Criteria andSongPicBetween(String value1, String value2) {
            addCriterion("song_pic between", value1, value2, "songPic");
            return (Criteria) this;
        }

        public Criteria andSongPicNotBetween(String value1, String value2) {
            addCriterion("song_pic not between", value1, value2, "songPic");
            return (Criteria) this;
        }

        public Criteria andSongUrlIsNull() {
            addCriterion("song_url is null");
            return (Criteria) this;
        }

        public Criteria andSongUrlIsNotNull() {
            addCriterion("song_url is not null");
            return (Criteria) this;
        }

        public Criteria andSongUrlEqualTo(String value) {
            addCriterion("song_url =", value, "songUrl");
            return (Criteria) this;
        }

        public Criteria andSongUrlNotEqualTo(String value) {
            addCriterion("song_url <>", value, "songUrl");
            return (Criteria) this;
        }

        public Criteria andSongUrlGreaterThan(String value) {
            addCriterion("song_url >", value, "songUrl");
            return (Criteria) this;
        }

        public Criteria andSongUrlGreaterThanOrEqualTo(String value) {
            addCriterion("song_url >=", value, "songUrl");
            return (Criteria) this;
        }

        public Criteria andSongUrlLessThan(String value) {
            addCriterion("song_url <", value, "songUrl");
            return (Criteria) this;
        }

        public Criteria andSongUrlLessThanOrEqualTo(String value) {
            addCriterion("song_url <=", value, "songUrl");
            return (Criteria) this;
        }

        public Criteria andSongUrlLike(String value) {
            addCriterion("song_url like", value, "songUrl");
            return (Criteria) this;
        }

        public Criteria andSongUrlNotLike(String value) {
            addCriterion("song_url not like", value, "songUrl");
            return (Criteria) this;
        }

        public Criteria andSongUrlIn(List<String> values) {
            addCriterion("song_url in", values, "songUrl");
            return (Criteria) this;
        }

        public Criteria andSongUrlNotIn(List<String> values) {
            addCriterion("song_url not in", values, "songUrl");
            return (Criteria) this;
        }

        public Criteria andSongUrlBetween(String value1, String value2) {
            addCriterion("song_url between", value1, value2, "songUrl");
            return (Criteria) this;
        }

        public Criteria andSongUrlNotBetween(String value1, String value2) {
            addCriterion("song_url not between", value1, value2, "songUrl");
            return (Criteria) this;
        }

        public Criteria andSongLyricIsNull() {
            addCriterion("song_lyric is null");
            return (Criteria) this;
        }

        public Criteria andSongLyricIsNotNull() {
            addCriterion("song_lyric is not null");
            return (Criteria) this;
        }

        public Criteria andSongLyricEqualTo(String value) {
            addCriterion("song_lyric =", value, "songLyric");
            return (Criteria) this;
        }

        public Criteria andSongLyricNotEqualTo(String value) {
            addCriterion("song_lyric <>", value, "songLyric");
            return (Criteria) this;
        }

        public Criteria andSongLyricGreaterThan(String value) {
            addCriterion("song_lyric >", value, "songLyric");
            return (Criteria) this;
        }

        public Criteria andSongLyricGreaterThanOrEqualTo(String value) {
            addCriterion("song_lyric >=", value, "songLyric");
            return (Criteria) this;
        }

        public Criteria andSongLyricLessThan(String value) {
            addCriterion("song_lyric <", value, "songLyric");
            return (Criteria) this;
        }

        public Criteria andSongLyricLessThanOrEqualTo(String value) {
            addCriterion("song_lyric <=", value, "songLyric");
            return (Criteria) this;
        }

        public Criteria andSongLyricLike(String value) {
            addCriterion("song_lyric like", value, "songLyric");
            return (Criteria) this;
        }

        public Criteria andSongLyricNotLike(String value) {
            addCriterion("song_lyric not like", value, "songLyric");
            return (Criteria) this;
        }

        public Criteria andSongLyricIn(List<String> values) {
            addCriterion("song_lyric in", values, "songLyric");
            return (Criteria) this;
        }

        public Criteria andSongLyricNotIn(List<String> values) {
            addCriterion("song_lyric not in", values, "songLyric");
            return (Criteria) this;
        }

        public Criteria andSongLyricBetween(String value1, String value2) {
            addCriterion("song_lyric between", value1, value2, "songLyric");
            return (Criteria) this;
        }

        public Criteria andSongLyricNotBetween(String value1, String value2) {
            addCriterion("song_lyric not between", value1, value2, "songLyric");
            return (Criteria) this;
        }

        public Criteria andSongIntroductionIsNull() {
            addCriterion("song_introduction is null");
            return (Criteria) this;
        }

        public Criteria andSongIntroductionIsNotNull() {
            addCriterion("song_introduction is not null");
            return (Criteria) this;
        }

        public Criteria andSongIntroductionEqualTo(String value) {
            addCriterion("song_introduction =", value, "songIntroduction");
            return (Criteria) this;
        }

        public Criteria andSongIntroductionNotEqualTo(String value) {
            addCriterion("song_introduction <>", value, "songIntroduction");
            return (Criteria) this;
        }

        public Criteria andSongIntroductionGreaterThan(String value) {
            addCriterion("song_introduction >", value, "songIntroduction");
            return (Criteria) this;
        }

        public Criteria andSongIntroductionGreaterThanOrEqualTo(String value) {
            addCriterion("song_introduction >=", value, "songIntroduction");
            return (Criteria) this;
        }

        public Criteria andSongIntroductionLessThan(String value) {
            addCriterion("song_introduction <", value, "songIntroduction");
            return (Criteria) this;
        }

        public Criteria andSongIntroductionLessThanOrEqualTo(String value) {
            addCriterion("song_introduction <=", value, "songIntroduction");
            return (Criteria) this;
        }

        public Criteria andSongIntroductionLike(String value) {
            addCriterion("song_introduction like", value, "songIntroduction");
            return (Criteria) this;
        }

        public Criteria andSongIntroductionNotLike(String value) {
            addCriterion("song_introduction not like", value, "songIntroduction");
            return (Criteria) this;
        }

        public Criteria andSongIntroductionIn(List<String> values) {
            addCriterion("song_introduction in", values, "songIntroduction");
            return (Criteria) this;
        }

        public Criteria andSongIntroductionNotIn(List<String> values) {
            addCriterion("song_introduction not in", values, "songIntroduction");
            return (Criteria) this;
        }

        public Criteria andSongIntroductionBetween(String value1, String value2) {
            addCriterion("song_introduction between", value1, value2, "songIntroduction");
            return (Criteria) this;
        }

        public Criteria andSongIntroductionNotBetween(String value1, String value2) {
            addCriterion("song_introduction not between", value1, value2, "songIntroduction");
            return (Criteria) this;
        }

        public Criteria andInsertAtIsNull() {
            addCriterion("insert_at is null");
            return (Criteria) this;
        }

        public Criteria andInsertAtIsNotNull() {
            addCriterion("insert_at is not null");
            return (Criteria) this;
        }

        public Criteria andInsertAtEqualTo(Date value) {
            addCriterion("insert_at =", value, "insertAt");
            return (Criteria) this;
        }

        public Criteria andInsertAtNotEqualTo(Date value) {
            addCriterion("insert_at <>", value, "insertAt");
            return (Criteria) this;
        }

        public Criteria andInsertAtGreaterThan(Date value) {
            addCriterion("insert_at >", value, "insertAt");
            return (Criteria) this;
        }

        public Criteria andInsertAtGreaterThanOrEqualTo(Date value) {
            addCriterion("insert_at >=", value, "insertAt");
            return (Criteria) this;
        }

        public Criteria andInsertAtLessThan(Date value) {
            addCriterion("insert_at <", value, "insertAt");
            return (Criteria) this;
        }

        public Criteria andInsertAtLessThanOrEqualTo(Date value) {
            addCriterion("insert_at <=", value, "insertAt");
            return (Criteria) this;
        }

        public Criteria andInsertAtIn(List<Date> values) {
            addCriterion("insert_at in", values, "insertAt");
            return (Criteria) this;
        }

        public Criteria andInsertAtNotIn(List<Date> values) {
            addCriterion("insert_at not in", values, "insertAt");
            return (Criteria) this;
        }

        public Criteria andInsertAtBetween(Date value1, Date value2) {
            addCriterion("insert_at between", value1, value2, "insertAt");
            return (Criteria) this;
        }

        public Criteria andInsertAtNotBetween(Date value1, Date value2) {
            addCriterion("insert_at not between", value1, value2, "insertAt");
            return (Criteria) this;
        }

        public Criteria andInsertByIsNull() {
            addCriterion("insert_by is null");
            return (Criteria) this;
        }

        public Criteria andInsertByIsNotNull() {
            addCriterion("insert_by is not null");
            return (Criteria) this;
        }

        public Criteria andInsertByEqualTo(String value) {
            addCriterion("insert_by =", value, "insertBy");
            return (Criteria) this;
        }

        public Criteria andInsertByNotEqualTo(String value) {
            addCriterion("insert_by <>", value, "insertBy");
            return (Criteria) this;
        }

        public Criteria andInsertByGreaterThan(String value) {
            addCriterion("insert_by >", value, "insertBy");
            return (Criteria) this;
        }

        public Criteria andInsertByGreaterThanOrEqualTo(String value) {
            addCriterion("insert_by >=", value, "insertBy");
            return (Criteria) this;
        }

        public Criteria andInsertByLessThan(String value) {
            addCriterion("insert_by <", value, "insertBy");
            return (Criteria) this;
        }

        public Criteria andInsertByLessThanOrEqualTo(String value) {
            addCriterion("insert_by <=", value, "insertBy");
            return (Criteria) this;
        }

        public Criteria andInsertByLike(String value) {
            addCriterion("insert_by like", value, "insertBy");
            return (Criteria) this;
        }

        public Criteria andInsertByNotLike(String value) {
            addCriterion("insert_by not like", value, "insertBy");
            return (Criteria) this;
        }

        public Criteria andInsertByIn(List<String> values) {
            addCriterion("insert_by in", values, "insertBy");
            return (Criteria) this;
        }

        public Criteria andInsertByNotIn(List<String> values) {
            addCriterion("insert_by not in", values, "insertBy");
            return (Criteria) this;
        }

        public Criteria andInsertByBetween(String value1, String value2) {
            addCriterion("insert_by between", value1, value2, "insertBy");
            return (Criteria) this;
        }

        public Criteria andInsertByNotBetween(String value1, String value2) {
            addCriterion("insert_by not between", value1, value2, "insertBy");
            return (Criteria) this;
        }

        public Criteria andUpdateAtIsNull() {
            addCriterion("update_at is null");
            return (Criteria) this;
        }

        public Criteria andUpdateAtIsNotNull() {
            addCriterion("update_at is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateAtEqualTo(Date value) {
            addCriterion("update_at =", value, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtNotEqualTo(Date value) {
            addCriterion("update_at <>", value, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtGreaterThan(Date value) {
            addCriterion("update_at >", value, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtGreaterThanOrEqualTo(Date value) {
            addCriterion("update_at >=", value, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtLessThan(Date value) {
            addCriterion("update_at <", value, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtLessThanOrEqualTo(Date value) {
            addCriterion("update_at <=", value, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtIn(List<Date> values) {
            addCriterion("update_at in", values, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtNotIn(List<Date> values) {
            addCriterion("update_at not in", values, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtBetween(Date value1, Date value2) {
            addCriterion("update_at between", value1, value2, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtNotBetween(Date value1, Date value2) {
            addCriterion("update_at not between", value1, value2, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("update_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("update_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(String value) {
            addCriterion("update_by =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(String value) {
            addCriterion("update_by <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(String value) {
            addCriterion("update_by >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("update_by >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(String value) {
            addCriterion("update_by <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(String value) {
            addCriterion("update_by <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLike(String value) {
            addCriterion("update_by like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotLike(String value) {
            addCriterion("update_by not like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<String> values) {
            addCriterion("update_by in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<String> values) {
            addCriterion("update_by not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(String value1, String value2) {
            addCriterion("update_by between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(String value1, String value2) {
            addCriterion("update_by not between", value1, value2, "updateBy");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}