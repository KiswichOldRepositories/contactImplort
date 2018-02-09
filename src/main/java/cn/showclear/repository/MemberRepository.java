package cn.showclear.repository;

import cn.showclear.entity.base.OrgMemberEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends CrudRepository<OrgMemberEntity,Integer> {

}
