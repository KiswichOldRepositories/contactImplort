package cn.showclear.service.impl;

import cn.showclear.entity.base.OrgDeptEntity;
import cn.showclear.entity.common.excel.Excel;
import cn.showclear.entity.common.excel.SheetPlus;
import cn.showclear.entity.common.excel.TableBlock;
import cn.showclear.init.InitBean;
import cn.showclear.service.ExcelService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    InitBean initBean;

    @Autowired
    Excel excel;

    @Override
    public Excel getExcel(String excelPath) throws IOException, InvalidFormatException {
        InputStream inputStream = new FileInputStream(initBean.getExcelConfig().getFileLocation());
        Workbook sheets = WorkbookFactory.create(inputStream);

        OrgDeptEntity XuJian = new OrgDeptEntity();
        XuJian.setDeptName("叙简科技");
        XuJian.setSortIndex(1);
        excel.setDeptEntity(XuJian);

        Iterator<Sheet> iterator = sheets.iterator();
        iterator.forEachRemaining(sheet -> {
            SheetPlus sheetPlus = new SheetPlus();
            excel.getSheets().add(sheetPlus);

            List<TableBlock> tableblock = getTableblock(sheet);


        });

        return null;
    }

    /**
     * 从一个sheet中获取到block
     * 即主要的逻辑
     * @param sheet
     * @return
     */
    public List<TableBlock> getTableblock(Sheet sheet){

        return null;
    }


    @Override
    public void saveExcel(Excel excel) {

    }
}
