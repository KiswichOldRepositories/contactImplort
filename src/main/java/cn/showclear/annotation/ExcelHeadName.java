package cn.showclear.annotation;


import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelHeadName {
    String[] HEAD_DEPT();
    String[] HEAD_NAME();
    String[] HEAD_PHONENUMBER();
    String[] HEAD_TELLPHONENUMBER();
    String[] HEAD_EMAIL();
    String[] HEAD_FEX();
    String[] HEAD_EXP();
}
