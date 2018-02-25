package cn.showclear.service.impl;

import cn.showclear.entity.common.excelsplit.TableEnum;
import cn.showclear.init.ExcelHeadConfig;
import cn.showclear.service.CellService;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.util.LocaleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class CellServiceImpl implements CellService {
    @Autowired
    ExcelHeadConfig excelHeadConfig;

    @Override
    public TableEnum changeCell(Cell cell) {
        String text = getText(cell);
        TableEnum tableEnum = null;
        if (excelHeadConfig.getDept().contains(text)) tableEnum = TableEnum.Dept;
        else if (excelHeadConfig.getEmail().contains(text)) tableEnum = TableEnum.email;
        else if (excelHeadConfig.getName().contains(text)) tableEnum = TableEnum.name;
        else if (excelHeadConfig.getPhoneNumber().contains(text)) tableEnum = TableEnum.phoneNumber;
        else if (excelHeadConfig.getTellphoneNumber().contains(text)) tableEnum = TableEnum.tellphoneNumber;
        else if (excelHeadConfig.getFex().contains(text)) tableEnum = TableEnum.fex;
        else if (excelHeadConfig.getExt().contains(text)) tableEnum = TableEnum.deptExt;
        return tableEnum;
    }

    @Override
    public String getText(Cell cell) {

        if (cell == null) return null;

        String text = null;
        switch (cell.getCellTypeEnum()) {
            case STRING:
                text = cell.getStringCellValue().trim();
                break;
            case NUMERIC:
                //如果这是时间格式的数字单元格
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", LocaleUtil.getUserLocale());
                    sdf.setTimeZone(LocaleUtil.getUserTimeZone());
                    text = sdf.format(cell.getDateCellValue());
                } else {//就是一个数字格式的单元格
                    text = String.valueOf((long) cell.getNumericCellValue()).trim();
                }
                break;
            case BLANK:
                break;
        }
        return text;
    }
}
