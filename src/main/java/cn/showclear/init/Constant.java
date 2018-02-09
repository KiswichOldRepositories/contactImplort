package cn.showclear.init;

import cn.showclear.entity.common.ExcelConfig;

import java.io.File;

/**
 * 用于保存在spring boot初始化前生成、输入的参数
 * 早spring boot初始化之后，参数保存在{@link Config}的 bean 当中
 */
public class Constant {
    public static final String SYSTEM = System.getProperty("user.home") + File.separator + "scooper" + File.separator + "temp";

    //该excel具有的参数 ，原则上不允许修改
    public static ExcelConfig excelConfig;

    //临时产生的配置文件
    public static File file;
}
