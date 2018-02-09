package cn.showclear.util;

import cn.showclear.ApplicationConfig;
import cn.showclear.annotation.ExcelCellValue;
import cn.showclear.annotation.ExcelHeadName;
import cn.showclear.constant.Constant;
import cn.showclear.pojo.EntityEnManager;
import cn.showclear.pojo.common.TableHeadEnum;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 读取各类配置信息
 */
public class ReadConfig implements Constant {
    /**
     * 读取各项配置的入口函数
     */
    public static void read() {
        readDicAnnoByReflect();
    }

    @Test
    public void test() {
        ReadConfig.readDicAnnoByReflect();
    }

    /**
     * 反射遍历
     */
    public static void readDicAnnoByReflect() {
        try {
            ExcelHeadName headName = ApplicationConfig.class.getAnnotation(ExcelHeadName.class);
            Class<? extends ExcelHeadName> headNameClass = headName.getClass();

            Map<String, TableHeadEnum> tableHeadDictory = new HashMap<>();

            Method[] methods = headNameClass.getMethods();

            for (Method method : methods) {
                String methodName = method.getName();

                for (TableHeadEnum headEnum : TableHeadEnum.values()) {
                    if (headEnum.toString().equals(methodName)) {
                        try {
                            String[] invoke = (String[]) method.invoke(headName);
                            for (String name : invoke) {
                                tableHeadDictory.put(name, headEnum);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            EntityEnManager.tableHeadDictory = tableHeadDictory;

            ExcelCellValue cellValue = ApplicationConfig.class.getAnnotation(ExcelCellValue.class);
            EntityEnManager.tableCellValueDictory = new HashMap<>();
            Map<String, Integer> cellValueDictory = EntityEnManager.tableCellValueDictory;
            Integer i = 0;
            Field[] fields = Constant.class.getFields();

            Class<? extends ExcelCellValue> cellValueClass = cellValue.getClass();
            Method[] cellValueClassMethods = cellValueClass.getMethods();

            for (Method method : cellValueClassMethods) {
                String name = method.getName();
                for (Field field : fields) {
                    //如果匹配成功
                    if(field.getName().equals(name)){
                        String[] invoke = (String[]) method.invoke(cellValue);
                        for (String cellText : invoke) {
                            cellValueDictory.put(cellText, (Integer) Constant.class.getField(name).get(i));
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取字典的注解信息,并储存
     */
    @Deprecated
    private static void readDicAnno() {
        ExcelHeadName headName = ApplicationConfig.class.getAnnotation(ExcelHeadName.class);
        EntityEnManager.tableHeadDictory = new HashMap<>();
        Map<String, TableHeadEnum> headDictory = EntityEnManager.tableHeadDictory;
        //储存部门词典
        for (String str : headName.Dept()) {
            headDictory.put(str, TableHeadEnum.Dept);
        }
        //储存邮箱词典
        for (String str : headName.email()) {
            headDictory.put(str, TableHeadEnum.email);
        }
        //储存传真词典
        for (String str : headName.fex()) {
            headDictory.put(str, TableHeadEnum.fex);
        }
        //储存姓名词典
        for (String str : headName.name()) {
            headDictory.put(str, TableHeadEnum.name);
        }
        //储存电话号码词典
        for (String str : headName.phoneNumber()) {
            headDictory.put(str, TableHeadEnum.phoneNumber);
        }
        //储存手机号码词典
        for (String str : headName.tellphoneNumber()) {
            headDictory.put(str, TableHeadEnum.tellphoneNumber);
        }
        //储存拓展字段词典
        for (String str : headName.deptExt()) {
            headDictory.put(str, TableHeadEnum.deptExt);
        }
        //储存性别词典
        for (String str : headName.sex()) {
            headDictory.put(str, TableHeadEnum.sex);
        }
        //储存成员编号词典
        for (String str : headName.memCode()) {
            headDictory.put(str, TableHeadEnum.memCode);
        }
        //储存部门编号词典
        for (String str : headName.orgCode()) {
            headDictory.put(str, TableHeadEnum.orgCode);
        }
        //储存用户类型词典
        for (String str : headName.type()) {
            headDictory.put(str, TableHeadEnum.type);
        }

        ExcelCellValue cellValue = ApplicationConfig.class.getAnnotation(ExcelCellValue.class);
        EntityEnManager.tableCellValueDictory = new HashMap<>();
        Map<String, Integer> cellValueDictory = EntityEnManager.tableCellValueDictory;
        //储存对应MEM_ACTIVE的字符串数组
        for (String string : cellValue.MEMBER_ACTIVE()) {
            cellValueDictory.put(string, MEMBER_ACTIVE);
        }
        //储存对应MEM_UNACTIVE的字符串数组
        for (String string : cellValue.MENBER_UNACTIVE()) {
            cellValueDictory.put(string, DEPT_UNACTIVE);
        }
        //储存对应MEMBER_TYPE_NORMAL的字符串数组
        for (String string : cellValue.MEMBER_TYPE_NORMAL()) {
            cellValueDictory.put(string, MEMBER_TYPE_NORMAL);
        }
        //储存对应MEMBER_TYPE_MOBILE的字符串数组
        for (String string : cellValue.MEMBER_TYPE_MOBILE()) {
            cellValueDictory.put(string, MEMBER_TYPE_MOBILE);
        }
        //储存对应MEMBER_TYPE_VIDEO的字符串数组
        for (String string : cellValue.MEMBER_TYPE_VIDEO()) {
            cellValueDictory.put(string, MEMBER_TYPE_VIDEO);
        }
        //储存对应MEMBER_SEX_MAN的字符串数组
        for (String string : cellValue.MEMBER_SEX_MAN()) {
            cellValueDictory.put(string, MEMBER_SEX_MAN);
        }
        //储存对应MEMBER_SEX_WOMAN的字符串数组
        for (String string : cellValue.MEMBER_SEX_WOMAN()) {
            cellValueDictory.put(string, MEMBER_SEX_WOMAN);
        }

    }
}
