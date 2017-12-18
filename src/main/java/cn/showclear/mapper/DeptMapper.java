package cn.showclear.mapper;

import cn.showclear.pojo.entity.DeptEntity;

public interface DeptMapper {
    //名字一样 就忽略，名字不一样 就插入
    int insertWithName(DeptEntity deptEntity);
    DeptEntity selectByName(String name);
    DeptEntity selectById(int id);
    DeptEntity selectByParentId(int parentId);
    DeptEntity selectByParentIdAndName(DeptEntity deptEntity);
    int insertIfNameAndParentId(DeptEntity deptEntity);
}
