package cn.showclear.repository.jpa.specification;

import cn.showclear.entity.base.OrgDeptEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TestSpecification implements Specification<OrgDeptEntity> {
    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {

        return null;
    }
}