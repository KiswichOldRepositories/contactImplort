package cn.showclear.service.impl;

import cn.showclear.ExcelApplication;
import cn.showclear.entity.common.ExcelConfig;
import cn.showclear.init.Config;
import cn.showclear.init.InitBean;
import org.apache.poi.POIXMLFactory;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;

/**
 * BlockServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 27, 2018</pre>
 */
@SpringBootTest(properties = "excelLocation=E://叙简科技内部通讯录.xlsx")
@RunWith(SpringRunner.class)
@Deprecated
public class BlockServiceImplTest {


    @Autowired
    Workbook workbook;
    @Before
    public void before() throws Exception {
        ExcelConfig excelConfig = new ExcelConfig();
        excelConfig.setFileLocation("E://叙简科技内部通讯录.xlsx");

        InitBean.getInstance().setExcelConfig(excelConfig);
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getBlock()
     */
    @Test
    public void testGetBlock() throws Exception {
        workbook = WorkbookFactory.create(new FileInputStream("E://叙简科技内部通讯录.xlsx"));
        String stringCellValue = workbook.getSheetAt(0).getRow(0).getCell(0).getStringCellValue();
        System.out.println(stringCellValue);
//TODO: Test goes here... 
    }


} 
