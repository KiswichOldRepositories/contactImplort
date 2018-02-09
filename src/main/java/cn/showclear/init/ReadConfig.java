package cn.showclear.init;

import cn.showclear.entity.common.ExcelConfig;
import cn.showclear.exception.ArgsMissException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * 读取命令行参数，转化成特定的参数
 */
public class ReadConfig {

    /**
     * 读取相关配置 并分类
     * @param args
     * @return
     */
    public static void read(String[] args) throws ArgsMissException, IOException {
        if(readHelp(args)) {//打印帮助信息
        }else{//读取表的参数
            Constant.excelConfig = readConfig(args);
            createTempConfigFile(Constant.excelConfig);
        }
    }

    /**
     * 若参数中有关于help的信息 ，则返回true
     * @param args
     * @return
     */
    public static boolean readHelp(String[] args){
        return Arrays.stream(args).anyMatch(arg->arg.equals("-help") || arg.equals("--help") || arg.equals("help"));
    }

    /**
     * 读取表的参数
     * @param args
     * @return
     * @throws ArgsMissException
     */
    public static ExcelConfig readConfig(String[] args) throws ArgsMissException {
        //默认参数
        ExcelConfig excelConfig = new ExcelConfig("showclear","showclear","3306");

        Arrays.stream(args).forEach(arg->{
            if(arg.startsWith("-d")){
                excelConfig.setUrl(new StringBuilder().append("jdbc:mysql://")
                        .append(arg.substring(2))
                        .append("/DB_SC_CORE?useUnicode=true&characterEncoding=utf8").toString());
            }else if(arg.startsWith("-o")){
                excelConfig.setPort(arg.substring(2));
            }else if(arg.startsWith("-u")){
                excelConfig.setUser(arg.substring(2));
            }else if(arg.startsWith("-p")){
                excelConfig.setPassword(arg.substring(2));
            }else if(arg.startsWith("-l")){
                excelConfig.setFileLocation(arg.substring(2));
            }
        });

        if(excelConfig.checkNone()) throw new ArgsMissException("参数输入错误 或者没有输入必要的参数");
        return excelConfig;
    }

    /**
     * 根据输入的配置信息，产生临时的配置文件
     * @param excelConfig
     */
    public static void createTempConfigFile(ExcelConfig excelConfig) throws IOException {

        File file = new File("comment.properties");
        if(file.exists()) file.delete();
        file.createNewFile();
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.append("spring.datasource.url=").append(excelConfig.getUrl()).append("\n")
                    .append("spring.datasource.username=").append(excelConfig.getUser()).append("\n")
                    .append("spring.datasource.password=").append(excelConfig.getPassword()).append("\n")
                    .append("spring.datasource.driver-class-name=").append("com.mysql.jdbc.Driver");
            fileWriter.flush();
            //保存临时配置文件的引用
            Constant.file = file;
        }
    }

}
