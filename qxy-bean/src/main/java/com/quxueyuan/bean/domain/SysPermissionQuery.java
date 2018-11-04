package com.quxueyuan.bean.domain;

import java.util.ArrayList;
import java.util.List;

public class SysPermissionQuery {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_permission
     *
     * @mbggenerated Thu Aug 09 09:58:05 CST 2018
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_permission
     *
     * @mbggenerated Thu Aug 09 09:58:05 CST 2018
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_permission
     *
     * @mbggenerated Thu Aug 09 09:58:05 CST 2018
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission
     *
     * @mbggenerated Thu Aug 09 09:58:05 CST 2018
     */
    public SysPermissionQuery() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission
     *
     * @mbggenerated Thu Aug 09 09:58:05 CST 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission
     *
     * @mbggenerated Thu Aug 09 09:58:05 CST 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission
     *
     * @mbggenerated Thu Aug 09 09:58:05 CST 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission
     *
     * @mbggenerated Thu Aug 09 09:58:05 CST 2018
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission
     *
     * @mbggenerated Thu Aug 09 09:58:05 CST 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission
     *
     * @mbggenerated Thu Aug 09 09:58:05 CST 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission
     *
     * @mbggenerated Thu Aug 09 09:58:05 CST 2018
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission
     *
     * @mbggenerated Thu Aug 09 09:58:05 CST 2018
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission
     *
     * @mbggenerated Thu Aug 09 09:58:05 CST 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission
     *
     * @mbggenerated Thu Aug 09 09:58:05 CST 2018
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sys_permission
     *
     * @mbggenerated Thu Aug 09 09:58:05 CST 2018
     */
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
            addCriterion("sysPermission.id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("sysPermission.id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("sysPermission.id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("sysPermission.id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("sysPermission.id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sysPermission.id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("sysPermission.id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("sysPermission.id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("sysPermission.id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("sysPermission.id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("sysPermission.id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sysPermission.id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("sysPermission.parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("sysPermission.parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(Integer value) {
            addCriterion("sysPermission.parent_id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(Integer value) {
            addCriterion("sysPermission.parent_id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(Integer value) {
            addCriterion("sysPermission.parent_id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sysPermission.parent_id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(Integer value) {
            addCriterion("sysPermission.parent_id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(Integer value) {
            addCriterion("sysPermission.parent_id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<Integer> values) {
            addCriterion("sysPermission.parent_id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<Integer> values) {
            addCriterion("sysPermission.parent_id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(Integer value1, Integer value2) {
            addCriterion("sysPermission.parent_id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sysPermission.parent_id not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andLabelIsNull() {
            addCriterion("sysPermission.label is null");
            return (Criteria) this;
        }

        public Criteria andLabelIsNotNull() {
            addCriterion("sysPermission.label is not null");
            return (Criteria) this;
        }

        public Criteria andLabelEqualTo(String value) {
            addCriterion("sysPermission.label =", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotEqualTo(String value) {
            addCriterion("sysPermission.label <>", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelGreaterThan(String value) {
            addCriterion("sysPermission.label >", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelGreaterThanOrEqualTo(String value) {
            addCriterion("sysPermission.label >=", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelLessThan(String value) {
            addCriterion("sysPermission.label <", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelLessThanOrEqualTo(String value) {
            addCriterion("sysPermission.label <=", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelLike(String value) {
            addCriterion("sysPermission.label like", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotLike(String value) {
            addCriterion("sysPermission.label not like", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelIn(List<String> values) {
            addCriterion("sysPermission.label in", values, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotIn(List<String> values) {
            addCriterion("sysPermission.label not in", values, "label");
            return (Criteria) this;
        }

        public Criteria andLabelBetween(String value1, String value2) {
            addCriterion("sysPermission.label between", value1, value2, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotBetween(String value1, String value2) {
            addCriterion("sysPermission.label not between", value1, value2, "label");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("sysPermission.type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("sysPermission.type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("sysPermission.type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("sysPermission.type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("sysPermission.type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("sysPermission.type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("sysPermission.type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("sysPermission.type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("sysPermission.type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("sysPermission.type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("sysPermission.type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("sysPermission.type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("sysPermission.type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("sysPermission.type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("sysPermission.url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("sysPermission.url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("sysPermission.url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("sysPermission.url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("sysPermission.url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("sysPermission.url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("sysPermission.url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("sysPermission.url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("sysPermission.url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("sysPermission.url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("sysPermission.url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("sysPermission.url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("sysPermission.url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("sysPermission.url not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andAvailableIsNull() {
            addCriterion("sysPermission.available is null");
            return (Criteria) this;
        }

        public Criteria andAvailableIsNotNull() {
            addCriterion("sysPermission.available is not null");
            return (Criteria) this;
        }

        public Criteria andAvailableEqualTo(Boolean value) {
            addCriterion("sysPermission.available =", value, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableNotEqualTo(Boolean value) {
            addCriterion("sysPermission.available <>", value, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableGreaterThan(Boolean value) {
            addCriterion("sysPermission.available >", value, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableGreaterThanOrEqualTo(Boolean value) {
            addCriterion("sysPermission.available >=", value, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableLessThan(Boolean value) {
            addCriterion("sysPermission.available <", value, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableLessThanOrEqualTo(Boolean value) {
            addCriterion("sysPermission.available <=", value, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableIn(List<Boolean> values) {
            addCriterion("sysPermission.available in", values, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableNotIn(List<Boolean> values) {
            addCriterion("sysPermission.available not in", values, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableBetween(Boolean value1, Boolean value2) {
            addCriterion("sysPermission.available between", value1, value2, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableNotBetween(Boolean value1, Boolean value2) {
            addCriterion("sysPermission.available not between", value1, value2, "available");
            return (Criteria) this;
        }

        public Criteria andIconIsNull() {
            addCriterion("sysPermission.icon is null");
            return (Criteria) this;
        }

        public Criteria andIconIsNotNull() {
            addCriterion("sysPermission.icon is not null");
            return (Criteria) this;
        }

        public Criteria andIconEqualTo(String value) {
            addCriterion("sysPermission.icon =", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotEqualTo(String value) {
            addCriterion("sysPermission.icon <>", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconGreaterThan(String value) {
            addCriterion("sysPermission.icon >", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconGreaterThanOrEqualTo(String value) {
            addCriterion("sysPermission.icon >=", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLessThan(String value) {
            addCriterion("sysPermission.icon <", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLessThanOrEqualTo(String value) {
            addCriterion("sysPermission.icon <=", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLike(String value) {
            addCriterion("sysPermission.icon like", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotLike(String value) {
            addCriterion("sysPermission.icon not like", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconIn(List<String> values) {
            addCriterion("sysPermission.icon in", values, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotIn(List<String> values) {
            addCriterion("sysPermission.icon not in", values, "icon");
            return (Criteria) this;
        }

        public Criteria andIconBetween(String value1, String value2) {
            addCriterion("sysPermission.icon between", value1, value2, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotBetween(String value1, String value2) {
            addCriterion("sysPermission.icon not between", value1, value2, "icon");
            return (Criteria) this;
        }

        public Criteria andLabelLikeInsensitive(String value) {
            addCriterion("upper(sysPermission.label) like", value.toUpperCase(), "label");
            return (Criteria) this;
        }

        public Criteria andTypeLikeInsensitive(String value) {
            addCriterion("upper(sysPermission.type) like", value.toUpperCase(), "type");
            return (Criteria) this;
        }

        public Criteria andUrlLikeInsensitive(String value) {
            addCriterion("upper(sysPermission.url) like", value.toUpperCase(), "url");
            return (Criteria) this;
        }

        public Criteria andIconLikeInsensitive(String value) {
            addCriterion("upper(sysPermission.icon) like", value.toUpperCase(), "icon");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sys_permission
     *
     * @mbggenerated do_not_delete_during_merge Thu Aug 09 09:58:05 CST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sys_permission
     *
     * @mbggenerated Thu Aug 09 09:58:05 CST 2018
     */
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