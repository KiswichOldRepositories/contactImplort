import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import org.junit.Test;

import java.util.Date;

public class testPinYinHelper {


    @Test
    /**
     *  2000个单字取拼音头用了 169ms
     *  2000个双字取拼音头用了258ms
     *
     *  2000个双字取没有音标的拼音用了265ms
     *  2000个双字取有音标的拼音用了124ms
     *
     */
    public void testPinYin(){
        try {
            Date date = new Date();
            for(int i = 0; i< 2000;i++){
                String pinyin = PinyinHelper.convertToPinyinString("你好"," ", PinyinFormat.WITH_TONE_MARK);
            }
           System.out.println(PinyinHelper.getShortPinyin("哇哇哇哇"));
            System.out.println(new Date().getTime() - date.getTime());
//            System.out.println(pinyin);
        } catch (PinyinException e) {
            e.printStackTrace();
        }
    }
}
