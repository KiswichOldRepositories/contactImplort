package cn.showclear.repository;

import cn.showclear.entity.base.OrgMemberEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends CrudRepository<OrgMemberEntity,Integer> {

//    @Modifying
//    @Query()
//    public Integer saveByEmail(@Param("member") OrgMemberEntity orgMemberEntity);

    public List<OrgMemberEntity> findByMemEmail(String memEmail);
}
