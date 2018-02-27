package cn.showclear.reposity;

import cn.showclear.entity.base.OrgDeptEntity;
import cn.showclear.entity.base.OrgMemberEntity;
import cn.showclear.repository.DeptRepository;
import cn.showclear.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootTest
@RunWith(SpringRunner.class)
@EnableJpaRepositories("cn.showclear.repository")
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ReposityTest {

    @Autowired
    DeptRepository deptRepository;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void testSave(){
        OrgDeptEntity one = deptRepository.findOne(1534);
        one.setDeptName("叙简科技");
        System.out.println("分割线--------------------------------------------------------------");
        deptRepository.save(one);
    }

    @Test
    public void testListSave(){
        ArrayList<OrgDeptEntity> orgDeptEntities = new ArrayList<>();
        IntStream.range(0,5).forEach(index->orgDeptEntities.add(new OrgDeptEntity("部门" + index)));
        deptRepository.save(orgDeptEntities);
    }

    @Test
    public void testOwnSave(){
        OrgMemberEntity orgMemberEntity = new OrgMemberEntity();
        orgMemberEntity.setMemEmail("zhangqiwei");
        orgMemberEntity.setMemName("娃哈哈哈哈哈哈");
        memberRepository.save(orgMemberEntity);

    }



}
