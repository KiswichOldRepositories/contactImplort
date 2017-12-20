package cn.showclear;

import cn.showclear.annotation.ExcelCellValue;
import cn.showclear.annotation.ExcelHeadName;
import org.junit.Test;

//这个版本的注解写死了
@ExcelHeadName(
        HEAD_DEPT={"部门",""},
        HEAD_NAME={"姓名"},
        HEAD_PHONENUMBER={"分机号","固话","内线"},
        HEAD_TELLPHONENUMBER={"手机"},
        HEAD_EMAIL={"E-mail","邮箱"},
        HEAD_FEX={"传真"},
        HEAD_EXP={"生日","职务"}
)
@ExcelCellValue(
        SEX_MAN = {"男"},
        SEX_WOMAN = {"女"},
        MEM_ACTIVE = {"是"},
        MEM_UNACTIVE = {"否"}
)
public class ApplicationConfig {

    @Test
    public void testConfig(){
        //使用反射获取注解字段
        ExcelHeadName annotation = ApplicationConfig.class.getAnnotation(ExcelHeadName.class);
        System.out.println(annotation);
    }
}
