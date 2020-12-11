package com.qiqv.music.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListSongExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ListSongExample() {
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

        public Criteria andSongIdIsNull() {
            addCriterion("song_id is null");
            return (Criteria) this;
        }

        public Criteria andSongIdIsNotNull() {
            addCriterion("song_id is not null");
            return (Criteria) this;
        }

        public Criteria andSongIdEqualTo(Integer value) {
            addCriterion("song_id =", value, "songId");
            return (Criteria) this;
        }

        public Criteria andSongIdNotEqualTo(Integer value) {
            addCriterion("song_id <>", value, "songId");
            return (Criteria) this;
        }

        public Criteria andSongIdGreaterThan(Integer value) {
            addCriterion("song_id >", value, "songId");
            return (Criteria) this;
        }

        public Criteria andSongIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("song_id >=", value, "songId");
            return (Criteria) this;
        }

        public Criteria andSongIdLessThan(Integer value) {
            addCriterion("song_id <", value, "songId");
            return (Criteria) this;
        }

        public Criteria andSongIdLessThanOrEqualTo(Integer value) {
            addCriterion("song_id <=", value, "songId");
            return (Criteria) this;
        }

        public Criteria andSongIdIn(List<Integer> values) {
            addCriterion("song_id in", values, "songId");
            return (Criteria) this;
        }

        public Criteria andSongIdNotIn(List<Integer> values) {
            addCriterion("song_id not in", values, "songId");
            return (Criteria) this;
        }

        public Criteria andSongIdBetween(Integer value1, Integer value2) {
            addCriterion("song_id between", value1, value2, "songId");
            return (Criteria) this;
        }

        public Criteria andSongIdNotBetween(Integer value1, Integer value2) {
            addCriterion("song_id not between", value1, value2, "songId");
            return (Criteria) this;
        }

        public Criteria andSongListIdIsNull() {
            addCriterion("song_list_id is null");
            return (Criteria) this;
        }

        public Criteria andSongListIdIsNotNull() {
            addCriterion("song_list_id is not null");
            return (Criteria) this;
        }

        public Criteria andSongListIdEqualTo(Integer value) {
            addCriterion("song_list_id =", value, "songListId");
            return (Criteria) this;
        }

        public Criteria andSongListIdNotEqualTo(Integer value) {
            addCriterion("song_list_id <>", value, "songListId");
            return (Criteria) this;
        }

        public Criteria andSongListIdGreaterThan(Integer value) {
            addCriterion("song_list_id >", value, "songListId");
            return (Criteria) this;
        }

        public Criteria andSongListIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("song_list_id >=", value, "songListId");
            return (Criteria) this;
        }

        public Criteria andSongListIdLessThan(Integer value) {
            addCriterion("song_list_id <", value, "songListId");
            return (Criteria) this;
        }

        public Criteria andSongListIdLessThanOrEqualTo(Integer value) {
            addCriterion("song_list_id <=", value, "songListId");
            return (Criteria) this;
        }

        public Criteria andSongListIdIn(List<Integer> values) {
            addCriterion("song_list_id in", values, "songListId");
            return (Criteria) this;
        }

        public Criteria andSongListIdNotIn(List<Integer> values) {
            addCriterion("song_list_id not in", values, "songListId");
            return (Criteria) this;
        }

        public Criteria andSongListIdBetween(Integer value1, Integer value2) {
            addCriterion("song_list_id between", value1, value2, "songListId");
            return (Criteria) this;
        }

        public Criteria andSongListIdNotBetween(Integer value1, Integer value2) {
            addCriterion("song_list_id not between", value1, value2, "songListId");
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