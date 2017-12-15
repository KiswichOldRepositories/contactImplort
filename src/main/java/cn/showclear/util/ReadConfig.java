package cn.showclear.util;

import cn.showclear.ApplicationConfig;
import cn.showclear.annotation.ExcelHeadName;
import cn.showclear.pojo.EntityManager;
import cn.showclear.pojo.common.TableHeadEnum;
import cn.showclear.pojo.common.TableHeadRe;

import java.util.HashMap;
import java.util.Map;

/**
 * 读取各类配置信息
 */
public class ReadConfig {
    /**
     * 读取各项配置的入口函数
     */
    public static void read(){
        readDicAnno();
    }

    /**
     * 读取字典的注解信息,并储存
     */
    private static void readDicAnno(){
        ExcelHeadName annotation = ApplicationConfig.class.getAnnotation(ExcelHeadName.class);
        EntityManager.tableHeadDictory = new HashMap<>();
        Map<String, TableHeadEnum> headDictory = EntityManager.tableHeadDictory;
        //储存部门词典
        for(String str: annotation.HEAD_DEPT()){
            headDictory.put(str, TableHeadEnum.Dept);
        }
        //储存邮箱词典
        for(String str:annotation.HEAD_EMAIL()){
            headDictory.put(str, TableHeadEnum.email);
        }
        //储存传真词典
        for(String str:annotation.HEAD_FEX()){
            headDictory.put(str, TableHeadEnum.fex);
        }
        //储存姓名词典
        for(String str:annotation.HEAD_NAME()){
            headDictory.put(str, TableHeadEnum.name);
        }
        //储存电话号码词典
        for(String str:annotation.HEAD_PHONENUMBER()){
            headDictory.put(str, TableHeadEnum.phoneNumber);
        }
        //储存手机号码词典
        for(String str:annotation.HEAD_TELLPHONENUMBER()){
            headDictory.put(str, TableHeadEnum.tellphoneNumber);
        }
        //储存拓展字段词典
        for(String str:annotation.HEAD_EXP()){
            headDictory.put(str, TableHeadEnum.deptExt);
        }
    }
}
