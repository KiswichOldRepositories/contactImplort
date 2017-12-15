package cn.showclear;

import cn.showclear.annotation.ExcelHeadName;
import org.junit.Test;

@ExcelHeadName(
        HEAD_DEPT={"部门",""},
        HEAD_NAME={"姓名"},
        HEAD_PHONENUMBER={"分机号","固话","内线"},
        HEAD_TELLPHONENUMBER={"手机"},
        HEAD_EMAIL={"E-mail","邮箱"},
        HEAD_FEX={"传真"},
        HEAD_EXP={"生日","职务"}
)
public class ApplicationConfig {

    @Test
    public void testConfig(){
        //使用反射获取注解字段
        ExcelHeadName annotation = ApplicationConfig.class.getAnnotation(ExcelHeadName.class);
        System.out.println(annotation);
    }
}
