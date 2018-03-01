package cn.showclear.repository.jpa.specification;


import cn.showclear.entity.base.OrgDeptEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 好像只能用于复杂查询
 */
@Component
public class SpecificationMethod {
    /**
     * 测试
     * @return
     */
    public Specification<OrgDeptEntity> searchCustom(final String name){
        return new Specification<OrgDeptEntity>() {
            @Override
            public Predicate toPredicate(Root<OrgDeptEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("deptName"),name);
            }
        };
    }
}
