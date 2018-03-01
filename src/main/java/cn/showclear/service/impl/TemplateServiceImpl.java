package cn.showclear.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 有关于模板的业务
 * 由于业务简单，为了速度，不需要使用spring环境，
 */
public class TemplateServiceImpl {

    /**
     * 生成默认的模板配置(application.properties)，只生成excel表头的那一部分
     */
    public void createTemplate() {
        File file = new File("application.properties");
        if (!file.exists()) {
            try {
                file.createNewFile();
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.append("# 关于表头的配置项\n" +
                        "excelsplit.dept= ;部门\n" +
                        "excelsplit.name=姓名\n" +
                        "excelsplit.phoneNumber=分机号;固话;内线\n" +
                        "excelsplit.tellphoneNumber=手机\n" +
                        "excelsplit.email=E-mail;邮箱\n" +
                        "excelsplit.fex=传真\n" +
                        "excelsplit.ext=生日;职务");
                fileWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("配置文件已经存在");
        }
    }

    /**
     * 生成另一种配置文件，application.yml
     */
    public void createYmlTemplate() {
        File file = new File("application.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
                FileOutputStream outputStream = new FileOutputStream(file);
                outputStream.write(("excelsplit:\n" +
                        "  dept: ;部门\n" +
                        "  name: 姓名\n" +
                        "  phoneNumber: 分机号;固话;内线\n" +
                        "  tellphoneNumber: 手机\n" +
                        "  email: E-mail;邮箱\n" +
                        "  fex: 传真\n" +
                        "  ext: 生日;职务").getBytes("utf-8"));
                outputStream.flush();
                System.out.println("配置文件已创建于脚本同目录，名为application.yml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("配置文件已经存在");
        }
    }
}
