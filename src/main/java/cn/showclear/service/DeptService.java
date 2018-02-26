package cn.showclear.service;

import cn.showclear.entity.base.OrgDeptEntity;
import cn.showclear.entity.base.OrgMemberEntity;

import java.util.List;

/**
 * 单元格处理中dept相关的业务
 */
public interface DeptService {

    /**
     * 组装部门路径，将最后一个部门的路径写成前面部门的路径.后面的路径
     * @param deptEntities
     */
    public void buildDeptPath(List<OrgDeptEntity> deptEntities);

    /**
     * 将指定索引后的部门清空
     * @param deptEntities
     * @param index
     */
    public void removeAfterDept(List<OrgDeptEntity> deptEntities,Integer index);

    /**
     * 加入新的部门 ，加入父子关系，加入部门路径
     * @param deptEntities
     * @param orgDeptEntity
     * @param index 指的是插入的位置，也就是部门的等级
     */
    public void addDept(List<OrgDeptEntity> deptEntities,OrgDeptEntity orgDeptEntity,Integer index);

    /**
     * 
     * @param deptEntity
     * @param orgMemberEntity
     */
    public void addMember(OrgDeptEntity deptEntity, OrgMemberEntity orgMemberEntity);

}
