package util;

import pojo.common.ApplicationInfomation;

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
        if(checkNull(infomation)) return infomation;
        else return null;
    }

    /**
     * 方式2解析：按照参数的顺序 数据库ip 端口号 数据库账号 数据库密码 通讯录文件地址
     *
     * @param args
     * @return
     */
    public static ApplicationInfomation AnalByMethod2(String... args) {

        return null;
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
    public static ApplicationInfomation AnalByMethod3(String... args) {

        return null;
    }

    /**
     * 测参数是否全部填充
     * @param infomation
     * @return
     */
    public static boolean checkNull(ApplicationInfomation infomation){
        return infomation.getDatabaseIp() != null && infomation.getDatabasePassword() != null && infomation.getDatabasePort() != null
                && infomation.getDatabaseUsername() != null && infomation.getExcelLocalPath() != null;
    }


}
