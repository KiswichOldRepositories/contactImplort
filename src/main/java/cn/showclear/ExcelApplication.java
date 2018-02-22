package cn.showclear;


import cn.showclear.entity.base.OrgDeptEntity;
import cn.showclear.entity.common.OptionEnum;
import cn.showclear.exception.ArgsMissException;
import cn.showclear.init.*;
import cn.showclear.repository.DeptRepository;
import cn.showclear.repository.MemberRepository;


import cn.showclear.service.impl.HelpServiceImpl;
import cn.showclear.service.impl.TemplateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;


import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;


/**
 * 启动入口
 * 读取输入的命令行参数，写入到外部配置文件
 */
@SpringBootApplication
@Configuration
@PropertySource("file:comment.properties")
@EnableJpaRepositories("cn.showclear.repository")

public class ExcelApplication implements CommandLineRunner {


    public static void main(String[] args) {
        ConfigurableApplicationContext run = null;

        try {

            try {
                System.out.println("正在读取输入参数...");
                ReadConfig.read(args);
            } catch (IOException e) {
                System.out.println("请检查所处的目录是否具有读写权限！");
                return;
            } catch (ArgsMissException e) {
                System.out.println(e.getMessage());
                return;
            }

            switch (InitBean.getInstance().getOptionEnum()) {
                case help:
                    new HelpServiceImpl().helpImfo();
                    break;
                case templeXML:
                    new TemplateServiceImpl().createYmlTemplate();
                    break;
                case transform:
                    //运行spring boot主题
                    System.out.println("正在启动spring boot，配置spring环境...");
                    SpringApplication application = new SpringApplication(ExcelApplication.class);
                    application.setWebEnvironment(false);
                    //运行结束退出程序
                    run = application.run(args);
                    break;
            }


        } finally {
            if (InitBean.getInstance().getFile() != null && InitBean.getInstance().getFile().exists()) {
                System.out.println("正在删除临时配置文件...");
                if (!InitBean.getInstance().getFile().delete()) System.out.println("于本目录下的临时配置文件删除失败，请手动删除 ");
            }

            if (run != null) SpringApplication.exit(run);
        }
    }

    @Autowired
    private InitBean initBean;

//    @Autowired private ExcelHeadConfig excelHeadConfig;

    @Autowired
    private ExcelHeadConfig configProperties;


    @Autowired
    MemberRepository memberRepository;

    @Autowired
    DeptRepository deptRepository;

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    @Transactional
    public void run(String... args) throws Exception {
        //这里开始写主要的逻辑
        System.out.println("spring环境配置完毕");
        initBean.setOptionEnum(OptionEnum.transform);
        System.out.println(initBean);
        System.out.println(InitBean.getInstance());
        System.out.println(configProperties.toString());
        Date date = new Date();
        OrgDeptEntity one = deptRepository.findOne(1534);
        System.out.println("花了 " + (new Date().getTime()-date.getTime()) );
        OrgDeptEntity next = one.getChildDept().iterator().next();
        next.setDeptName("叙简");
        deptRepository.save(one);

        OrgDeptEntity orgDeptEntity = new OrgDeptEntity();
        orgDeptEntity.setDeptName("测试");
        orgDeptEntity.setId(1535);
        deptRepository.setByDept(orgDeptEntity);

        System.out.println(Arrays.toString(one.getChildDept().toArray()));

        System.out.println(initBean.getFile().getAbsolutePath());


    }
}


