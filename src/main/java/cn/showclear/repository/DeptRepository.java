package cn.showclear.repository;

import cn.showclear.entity.base.OrgDeptEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptRepository extends CrudRepository<OrgDeptEntity,Integer> {

}
