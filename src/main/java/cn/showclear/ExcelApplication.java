package cn.showclear;

import cn.showclear.entity.User;
import cn.showclear.exception.ArgsMissException;
import cn.showclear.init.Constant;
import cn.showclear.init.ReadConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;


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
        try {
            ReadConfig.read(args);
        } catch (IOException e) {
            System.out.println("请检查所处的目录是否具有读写权限！");
            return;
        } catch (ArgsMissException e) {
            System.out.println(e.getMessage());
            return;
        }

        try {
            //运行spring boot主题
            SpringApplication application = new SpringApplication(ExcelApplication.class);
            application.setWebEnvironment(false);
            application.run(args);
        } finally {
            if (Constant.file != null && Constant.file.exists()) {
                if (!Constant.file.delete()) System.out.println("于本目录下的临时配置文件删除失败，请手动删除 ");
            }
        }
    }

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        //这里开始写主要的逻辑

    }
}


