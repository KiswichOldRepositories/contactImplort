package cn.showclear.annotation;

import cn.showclear.constant.EnumMark;

import java.lang.annotation.*;


/**
 * 用来配置单元格中的值对应某个常量的配置。
 * （如 "视频用户" 对应着 Constant 中的常量 MENBER_TYPE_VIDEO）
 * 解决方案1：给每一个常量加一种配置
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelCellValue {

    String[] MEMBER_SEX_MAN() default {};
    String[] MEMBER_SEX_WOMAN() default {};
    String[] MEMBER_TYPE_NORMAL() default {};
    String[] MEMBER_TYPE_VIDEO() default {};
    String[] MEMBER_TYPE_MOBILE() default {};
    String[] MEMBER_ACTIVE() default {};
    String[] MENBER_UNACTIVE() default {};
}
