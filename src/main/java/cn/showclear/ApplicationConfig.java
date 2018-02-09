package cn.showclear;

import cn.showclear.annotation.ExcelCellValue;
import cn.showclear.annotation.ExcelHeadName;
import org.junit.Test;

//这个版本的注解写死了
@ExcelHeadName(
        Dept ={"部门",""},
        name ={"姓名"},
        phoneNumber ={"分机号","固话","内线"},
        tellphoneNumber ={"手机"},
        email ={"E-mail","邮箱"},
        fex ={"传真"},
        deptExt ={"生日","职务"}
)
@ExcelCellValue(
        MEMBER_SEX_MAN = {"男"},
        MEMBER_SEX_WOMAN = {"女"},
        MEMBER_ACTIVE = {"是"},
        MENBER_UNACTIVE = {"否"}
)
public class ApplicationConfig {

    @Test
    public void testConfig(){
        //使用反射获取注解字段
        ExcelHeadName annotation = ApplicationConfig.class.getAnnotation(ExcelHeadName.class);
        System.out.println(annotation);
    }
}
