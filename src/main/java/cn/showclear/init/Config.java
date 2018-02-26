package cn.showclear.init;

import cn.showclear.entity.base.OrgDeptEntity;
import cn.showclear.entity.common.ExcelConfig;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * spring boot 的配置项
 */
@Configuration
public class Config {

    @Autowired Constant constant;

    /**
     * 获取主部门
     * @return 单实例
     */
    @Bean
    @Order(99)
    public OrgDeptEntity getMainDept(){
        return new OrgDeptEntity(constant.getMainDept(),1,constant.getMainDept());
    }

    @Bean
    @Order(100)
    public Workbook getExcel() throws IOException, InvalidFormatException {
        InputStream inputStream = new FileInputStream(InitBean.getInstance().getExcelConfig().getFileLocation());
        return  WorkbookFactory.create(inputStream);
    }



}
