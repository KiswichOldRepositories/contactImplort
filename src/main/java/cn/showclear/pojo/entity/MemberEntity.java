package cn.showclear.pojo.entity;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Objects;

/**
 * 员工实体类
 */
public class MemberEntity {
    private int id;
    //部门ID
    private int deptId;
    //成员编号（如员工号、警员编号等信息）
    private String memCode;
    //成员姓名
    private String memName;
    //性别，1:男； 2:女
    private Integer sex;
    //姓名拼音首字母
    private String firstLetter;
    //成员号码
    private String memTel;
    //成员类型:0-一般用户；1-视频话机用户；2-移动终端用户；
    private Integer memType;
    //手机号码
    private String memMobile;
    //号码二
    private String memTel2;
    //号码三
    private String memTel3;
    //号码四
    private String memTel4;
    //号码五
    private String memTel5;
    //传真号码
    private String memFax;
    //电子邮箱
    private String memEmail;
    //图片
    private String memPicture;
    //视频
    private String memVideo;
    //GIS坐标
    private String memGis;
    //组织编码
    private String orgCode;
    //排序号
    private Integer sortIndex;
    //更新时间
    private long updateTime;
    //扩展字段,建议使用json格式
    private String deptExt;
    //是否激活：0-否；1-是
    private Integer isActive;
    //记录修改时间
    private Date modifyTime;
    //记录创建时间
    private Date createTime;

    //添加电话号码 完成自动从电话1填充到电话5(还不够的话就自动忽略)
    public void addPhoneNumber(String phoneNumber) {
        if (StringUtils.isBlank(this.memTel)) this.memTel = phoneNumber;
        else if (StringUtils.isBlank(this.memTel2)) this.memTel2 = phoneNumber;
        else if (StringUtils.isBlank(this.memTel3)) this.memTel3 = phoneNumber;
        else if (StringUtils.isBlank(this.memTel4)) this.memTel4 = phoneNumber;
        else if (StringUtils.isBlank(this.memTel4)) this.memTel5 = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getMemCode() {
        return memCode;
    }

    public void setMemCode(String memCode) {
        this.memCode = memCode;
    }

    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public String getMemTel() {
        return memTel;
    }

    public void setMemTel(String memTel) {
        this.memTel = memTel;
    }

    public Integer getMemType() {
        return memType;
    }

    public void setMemType(Integer memType) {
        this.memType = memType;
    }

    public String getMemMobile() {
        return memMobile;
    }

    public void setMemMobile(String memMobile) {
        this.memMobile = memMobile;
    }

    public String getMemTel2() {
        return memTel2;
    }

    public void setMemTel2(String memTel2) {
        this.memTel2 = memTel2;
    }

    public String getMemTel3() {
        return memTel3;
    }

    public void setMemTel3(String memTel3) {
        this.memTel3 = memTel3;
    }

    public String getMemTel4() {
        return memTel4;
    }

    public void setMemTel4(String memTel4) {
        this.memTel4 = memTel4;
    }

    public String getMemTel5() {
        return memTel5;
    }

    public void setMemTel5(String memTel5) {
        this.memTel5 = memTel5;
    }

    public String getMemFax() {
        return memFax;
    }

    public void setMemFax(String memFax) {
        this.memFax = memFax;
    }

    public String getMemEmail() {
        return memEmail;
    }

    public void setMemEmail(String memEmail) {
        this.memEmail = memEmail;
    }

    public String getMemPicture() {
        return memPicture;
    }

    public void setMemPicture(String memPicture) {
        this.memPicture = memPicture;
    }

    public String getMemVideo() {
        return memVideo;
    }

    public void setMemVideo(String memVideo) {
        this.memVideo = memVideo;
    }

    public String getMemGis() {
        return memGis;
    }

    public void setMemGis(String memGis) {
        this.memGis = memGis;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }


    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeptExt() {
        return deptExt;
    }

    public void setDeptExt(String deptExt) {
        this.deptExt = deptExt;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
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
        MemberEntity that = (MemberEntity) o;
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

    @Override
    public String toString() {
        return "MemberEntity{" +
                "id=" + id +
                ", deptId=" + deptId +
                ", memCode='" + memCode + '\'' +
                ", memName='" + memName + '\'' +
                ", sex=" + sex +
                ", firstLetter='" + firstLetter + '\'' +
                ", memTel='" + memTel + '\'' +
                ", memType=" + memType +
                ", memMobile='" + memMobile + '\'' +
                ", memTel2='" + memTel2 + '\'' +
                ", memTel3='" + memTel3 + '\'' +
                ", memTel4='" + memTel4 + '\'' +
                ", memTel5='" + memTel5 + '\'' +
                ", memFax='" + memFax + '\'' +
                ", memEmail='" + memEmail + '\'' +
                ", memPicture='" + memPicture + '\'' +
                ", memVideo='" + memVideo + '\'' +
                ", memGis='" + memGis + '\'' +
                ", orgCode='" + orgCode + '\'' +
                ", sortIndex=" + sortIndex +
                ", updateTime=" + updateTime +
                ", deptExt='" + deptExt + '\'' +
                ", isActive=" + isActive +
                ", modifyTime=" + modifyTime +
                ", createTime=" + createTime +
                '}';
    }
}
