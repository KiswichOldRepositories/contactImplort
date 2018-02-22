package cn.showclear.repository.entityinformation;

import cn.showclear.entity.base.OrgMemberEntity;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.EntityInformation;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class MemberInformation extends JpaRepositoryFactory {

    /**
     * Creates a new {@link JpaRepositoryFactory}.
     *
     * @param entityManager must not be {@literal null}
     */
    public MemberInformation(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public <T, ID extends Serializable> JpaEntityInformation<T, ID> getEntityInformation(Class<T> domainClass) {
        return super.getEntityInformation(domainClass);
    }
}
