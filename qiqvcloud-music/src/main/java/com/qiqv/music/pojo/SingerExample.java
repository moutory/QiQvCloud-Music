package com.qiqv.music.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SingerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SingerExample() {
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

        public Criteria andSingerNameIsNull() {
            addCriterion("singer_name is null");
            return (Criteria) this;
        }

        public Criteria andSingerNameIsNotNull() {
            addCriterion("singer_name is not null");
            return (Criteria) this;
        }

        public Criteria andSingerNameEqualTo(String value) {
            addCriterion("singer_name =", value, "singerName");
            return (Criteria) this;
        }

        public Criteria andSingerNameNotEqualTo(String value) {
            addCriterion("singer_name <>", value, "singerName");
            return (Criteria) this;
        }

        public Criteria andSingerNameGreaterThan(String value) {
            addCriterion("singer_name >", value, "singerName");
            return (Criteria) this;
        }

        public Criteria andSingerNameGreaterThanOrEqualTo(String value) {
            addCriterion("singer_name >=", value, "singerName");
            return (Criteria) this;
        }

        public Criteria andSingerNameLessThan(String value) {
            addCriterion("singer_name <", value, "singerName");
            return (Criteria) this;
        }

        public Criteria andSingerNameLessThanOrEqualTo(String value) {
            addCriterion("singer_name <=", value, "singerName");
            return (Criteria) this;
        }

        public Criteria andSingerNameLike(String value) {
            addCriterion("singer_name like", value, "singerName");
            return (Criteria) this;
        }

        public Criteria andSingerNameNotLike(String value) {
            addCriterion("singer_name not like", value, "singerName");
            return (Criteria) this;
        }

        public Criteria andSingerNameIn(List<String> values) {
            addCriterion("singer_name in", values, "singerName");
            return (Criteria) this;
        }

        public Criteria andSingerNameNotIn(List<String> values) {
            addCriterion("singer_name not in", values, "singerName");
            return (Criteria) this;
        }

        public Criteria andSingerNameBetween(String value1, String value2) {
            addCriterion("singer_name between", value1, value2, "singerName");
            return (Criteria) this;
        }

        public Criteria andSingerNameNotBetween(String value1, String value2) {
            addCriterion("singer_name not between", value1, value2, "singerName");
            return (Criteria) this;
        }

        public Criteria andSingerSexIsNull() {
            addCriterion("singer_sex is null");
            return (Criteria) this;
        }

        public Criteria andSingerSexIsNotNull() {
            addCriterion("singer_sex is not null");
            return (Criteria) this;
        }

        public Criteria andSingerSexEqualTo(Boolean value) {
            addCriterion("singer_sex =", value, "singerSex");
            return (Criteria) this;
        }

        public Criteria andSingerSexNotEqualTo(Boolean value) {
            addCriterion("singer_sex <>", value, "singerSex");
            return (Criteria) this;
        }

        public Criteria andSingerSexGreaterThan(Boolean value) {
            addCriterion("singer_sex >", value, "singerSex");
            return (Criteria) this;
        }

        public Criteria andSingerSexGreaterThanOrEqualTo(Boolean value) {
            addCriterion("singer_sex >=", value, "singerSex");
            return (Criteria) this;
        }

        public Criteria andSingerSexLessThan(Boolean value) {
            addCriterion("singer_sex <", value, "singerSex");
            return (Criteria) this;
        }

        public Criteria andSingerSexLessThanOrEqualTo(Boolean value) {
            addCriterion("singer_sex <=", value, "singerSex");
            return (Criteria) this;
        }

        public Criteria andSingerSexIn(List<Boolean> values) {
            addCriterion("singer_sex in", values, "singerSex");
            return (Criteria) this;
        }

        public Criteria andSingerSexNotIn(List<Boolean> values) {
            addCriterion("singer_sex not in", values, "singerSex");
            return (Criteria) this;
        }

        public Criteria andSingerSexBetween(Boolean value1, Boolean value2) {
            addCriterion("singer_sex between", value1, value2, "singerSex");
            return (Criteria) this;
        }

        public Criteria andSingerSexNotBetween(Boolean value1, Boolean value2) {
            addCriterion("singer_sex not between", value1, value2, "singerSex");
            return (Criteria) this;
        }

        public Criteria andSingerPicIsNull() {
            addCriterion("singer_pic is null");
            return (Criteria) this;
        }

        public Criteria andSingerPicIsNotNull() {
            addCriterion("singer_pic is not null");
            return (Criteria) this;
        }

        public Criteria andSingerPicEqualTo(String value) {
            addCriterion("singer_pic =", value, "singerPic");
            return (Criteria) this;
        }

        public Criteria andSingerPicNotEqualTo(String value) {
            addCriterion("singer_pic <>", value, "singerPic");
            return (Criteria) this;
        }

        public Criteria andSingerPicGreaterThan(String value) {
            addCriterion("singer_pic >", value, "singerPic");
            return (Criteria) this;
        }

        public Criteria andSingerPicGreaterThanOrEqualTo(String value) {
            addCriterion("singer_pic >=", value, "singerPic");
            return (Criteria) this;
        }

        public Criteria andSingerPicLessThan(String value) {
            addCriterion("singer_pic <", value, "singerPic");
            return (Criteria) this;
        }

        public Criteria andSingerPicLessThanOrEqualTo(String value) {
            addCriterion("singer_pic <=", value, "singerPic");
            return (Criteria) this;
        }

        public Criteria andSingerPicLike(String value) {
            addCriterion("singer_pic like", value, "singerPic");
            return (Criteria) this;
        }

        public Criteria andSingerPicNotLike(String value) {
            addCriterion("singer_pic not like", value, "singerPic");
            return (Criteria) this;
        }

        public Criteria andSingerPicIn(List<String> values) {
            addCriterion("singer_pic in", values, "singerPic");
            return (Criteria) this;
        }

        public Criteria andSingerPicNotIn(List<String> values) {
            addCriterion("singer_pic not in", values, "singerPic");
            return (Criteria) this;
        }

        public Criteria andSingerPicBetween(String value1, String value2) {
            addCriterion("singer_pic between", value1, value2, "singerPic");
            return (Criteria) this;
        }

        public Criteria andSingerPicNotBetween(String value1, String value2) {
            addCriterion("singer_pic not between", value1, value2, "singerPic");
            return (Criteria) this;
        }

        public Criteria andSingerBirthIsNull() {
            addCriterion("singer_birth is null");
            return (Criteria) this;
        }

        public Criteria andSingerBirthIsNotNull() {
            addCriterion("singer_birth is not null");
            return (Criteria) this;
        }

        public Criteria andSingerBirthEqualTo(Date value) {
            addCriterion("singer_birth =", value, "singerBirth");
            return (Criteria) this;
        }

        public Criteria andSingerBirthNotEqualTo(Date value) {
            addCriterion("singer_birth <>", value, "singerBirth");
            return (Criteria) this;
        }

        public Criteria andSingerBirthGreaterThan(Date value) {
            addCriterion("singer_birth >", value, "singerBirth");
            return (Criteria) this;
        }

        public Criteria andSingerBirthGreaterThanOrEqualTo(Date value) {
            addCriterion("singer_birth >=", value, "singerBirth");
            return (Criteria) this;
        }

        public Criteria andSingerBirthLessThan(Date value) {
            addCriterion("singer_birth <", value, "singerBirth");
            return (Criteria) this;
        }

        public Criteria andSingerBirthLessThanOrEqualTo(Date value) {
            addCriterion("singer_birth <=", value, "singerBirth");
            return (Criteria) this;
        }

        public Criteria andSingerBirthIn(List<Date> values) {
            addCriterion("singer_birth in", values, "singerBirth");
            return (Criteria) this;
        }

        public Criteria andSingerBirthNotIn(List<Date> values) {
            addCriterion("singer_birth not in", values, "singerBirth");
            return (Criteria) this;
        }

        public Criteria andSingerBirthBetween(Date value1, Date value2) {
            addCriterion("singer_birth between", value1, value2, "singerBirth");
            return (Criteria) this;
        }

        public Criteria andSingerBirthNotBetween(Date value1, Date value2) {
            addCriterion("singer_birth not between", value1, value2, "singerBirth");
            return (Criteria) this;
        }

        public Criteria andSingerLocaltionIsNull() {
            addCriterion("singer_localtion is null");
            return (Criteria) this;
        }

        public Criteria andSingerLocaltionIsNotNull() {
            addCriterion("singer_localtion is not null");
            return (Criteria) this;
        }

        public Criteria andSingerLocaltionEqualTo(String value) {
            addCriterion("singer_localtion =", value, "singerLocaltion");
            return (Criteria) this;
        }

        public Criteria andSingerLocaltionNotEqualTo(String value) {
            addCriterion("singer_localtion <>", value, "singerLocaltion");
            return (Criteria) this;
        }

        public Criteria andSingerLocaltionGreaterThan(String value) {
            addCriterion("singer_localtion >", value, "singerLocaltion");
            return (Criteria) this;
        }

        public Criteria andSingerLocaltionGreaterThanOrEqualTo(String value) {
            addCriterion("singer_localtion >=", value, "singerLocaltion");
            return (Criteria) this;
        }

        public Criteria andSingerLocaltionLessThan(String value) {
            addCriterion("singer_localtion <", value, "singerLocaltion");
            return (Criteria) this;
        }

        public Criteria andSingerLocaltionLessThanOrEqualTo(String value) {
            addCriterion("singer_localtion <=", value, "singerLocaltion");
            return (Criteria) this;
        }

        public Criteria andSingerLocaltionLike(String value) {
            addCriterion("singer_localtion like", value, "singerLocaltion");
            return (Criteria) this;
        }

        public Criteria andSingerLocaltionNotLike(String value) {
            addCriterion("singer_localtion not like", value, "singerLocaltion");
            return (Criteria) this;
        }

        public Criteria andSingerLocaltionIn(List<String> values) {
            addCriterion("singer_localtion in", values, "singerLocaltion");
            return (Criteria) this;
        }

        public Criteria andSingerLocaltionNotIn(List<String> values) {
            addCriterion("singer_localtion not in", values, "singerLocaltion");
            return (Criteria) this;
        }

        public Criteria andSingerLocaltionBetween(String value1, String value2) {
            addCriterion("singer_localtion between", value1, value2, "singerLocaltion");
            return (Criteria) this;
        }

        public Criteria andSingerLocaltionNotBetween(String value1, String value2) {
            addCriterion("singer_localtion not between", value1, value2, "singerLocaltion");
            return (Criteria) this;
        }

        public Criteria andSingerIntroductionIsNull() {
            addCriterion("singer_introduction is null");
            return (Criteria) this;
        }

        public Criteria andSingerIntroductionIsNotNull() {
            addCriterion("singer_introduction is not null");
            return (Criteria) this;
        }

        public Criteria andSingerIntroductionEqualTo(String value) {
            addCriterion("singer_introduction =", value, "singerIntroduction");
            return (Criteria) this;
        }

        public Criteria andSingerIntroductionNotEqualTo(String value) {
            addCriterion("singer_introduction <>", value, "singerIntroduction");
            return (Criteria) this;
        }

        public Criteria andSingerIntroductionGreaterThan(String value) {
            addCriterion("singer_introduction >", value, "singerIntroduction");
            return (Criteria) this;
        }

        public Criteria andSingerIntroductionGreaterThanOrEqualTo(String value) {
            addCriterion("singer_introduction >=", value, "singerIntroduction");
            return (Criteria) this;
        }

        public Criteria andSingerIntroductionLessThan(String value) {
            addCriterion("singer_introduction <", value, "singerIntroduction");
            return (Criteria) this;
        }

        public Criteria andSingerIntroductionLessThanOrEqualTo(String value) {
            addCriterion("singer_introduction <=", value, "singerIntroduction");
            return (Criteria) this;
        }

        public Criteria andSingerIntroductionLike(String value) {
            addCriterion("singer_introduction like", value, "singerIntroduction");
            return (Criteria) this;
        }

        public Criteria andSingerIntroductionNotLike(String value) {
            addCriterion("singer_introduction not like", value, "singerIntroduction");
            return (Criteria) this;
        }

        public Criteria andSingerIntroductionIn(List<String> values) {
            addCriterion("singer_introduction in", values, "singerIntroduction");
            return (Criteria) this;
        }

        public Criteria andSingerIntroductionNotIn(List<String> values) {
            addCriterion("singer_introduction not in", values, "singerIntroduction");
            return (Criteria) this;
        }

        public Criteria andSingerIntroductionBetween(String value1, String value2) {
            addCriterion("singer_introduction between", value1, value2, "singerIntroduction");
            return (Criteria) this;
        }

        public Criteria andSingerIntroductionNotBetween(String value1, String value2) {
            addCriterion("singer_introduction not between", value1, value2, "singerIntroduction");
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