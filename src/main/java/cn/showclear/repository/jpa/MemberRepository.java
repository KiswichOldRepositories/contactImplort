package cn.showclear.repository.jpa;

import cn.showclear.entity.base.OrgMemberEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends CrudRepository<OrgMemberEntity,Integer> {

    public List<OrgMemberEntity> findByMemEmail(String memEmail);
}
