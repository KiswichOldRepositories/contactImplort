package cn.showclear.repository.jpa;

import cn.showclear.entity.base.OrgDeptEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptRepository extends CrudRepository<OrgDeptEntity,Integer> ,JpaSpecificationExecutor{
    @Modifying
    @Query("update OrgDeptEntity d set d.deptName = :#{#dept.deptName}  where d.id= :#{#dept.id}")
    public int setByDept(@Param("dept") OrgDeptEntity orgDeptEntity);

    public List<OrgDeptEntity> findByPathName(String pathName);
}
