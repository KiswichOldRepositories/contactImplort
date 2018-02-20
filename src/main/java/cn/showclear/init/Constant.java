package cn.showclear.init;

import cn.showclear.entity.common.ExcelConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;


/**
 * 存储在配置文件中的常量 之后的常量均可配置
 */
@ConfigurationProperties(prefix = "constant")
@Component
public class Constant {
    private String mainDept;



    public String getMainDept() {
        return mainDept;
    }

    public void setMainDept(String mainDept) {
        this.mainDept = mainDept;
    }
}
