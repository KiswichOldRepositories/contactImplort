package cn.showclear.mapper;

import cn.showclear.pojo.entity.DeptEntity;


public interface DeptMapper {
    //。。单纯的插入一个DeptEntity
    int insertWithName(DeptEntity deptEntity);
    //根据name来查找DeptEntity
    DeptEntity selectByName(String name);
    //根据id来查找DeptEntity
    DeptEntity selectById(int id);
    //根据ParentId来查找DeptEntity
    DeptEntity selectByParentId(int parentId);
    //根据ParentId和Name来查找DeptEntity
    DeptEntity selectByParentIdAndName(DeptEntity deptEntity);

    int insertIfNameAndParentId(DeptEntity deptEntity);//功能有点问题
}
