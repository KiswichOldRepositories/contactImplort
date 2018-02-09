package cn.showclear.annotation;


import java.lang.annotation.*;

/**
 * 用于表格头配置的注解类
 * 各个属性表示数据库中的一种字段，其string[]中的每一个值为在excel中可能出现的字段名
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelHeadName {
    /**
     * 表示表示部门的一串string
     * @return
     */
    String[] Dept() default {};
    /**
     * 表示人员姓名的一串string
     * @return
     */
    String[] name() default {};
    /**
     * 表示固话的一串string
     * @return
     */
    String[] phoneNumber() default {};
    /**
     * 表示移动电话的一串string
     * @return
     */
    String[] tellphoneNumber() default {};
    /**
     * 表示电子邮箱的一串string
     * @return
     */
    String[] email() default {};
    /**
     * 表示传真的一串string
     * @return
     */
    String[] fex() default {};
    /**
     * 表示拓展字段的一串string
     * string
     * @return
     */
    String[] deptExt() default {};
    /**
     * 表示性别的一串string
     * @return
     */
    String[] sex() default {};
    /**
     * 表示成员编号的一串string
     * @return
     */
    String[] memCode() default {};
    /**
     * 表示成员类型的一串string
     * @return
     */
    String[] type() default {};
    /**
     * 表示组织编码的一串string
     * @return
     */
    String[] orgCode() default {};
}
