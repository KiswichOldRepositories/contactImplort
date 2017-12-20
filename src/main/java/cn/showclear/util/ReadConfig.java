package cn.showclear.util;

import cn.showclear.ApplicationConfig;
import cn.showclear.annotation.ExcelCellValue;
import cn.showclear.annotation.ExcelHeadName;
import cn.showclear.constant.Constant;
import cn.showclear.pojo.EntityEnManager;
import cn.showclear.pojo.common.TableHeadEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * 读取各类配置信息
 */
public class ReadConfig implements Constant{
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
        ExcelHeadName headName = ApplicationConfig.class.getAnnotation(ExcelHeadName.class);
        EntityEnManager.tableHeadDictory = new HashMap<>();
        Map<String, TableHeadEnum> headDictory = EntityEnManager.tableHeadDictory;
        //储存部门词典
        for(String str: headName.HEAD_DEPT()){
            headDictory.put(str, TableHeadEnum.Dept);
        }
        //储存邮箱词典
        for(String str:headName.HEAD_EMAIL()){
            headDictory.put(str, TableHeadEnum.email);
        }
        //储存传真词典
        for(String str:headName.HEAD_FEX()){
            headDictory.put(str, TableHeadEnum.fex);
        }
        //储存姓名词典
        for(String str:headName.HEAD_NAME()){
            headDictory.put(str, TableHeadEnum.name);
        }
        //储存电话号码词典
        for(String str:headName.HEAD_PHONENUMBER()){
            headDictory.put(str, TableHeadEnum.phoneNumber);
        }
        //储存手机号码词典
        for(String str:headName.HEAD_TELLPHONENUMBER()){
            headDictory.put(str, TableHeadEnum.tellphoneNumber);
        }
        //储存拓展字段词典
        for(String str:headName.HEAD_EXP()){
            headDictory.put(str, TableHeadEnum.deptExt);
        }
        //储存性别词典
        for(String str:headName.HEAD_SEX()){
            headDictory.put(str, TableHeadEnum.sex);
        }
        //储存成员编号词典
        for(String str:headName.HEAD_MEM_CODE()){
            headDictory.put(str, TableHeadEnum.memCode);
        }
        //储存部门编号词典
        for(String str:headName.HEAD_ORG_CODE()){
            headDictory.put(str, TableHeadEnum.orgCode);
        }
        //储存用户类型词典
        for(String str:headName.HEAD_MEM_TYPE()){
            headDictory.put(str, TableHeadEnum.type);
        }

        ExcelCellValue cellValue = ApplicationConfig.class.getAnnotation(ExcelCellValue.class);
        EntityEnManager.tableCellValueDictory = new HashMap<>();
        Map<String, Integer> cellValueDictory = EntityEnManager.tableCellValueDictory;
        //储存对应MEM_ACTIVE的字符串数组
        for(String string:cellValue.MEM_ACTIVE()){
            cellValueDictory.put(string,MEMBER_ACTIVE);
        }
        //储存对应MEM_UNACTIVE的字符串数组
        for(String string:cellValue.MEM_UNACTIVE()){
            cellValueDictory.put(string,DEPT_UNACTIVE);
        }
        //储存对应MEMBER_TYPE_NORMAL的字符串数组
        for(String string:cellValue.MEM_TYPE_NORMAL()){
            cellValueDictory.put(string,MEMBER_TYPE_NORMAL);
        }
        //储存对应MEMBER_TYPE_MOBILE的字符串数组
        for(String string:cellValue.MEM_TYPE_MOBILE()){
            cellValueDictory.put(string,MEMBER_TYPE_MOBILE);
        }
        //储存对应MEMBER_TYPE_VIDEO的字符串数组
        for(String string:cellValue.MEM_TYPE_VIDEO()){
            cellValueDictory.put(string,MEMBER_TYPE_VIDEO);
        }
        //储存对应MEMBER_SEX_MAN的字符串数组
        for(String string:cellValue.SEX_MAN()){
            cellValueDictory.put(string,MEMBER_SEX_MAN);
        }
        //储存对应MEMBER_SEX_WOMAN的字符串数组
        for(String string:cellValue.SEX_WOMAN()){
            cellValueDictory.put(string,MEMBER_SEX_WOMAN);
        }

    }
}
