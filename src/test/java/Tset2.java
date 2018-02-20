import org.junit.Test;

import java.util.Arrays;

public class Tset2 {
    @Test
    public void testSubstring(){
        String str = "-d33060";
        System.out.println(str.substring(2));
    }

    @Test
    public void testSplitSpace(){
        String str = "jk jk jss";
        System.out.println(Arrays.toString(str.split(" ")));
    }
}
