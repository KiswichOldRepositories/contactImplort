package cn.showclear.mapper;

import cn.showclear.pojo.entity.MemberEntity;

public interface MemberMapper {
    //判断email有没有重复的，没有重复的就插入一个MemberEntity，有重复的就更新那个MemberEntity，但这样就无法返回id
    Integer insertByMember(MemberEntity memberEntity);
}
