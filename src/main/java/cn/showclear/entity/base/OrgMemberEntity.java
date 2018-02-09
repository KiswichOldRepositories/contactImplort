package cn.showclear.entity.base;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "T_ORG_MEMBER", schema = "DB_SC_CORE", catalog = "")
@DynamicInsert
@DynamicUpdate
public class OrgMemberEntity {
    private int id;
    private int deptId;
    private String memCode;
    private String memName;
    private byte sex;
    private String firstLetter;
    private String memTel;
    private byte memType;
    private String memMobile;
    private String memTel2;
    private String memTel3;
    private String memTel4;
    private String memTel5;
    private String memFax;
    private String memEmail;
    private String memPicture;
    private String memVideo;
    private String memGis;
    private String orgCode;
    private Integer sortIndex;
    private long updateTime;
    private String deptExt;
    private byte isActive;
    private Timestamp modifyTime;
    private Timestamp createTime;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "dept_id")
    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    @Basic
    @Column(name = "mem_code")
    public String getMemCode() {
        return memCode;
    }

    public void setMemCode(String memCode) {
        this.memCode = memCode;
    }

    @Basic
    @Column(name = "mem_name")
    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName;
    }

    @Basic
    @Column(name = "sex")
    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
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
    @Column(name = "mem_tel")
    public String getMemTel() {
        return memTel;
    }

    public void setMemTel(String memTel) {
        this.memTel = memTel;
    }

    @Basic
    @Column(name = "mem_type")
    public byte getMemType() {
        return memType;
    }

    public void setMemType(byte memType) {
        this.memType = memType;
    }

    @Basic
    @Column(name = "mem_mobile")
    public String getMemMobile() {
        return memMobile;
    }

    public void setMemMobile(String memMobile) {
        this.memMobile = memMobile;
    }

    @Basic
    @Column(name = "mem_tel2")
    public String getMemTel2() {
        return memTel2;
    }

    public void setMemTel2(String memTel2) {
        this.memTel2 = memTel2;
    }

    @Basic
    @Column(name = "mem_tel3")
    public String getMemTel3() {
        return memTel3;
    }

    public void setMemTel3(String memTel3) {
        this.memTel3 = memTel3;
    }

    @Basic
    @Column(name = "mem_tel4")
    public String getMemTel4() {
        return memTel4;
    }

    public void setMemTel4(String memTel4) {
        this.memTel4 = memTel4;
    }

    @Basic
    @Column(name = "mem_tel5")
    public String getMemTel5() {
        return memTel5;
    }

    public void setMemTel5(String memTel5) {
        this.memTel5 = memTel5;
    }

    @Basic
    @Column(name = "mem_fax")
    public String getMemFax() {
        return memFax;
    }

    public void setMemFax(String memFax) {
        this.memFax = memFax;
    }

    @Basic
    @Column(name = "mem_email")
    public String getMemEmail() {
        return memEmail;
    }

    public void setMemEmail(String memEmail) {
        this.memEmail = memEmail;
    }

    @Basic
    @Column(name = "mem_picture")
    public String getMemPicture() {
        return memPicture;
    }

    public void setMemPicture(String memPicture) {
        this.memPicture = memPicture;
    }

    @Basic
    @Column(name = "mem_video")
    public String getMemVideo() {
        return memVideo;
    }

    public void setMemVideo(String memVideo) {
        this.memVideo = memVideo;
    }

    @Basic
    @Column(name = "mem_gis")
    public String getMemGis() {
        return memGis;
    }

    public void setMemGis(String memGis) {
        this.memGis = memGis;
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
    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    @Basic
    @Column(name = "update_time")
    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
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
    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrgMemberEntity that = (OrgMemberEntity) o;
        return id == that.id &&
                deptId == that.deptId &&
                sex == that.sex &&
                memType == that.memType &&
                updateTime == that.updateTime &&
                isActive == that.isActive &&
                Objects.equals(memCode, that.memCode) &&
                Objects.equals(memName, that.memName) &&
                Objects.equals(firstLetter, that.firstLetter) &&
                Objects.equals(memTel, that.memTel) &&
                Objects.equals(memMobile, that.memMobile) &&
                Objects.equals(memTel2, that.memTel2) &&
                Objects.equals(memTel3, that.memTel3) &&
                Objects.equals(memTel4, that.memTel4) &&
                Objects.equals(memTel5, that.memTel5) &&
                Objects.equals(memFax, that.memFax) &&
                Objects.equals(memEmail, that.memEmail) &&
                Objects.equals(memPicture, that.memPicture) &&
                Objects.equals(memVideo, that.memVideo) &&
                Objects.equals(memGis, that.memGis) &&
                Objects.equals(orgCode, that.orgCode) &&
                Objects.equals(sortIndex, that.sortIndex) &&
                Objects.equals(deptExt, that.deptExt) &&
                Objects.equals(modifyTime, that.modifyTime) &&
                Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, deptId, memCode, memName, sex, firstLetter, memTel, memType, memMobile, memTel2, memTel3, memTel4, memTel5, memFax, memEmail, memPicture, memVideo, memGis, orgCode, sortIndex, updateTime, deptExt, isActive, modifyTime, createTime);
    }
}