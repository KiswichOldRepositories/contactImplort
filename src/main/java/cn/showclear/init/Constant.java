package cn.showclear.init;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * 存储在配置文件中的常量 之后的常量均可配置
 */
@ConfigurationProperties(prefix = "constant")
@Component
@Order(1)
public class Constant {
    private String mainDept;



    public String getMainDept() {
        return mainDept;
    }

    public void setMainDept(String mainDept) {
        this.mainDept = mainDept;
    }
}
