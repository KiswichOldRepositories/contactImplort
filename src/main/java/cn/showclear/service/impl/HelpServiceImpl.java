package cn.showclear.service.impl;

import javax.print.DocFlavor;
import java.io.IOException;
import java.io.InputStream;

/**
 * 有关于帮助的业务
 */
public class HelpServiceImpl {

    /**
     * 将包内的/doc/readme.md 打印到控制台
     */
    public void helpImfo(){
        InputStream inputStream = this.getClass().getResourceAsStream("/readme.md");
        byte[] bytes = new byte[1024*1024];
        try {
            int len = inputStream.read(bytes);
            System.out.println(new String(bytes,0,len,"utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
