package cn.showclear.init;


import cn.showclear.entity.common.ExcelConfig;
import cn.showclear.entity.common.OptionEnum;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 存入了所有的的初始化参数
 * 通过set注入和构造器注入和代理模式， 保证了静态实例和bean的统一
 *
 *
 */
@Component
public class InitBean {
    //单实例操作
    private static InitBean initBean = new InitBean(0);

    //用于bean的默认构造器，将静态实例中的参数传入
    public InitBean() {
    }

    private InitBean(int T) {//这是空构造器...

    }

    public static InitBean getInstance() {
        return initBean;
    }

    //该excel具有的参数
    private ExcelConfig excelConfig;

    //临时产生的配置文件
    private File file;

    //输入的参数对应的操作
    private OptionEnum optionEnum;


    public ExcelConfig getExcelConfig() {
        return initBean.excelConfig;
    }

    public void setExcelConfig(ExcelConfig excelConfig) {
        initBean.excelConfig = excelConfig;
    }

    public File getFile() {
        return initBean.file;
    }

    public void setFile(File file) {
        initBean.file = file;
    }

    public OptionEnum getOptionEnum() {
        return initBean.optionEnum;
    }


    public void setOptionEnum(OptionEnum optionEnum) {
        initBean.optionEnum = optionEnum;
    }

    @Override
    public String toString() {
        return "InitBean{" +
                "excelConfig=" + initBean.getExcelConfig() +
                ", file=" + initBean.getFile() +
                ", optionEnum=" + initBean.getOptionEnum() +
                '}';
    }
}
