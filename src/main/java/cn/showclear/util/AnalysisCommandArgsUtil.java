package cn.showclear.util;

import cn.showclear.pojo.common.ApplicationInfomation;

import java.util.ArrayList;
import java.util.Stack;

public class AnalysisCommandArgsUtil {

    /**
     * 方式1解析：-形式的命令 -d数据库ip -o端口号 -u数据库账号 -p数据库密码 -l通讯录文件地址
     *
     * @param args 原始的命令参数表
     * @return 解析结果，返回null则说明解析失败
     */
    public static ApplicationInfomation AnalByMethod1(String... args) {
        ApplicationInfomation infomation = new ApplicationInfomation();
        for (String str : args) {
            if (str.indexOf("-") != 0) return null;
            String mode = str.substring(0, 2);
            switch (mode) {
                case "-d":
                    infomation.setDatabaseIp(str.substring(2));
                    break;
                case "-o":
                    infomation.setDatabasePort(Integer.parseInt(str.substring(2)));
                    break;
                case "-u":
                    infomation.setDatabaseUsername(str.substring(2));
                    break;
                case "-p":
                    infomation.setDatabasePassword(str.substring(2));
                    break;
                case "-l":
                    infomation.setExcelLocalPath(str.substring(2));
                    break;
                default:
                    return null;
            }

        }
        if (checkNull(infomation)) return infomation;
        else return null;
    }

    /**
     * 方式2解析：按照参数的顺序 数据库ip 端口号 数据库账号 数据库密码 通讯录文件地址
     *
     * @param args
     * @return
     */
    public static ApplicationInfomation AnalByMethod2(String... args) {
        ApplicationInfomation infomation = new ApplicationInfomation();

        if (args.length >= 5) {
            infomation.setDatabaseIp(args[0]);
            infomation.setDatabasePort(Integer.parseInt(args[1]));
            infomation.setDatabaseUsername(args[2]);
            infomation.setDatabasePassword(args[3]);
            infomation.setExcelLocalPath(args[4]);
            //后面的参数就忽略掉了
            return infomation;
        } else return null;
    }

    /**
     * 方式3解析：通过特征来分辨参数
     * 1.先找到数据库ip split(".")
     * 2.找到文件地址 split("//[\]")
     * 3.找到0-65535之间的数字 ,若有n个，则循环链接数据库直到成功。。。
     * （最差的情况，3*2 ，要链接6次）
     *
     * @param args
     * @return
     */
    public static ApplicationInfomation[] AnalByMethod3(String... args) {
        try {
            ApplicationInfomation[] infomations = new ApplicationInfomation[6];
            ArrayList<Integer> integers = new ArrayList<>();//存储参数的位置
            for (int i = 0; i < 5; i++) integers.add(i);

            for (int i = 0, infomationsLength = infomations.length; i < infomationsLength; i++) {
                infomations[i] = new ApplicationInfomation();
            }

            for (int i = 0; i < args.length; i++) {
                String str = args[i];
                if (str.split(".").length == 4 || str.equals("localhost")) {
                    for (ApplicationInfomation infomation : infomations) infomation.setDatabaseIp(str);
                    integers.remove(new Integer(i));
                    break;
                }
            }


            for (int i = 0; i < args.length; i++) {
                String str = args[i];
                if (str.split("\\\\").length >= 2 || str.split("//").length >= 2) {
                    for (ApplicationInfomation infomation : infomations) infomation.setExcelLocalPath(str);
                    integers.remove(new Integer(i));
                    break;
                }
            }

            //排序 将integers剩下的三个参数进行排序 将结果存入arrayss
            //就是先将0,1,2进行排列
            //。。直接写出来效率更高
            Integer[][] arrays = {{0, 1, 2}, {0, 2, 1}, {1, 0, 2}, {1, 2, 0}, {2, 1, 0}, {2, 0, 1}};

            //填充

                for (int i = 0; i < infomations.length; i++) {
                    try {
                        infomations[i].setDatabasePort(Integer.parseInt(args[integers.get(arrays[i][0])]));
                        infomations[i].setDatabaseUsername(args[integers.get(arrays[i][1])]);
                        infomations[i].setDatabasePassword(args[integers.get(arrays[i][2])]);
                    } catch (Exception ignored) {
                        //试着加入，失败则清空
                        infomations[i] = null;
                    }
                }

            return infomations;

        } catch (Exception e) {

        }

        return null;
    }

    /**
     * 测参数是否全部填充
     *
     * @param infomation
     * @return
     */
    public static boolean checkNull(ApplicationInfomation infomation) {
        return infomation.getDatabaseIp() != null && infomation.getDatabasePassword() != null && infomation.getDatabasePort() != null
                && infomation.getDatabaseUsername() != null && infomation.getExcelLocalPath() != null;
    }


}
