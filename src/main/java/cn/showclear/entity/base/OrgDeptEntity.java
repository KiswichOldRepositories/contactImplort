package cn.showclear.entity.base;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "T_ORG_DEPT", schema = "DB_SC_CORE", catalog = "")
@DynamicInsert
@DynamicUpdate
public class OrgDeptEntity {
    private int id;
    private int parentId;
    private String deptName;
    private String firstLetter;
    private String orgCode;
    private int sortIndex;
    private String pathName;
    private String syncKey;
    private String deptDesc;
    private String deptExt;
    private byte isActive;
    private Date modifyTime;
    private Date createTime;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "parent_id")
    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "dept_name")
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
        try {
            this.firstLetter = PinyinHelper.getShortPinyin(deptName);
        } catch (PinyinException e) {
            e.printStackTrace();
        }
    }

    @Basic
    @Column(name = "first_letter")
    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    @Basic
    @Column(name = "org_code")
    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    @Basic
    @Column(name = "sort_index")
    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }

    @Basic
    @Column(name = "path_name")
    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    @Basic
    @Column(name = "sync_key")
    public String getSyncKey() {
        return syncKey;
    }

    public void setSyncKey(String syncKey) {
        this.syncKey = syncKey;
    }

    @Basic
    @Column(name = "dept_desc")
    public String getDeptDesc() {
        return deptDesc;
    }

    public void setDeptDesc(String deptDesc) {
        this.deptDesc = deptDesc;
    }

    @Basic
    @Column(name = "dept_ext")
    public String getDeptExt() {
        return deptExt;
    }

    public void setDeptExt(String deptExt) {
        this.deptExt = deptExt;
    }

    @Basic
    @Column(name = "is_active")
    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte isActive) {
        this.isActive = isActive;
    }

    @Basic
    @Column(name = "modify_time")
    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Basic
    @Column(name = "create_time")
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
        OrgDeptEntity that = (OrgDeptEntity) o;
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
