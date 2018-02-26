package cn.showclear.service.impl;

import cn.showclear.entity.base.OrgDeptEntity;
import cn.showclear.entity.base.OrgMemberEntity;
import cn.showclear.service.DeptService;
import org.junit.Test;
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
        for (int i = index; i < deptEntities.size()+1; i++) {
            deptEntities.remove((int)index);
        }
    }

    /**
     * 加入新的部门 ，加入父子关系，加入部门路径
     *
     * @param deptEntities
     * @param orgDeptEntity
     */
    @Override
    public void addDept(List<OrgDeptEntity> deptEntities, OrgDeptEntity orgDeptEntity,Integer index) {
        removeAfterDept(deptEntities,index);

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


}
