package cn.showclear.util;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import org.junit.Test;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringAnalysisUtil {
    /**
     * 将公司总机号和传真号提取出来
     *
     * @param string 例如 "，传真号：57898719。公司总机号：57898699"
     * @return {公司总机号，传真号}例如 {"57898699","57898719"}
     */
    public static String[] getPhoneAndFex(String string) {
        String[] strings = new String[2];
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(string);

        if (string.indexOf("总机") < string.indexOf("传真")) {//总机在前，传真在后
            int i = 0;
            while (matcher.find()) {
                strings[i++] = matcher.group();
            }
        } else {
            int i = 0;
            while (matcher.find()) {
                strings[1 - i++] = matcher.group();
            }
        }
        return strings;
    }

    /**
     * 获取中文的拼音首字母
     * @param name
     * @return
     */
    public static String getFirstLetter(String name){
        try {
            return PinyinHelper.getShortPinyin(name);
        } catch (PinyinException e) {
            e.printStackTrace();
        }
        return null;
    }



    @Test
    public void testRegex() {
        String[] phoneAndFex = getPhoneAndFex("传真号：57898719 。公司总机号：57898699，");
        System.out.println(Arrays.toString(phoneAndFex));
    }
}
