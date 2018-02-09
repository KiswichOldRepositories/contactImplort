import org.junit.Test;

import java.util.Arrays;

public class testArray {
    /**
     * 测试结果为[1,222]，即对string[]是个引用对象
     */
    @Test
    public void testArray() {
        String[] strings = {"111","222"};
        testArray.curArray(strings);
        System.out.println(Arrays.toString(strings));
    }

    public static void curArray(String[] strings) {
        if (strings != null && strings.length > 1) strings[0] = "1";
    }


    /**
     * 测试结果为 [1,222] 一样
     */
    @Test
    public void testIntArray(){
        int[] ints = {111,222};
        testArray.cutInrArray(ints);
        System.out.println(Arrays.toString(ints));

    }

    public static void cutInrArray(int[] ints){
        if(ints.length>1) ints[0] = 1;
    }
}
