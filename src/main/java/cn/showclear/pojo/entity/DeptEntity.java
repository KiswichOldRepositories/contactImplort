package cn.showclear.pojo.entity;

import java.util.Date;
import java.util.Objects;

public class DeptEntity {
    private int id;
    //上级部门ID
    private int parentId;
    //部门名称
    private String deptName;
    //部门名称拼音首字母
    private String firstLetter;
    //组织编码
    private String orgCode;
    //排序
    private int sortIndex;
    //部门全称，如：叙简科技.研发部
    private String pathName;
    //同步KEY,该字段在和其他系统对接时使用
    private String syncKey;
    //描述
    private String deptDesc;
    //扩展字段,建议使用json格式
    private String deptExt;
    //是否激活：0-否;1-是
    private byte isActive;
    //记录修改时间
    private Date modifyTime;
    //记录创建时间
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public String getSyncKey() {
        return syncKey;
    }

    public void setSyncKey(String syncKey) {
        this.syncKey = syncKey;
    }

    public String getDeptDesc() {
        return deptDesc;
    }

    public void setDeptDesc(String deptDesc) {
        this.deptDesc = deptDesc;
    }

    public String getDeptExt() {
        return deptExt;
    }

    public void setDeptExt(String deptExt) {
        this.deptExt = deptExt;
    }

    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte isActive) {
        this.isActive = isActive;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeptEntity that = (DeptEntity) o;
        return id == that.id &&
                parentId == that.parentId &&
                sortIndex == that.sortIndex &&
                isActive == that.isActive &&
                Objects.equals(deptName, that.deptName) &&
                Objects.equals(firstLetter, that.firstLetter) &&
                Objects.equals(orgCode, that.orgCode) &&
                Objects.equals(pathName, that.pathName) &&
                Objects.equals(syncKey, that.syncKey) &&
                Objects.equals(deptDesc, that.deptDesc) &&
                Objects.equals(deptExt, that.deptExt) &&
                Objects.equals(modifyTime, that.modifyTime) &&
                Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, parentId, deptName, firstLetter, orgCode, sortIndex, pathName, syncKey, deptDesc, deptExt, isActive, modifyTime, createTime);
    }
}
