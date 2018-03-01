package cn.showclear.service.impl;

import cn.showclear.entity.base.OrgDeptEntity;
import cn.showclear.entity.base.OrgMemberEntity;
import cn.showclear.service.DeptService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    /**
     * 组装部门路径，将最后一个部门的路径写成前面部门的路径.后面的路径
     *
     * @param deptEntities
     */
    @Override
    public void buildDeptPath(List<OrgDeptEntity> deptEntities) {
        OrgDeptEntity childDept = deptEntities.get(deptEntities.size() - 1);
        OrgDeptEntity parentDept = deptEntities.get(deptEntities.size() - 2);
        childDept.setPathName(parentDept.getPathName() + "." + childDept.getDeptName());
    }

    /**
     * 将指定索引后的部门清空
     *
     * @param deptEntities
     * @param index
     */
    @Override
    public void removeAfterDept(List<OrgDeptEntity> deptEntities, Integer index) {
        if (index > deptEntities.size() - 1) return;
        for (int i = index; i < deptEntities.size() + 1; i++) {
            deptEntities.remove((int) index);
        }
    }

    /**
     * 加入新的部门 ，加入父子关系，加入部门路径
     *
     * @param deptEntities
     * @param orgDeptEntity
     */
    @Override
    public void addDept(List<OrgDeptEntity> deptEntities, OrgDeptEntity orgDeptEntity, Integer index) {
        removeAfterDept(deptEntities, index);

        if (!deptEntities.isEmpty()) {
            OrgDeptEntity parentDept = deptEntities.get(deptEntities.size() - 1);
            orgDeptEntity.setParentDept(parentDept);
            parentDept.getChildDept().add(orgDeptEntity);
        }

        deptEntities.add(orgDeptEntity);
        buildDeptPath(deptEntities);
    }

    /**
     * @param deptEntity
     * @param orgMemberEntity
     */
    @Override
    public void addMember(OrgDeptEntity deptEntity, OrgMemberEntity orgMemberEntity) {
        deptEntity.getChildMember().add(orgMemberEntity);
        orgMemberEntity.setParentDept(deptEntity);
    }

    /**
     * 复制部门的信息，
     * 用于findOne后，用查询的数据覆盖原先的空数据
     *
     * @param sourceDept
     * @param descDept
     */
    @Override
    public void copyDept(OrgDeptEntity sourceDept, OrgDeptEntity descDept) {
        descDept.setId(sourceDept.getId());
        if (descDept.getDeptName() == null) descDept.setDeptName(sourceDept.getDeptName());
        if (descDept.getOrgCode() == null) descDept.setOrgCode(sourceDept.getOrgCode());
        if (descDept.getSortIndex() == null) descDept.setSortIndex(sourceDept.getSortIndex());
        if (descDept.getPathName() == null) descDept.setPathName(sourceDept.getPathName());
        if (descDept.getSyncKey() == null) descDept.setSyncKey(sourceDept.getSyncKey());
        if (descDept.getDeptDesc() == null) descDept.setDeptDesc(sourceDept.getDeptDesc());
        if (descDept.getDeptExt() == null) descDept.setDeptExt(sourceDept.getDeptExt());
        if (descDept.getIsActive() == null) descDept.setIsActive(sourceDept.getIsActive());
        if (descDept.getModifyTime() == null) descDept.setModifyTime(sourceDept.getModifyTime());
        if (descDept.getCreateTime() == null) descDept.setCreateTime(sourceDept.getCreateTime());

    }

    /**
     * 复制人员的信息，
     * 用于findOne后，用查询的数据覆盖原先的空数据
     *
     * @param sourceMember
     * @param descMember
     */
    @Override
    @Deprecated
    public void copyMember(OrgMemberEntity sourceMember, OrgMemberEntity descMember) {
        descMember.setId(sourceMember.getId());
        if (descMember.getMemCode() == null) descMember.setMemCode(sourceMember.getMemCode());
        if (descMember.getMemName() == null) descMember.setMemName(sourceMember.getMemName());
        if (descMember.getSex() == null) descMember.setSex(sourceMember.getSex());
        if (descMember.getMemTel() == null) descMember.setMemTel(sourceMember.getMemTel());
        if (descMember.getMemType() == null) descMember.setMemType(sourceMember.getMemType());
        if (descMember.getMemMobile() == null)  descMember.setMemMobile(sourceMember.getMemMobile());
        if (descMember.getMemTel2() == null)  descMember.setMemTel2(sourceMember.getMemTel2());
        if (descMember.getMemTel3() == null)  descMember.setMemTel3(sourceMember.getMemTel3());
        if (descMember.getMemTel4() == null)  descMember.setMemTel4(sourceMember.getMemTel4());
        if (descMember.getMemTel5() == null)  descMember.setMemTel5(sourceMember.getMemTel5());
        if (descMember.getMemFax() == null)  descMember.setMemFax(sourceMember.getMemFax());
        if (descMember.getMemPicture() == null)  descMember.setMemPicture(sourceMember.getMemPicture());
        if (descMember.getMemVideo() == null)  descMember.setMemVideo(sourceMember.getMemVideo());
        if (descMember.getMemGis() == null)  descMember.setMemGis(sourceMember.getMemGis());
        if (descMember.getOrgCode() == null)  descMember.setOrgCode(sourceMember.getOrgCode());
        if (descMember.getSortIndex() == null)  descMember.setSortIndex(sourceMember.getSortIndex());
        if (descMember.getUpdateTime() == 0)   descMember.setUpdateTime(sourceMember.getUpdateTime());
        if (descMember.getDeptExt() == null)   descMember.setDeptExt(sourceMember.getDeptExt());
        if (descMember.getIsActive() == null)   descMember.setIsActive(sourceMember.getIsActive());
        if (descMember.getModifyTime() == null)  descMember.setModifyTime(sourceMember.getModifyTime());
        if (descMember.getParentDept() == null)   descMember.setParentDept(sourceMember.getParentDept());
    }


}
