package com.qiqv.music.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SongListExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SongListExample() {
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

        public Criteria andSonglistTitleIsNull() {
            addCriterion("songList_title is null");
            return (Criteria) this;
        }

        public Criteria andSonglistTitleIsNotNull() {
            addCriterion("songList_title is not null");
            return (Criteria) this;
        }

        public Criteria andSonglistTitleEqualTo(String value) {
            addCriterion("songList_title =", value, "songlistTitle");
            return (Criteria) this;
        }

        public Criteria andSonglistTitleNotEqualTo(String value) {
            addCriterion("songList_title <>", value, "songlistTitle");
            return (Criteria) this;
        }

        public Criteria andSonglistTitleGreaterThan(String value) {
            addCriterion("songList_title >", value, "songlistTitle");
            return (Criteria) this;
        }

        public Criteria andSonglistTitleGreaterThanOrEqualTo(String value) {
            addCriterion("songList_title >=", value, "songlistTitle");
            return (Criteria) this;
        }

        public Criteria andSonglistTitleLessThan(String value) {
            addCriterion("songList_title <", value, "songlistTitle");
            return (Criteria) this;
        }

        public Criteria andSonglistTitleLessThanOrEqualTo(String value) {
            addCriterion("songList_title <=", value, "songlistTitle");
            return (Criteria) this;
        }

        public Criteria andSonglistTitleLike(String value) {
            addCriterion("songList_title like", value, "songlistTitle");
            return (Criteria) this;
        }

        public Criteria andSonglistTitleNotLike(String value) {
            addCriterion("songList_title not like", value, "songlistTitle");
            return (Criteria) this;
        }

        public Criteria andSonglistTitleIn(List<String> values) {
            addCriterion("songList_title in", values, "songlistTitle");
            return (Criteria) this;
        }

        public Criteria andSonglistTitleNotIn(List<String> values) {
            addCriterion("songList_title not in", values, "songlistTitle");
            return (Criteria) this;
        }

        public Criteria andSonglistTitleBetween(String value1, String value2) {
            addCriterion("songList_title between", value1, value2, "songlistTitle");
            return (Criteria) this;
        }

        public Criteria andSonglistTitleNotBetween(String value1, String value2) {
            addCriterion("songList_title not between", value1, value2, "songlistTitle");
            return (Criteria) this;
        }

        public Criteria andSonglistPicIsNull() {
            addCriterion("songList_pic is null");
            return (Criteria) this;
        }

        public Criteria andSonglistPicIsNotNull() {
            addCriterion("songList_pic is not null");
            return (Criteria) this;
        }

        public Criteria andSonglistPicEqualTo(String value) {
            addCriterion("songList_pic =", value, "songlistPic");
            return (Criteria) this;
        }

        public Criteria andSonglistPicNotEqualTo(String value) {
            addCriterion("songList_pic <>", value, "songlistPic");
            return (Criteria) this;
        }

        public Criteria andSonglistPicGreaterThan(String value) {
            addCriterion("songList_pic >", value, "songlistPic");
            return (Criteria) this;
        }

        public Criteria andSonglistPicGreaterThanOrEqualTo(String value) {
            addCriterion("songList_pic >=", value, "songlistPic");
            return (Criteria) this;
        }

        public Criteria andSonglistPicLessThan(String value) {
            addCriterion("songList_pic <", value, "songlistPic");
            return (Criteria) this;
        }

        public Criteria andSonglistPicLessThanOrEqualTo(String value) {
            addCriterion("songList_pic <=", value, "songlistPic");
            return (Criteria) this;
        }

        public Criteria andSonglistPicLike(String value) {
            addCriterion("songList_pic like", value, "songlistPic");
            return (Criteria) this;
        }

        public Criteria andSonglistPicNotLike(String value) {
            addCriterion("songList_pic not like", value, "songlistPic");
            return (Criteria) this;
        }

        public Criteria andSonglistPicIn(List<String> values) {
            addCriterion("songList_pic in", values, "songlistPic");
            return (Criteria) this;
        }

        public Criteria andSonglistPicNotIn(List<String> values) {
            addCriterion("songList_pic not in", values, "songlistPic");
            return (Criteria) this;
        }

        public Criteria andSonglistPicBetween(String value1, String value2) {
            addCriterion("songList_pic between", value1, value2, "songlistPic");
            return (Criteria) this;
        }

        public Criteria andSonglistPicNotBetween(String value1, String value2) {
            addCriterion("songList_pic not between", value1, value2, "songlistPic");
            return (Criteria) this;
        }

        public Criteria andSonglistIntroductionIsNull() {
            addCriterion("songList_introduction is null");
            return (Criteria) this;
        }

        public Criteria andSonglistIntroductionIsNotNull() {
            addCriterion("songList_introduction is not null");
            return (Criteria) this;
        }

        public Criteria andSonglistIntroductionEqualTo(String value) {
            addCriterion("songList_introduction =", value, "songlistIntroduction");
            return (Criteria) this;
        }

        public Criteria andSonglistIntroductionNotEqualTo(String value) {
            addCriterion("songList_introduction <>", value, "songlistIntroduction");
            return (Criteria) this;
        }

        public Criteria andSonglistIntroductionGreaterThan(String value) {
            addCriterion("songList_introduction >", value, "songlistIntroduction");
            return (Criteria) this;
        }

        public Criteria andSonglistIntroductionGreaterThanOrEqualTo(String value) {
            addCriterion("songList_introduction >=", value, "songlistIntroduction");
            return (Criteria) this;
        }

        public Criteria andSonglistIntroductionLessThan(String value) {
            addCriterion("songList_introduction <", value, "songlistIntroduction");
            return (Criteria) this;
        }

        public Criteria andSonglistIntroductionLessThanOrEqualTo(String value) {
            addCriterion("songList_introduction <=", value, "songlistIntroduction");
            return (Criteria) this;
        }

        public Criteria andSonglistIntroductionLike(String value) {
            addCriterion("songList_introduction like", value, "songlistIntroduction");
            return (Criteria) this;
        }

        public Criteria andSonglistIntroductionNotLike(String value) {
            addCriterion("songList_introduction not like", value, "songlistIntroduction");
            return (Criteria) this;
        }

        public Criteria andSonglistIntroductionIn(List<String> values) {
            addCriterion("songList_introduction in", values, "songlistIntroduction");
            return (Criteria) this;
        }

        public Criteria andSonglistIntroductionNotIn(List<String> values) {
            addCriterion("songList_introduction not in", values, "songlistIntroduction");
            return (Criteria) this;
        }

        public Criteria andSonglistIntroductionBetween(String value1, String value2) {
            addCriterion("songList_introduction between", value1, value2, "songlistIntroduction");
            return (Criteria) this;
        }

        public Criteria andSonglistIntroductionNotBetween(String value1, String value2) {
            addCriterion("songList_introduction not between", value1, value2, "songlistIntroduction");
            return (Criteria) this;
        }

        public Criteria andSonglistStyleIsNull() {
            addCriterion("songList_style is null");
            return (Criteria) this;
        }

        public Criteria andSonglistStyleIsNotNull() {
            addCriterion("songList_style is not null");
            return (Criteria) this;
        }

        public Criteria andSonglistStyleEqualTo(String value) {
            addCriterion("songList_style =", value, "songlistStyle");
            return (Criteria) this;
        }

        public Criteria andSonglistStyleNotEqualTo(String value) {
            addCriterion("songList_style <>", value, "songlistStyle");
            return (Criteria) this;
        }

        public Criteria andSonglistStyleGreaterThan(String value) {
            addCriterion("songList_style >", value, "songlistStyle");
            return (Criteria) this;
        }

        public Criteria andSonglistStyleGreaterThanOrEqualTo(String value) {
            addCriterion("songList_style >=", value, "songlistStyle");
            return (Criteria) this;
        }

        public Criteria andSonglistStyleLessThan(String value) {
            addCriterion("songList_style <", value, "songlistStyle");
            return (Criteria) this;
        }

        public Criteria andSonglistStyleLessThanOrEqualTo(String value) {
            addCriterion("songList_style <=", value, "songlistStyle");
            return (Criteria) this;
        }

        public Criteria andSonglistStyleLike(String value) {
            addCriterion("songList_style like", value, "songlistStyle");
            return (Criteria) this;
        }

        public Criteria andSonglistStyleNotLike(String value) {
            addCriterion("songList_style not like", value, "songlistStyle");
            return (Criteria) this;
        }

        public Criteria andSonglistStyleIn(List<String> values) {
            addCriterion("songList_style in", values, "songlistStyle");
            return (Criteria) this;
        }

        public Criteria andSonglistStyleNotIn(List<String> values) {
            addCriterion("songList_style not in", values, "songlistStyle");
            return (Criteria) this;
        }

        public Criteria andSonglistStyleBetween(String value1, String value2) {
            addCriterion("songList_style between", value1, value2, "songlistStyle");
            return (Criteria) this;
        }

        public Criteria andSonglistStyleNotBetween(String value1, String value2) {
            addCriterion("songList_style not between", value1, value2, "songlistStyle");
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