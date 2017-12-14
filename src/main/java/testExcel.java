
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.xmlbeans.SystemProperties;
import org.junit.Test;
import pojo.EntityManager;
import pojo.entity.DeptEntity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class testExcel {

    @Test
    public void testExcel() {
        SqlSession session = EntityManager.getSession();
        try {
            FileInputStream fileInputStream = new FileInputStream("E://叙简科技内部通讯录.xlsx");
            Workbook sheets = WorkbookFactory.create(fileInputStream);

            Iterator<Sheet> sheetIterator = sheets.sheetIterator();
            while (sheetIterator.hasNext()) {
                Sheet next = sheetIterator.next();
                System.out.println(next.getSheetName());
            }

            Sheet sheet = sheets.getSheetAt(0);

//            for(int i = 0 ; i< sheet.getNumMergedRegions();i++){
//                CellRangeAddress region = sheet.getMergedRegion(i);
//                int column = region.getFirstColumn();
//                int row = region.getFirstRow();
//                System.out.println("第 " + i+ "个合并区域 ： " + sheet.getRow(row).getCell(column).getStringCellValue());
//            }
            Row row0 = sheet.getRow(0);
            Row row1 = sheet.getRow(1);
            Row row2 = sheet.getRow(2);
            Row row3 = sheet.getRow(3);
            System.out.println(row2.getCell(4));


            //System.out.println(row0.getCell(0));
//            System.out.println(row0.getCell(1));
//            System.out.println(row0.getCell(2));
//            System.out.println(row0.getCell(2).getCellTypeEnum());
//            System.out.println(sheet.getLastRowNum());
//            System.out.println(row0.getLastCellNum());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPoi() {
        try {
            FileInputStream fileInputStream = new FileInputStream("E://test.xlsx");
            Workbook sheets = WorkbookFactory.create(fileInputStream);
            Sheet sheet = sheets.getSheetAt(0);
//            int headIndex = 1;
//            while (StringUtils.isBlank(sheet.getRow(headIndex).getCell(1).getStringCellValue())) {
//                headIndex++;
//            }
//            System.out.println(headIndex);

            System.out.println(sheet.getLastRowNum());
            //不能用这种方式遍历 因为有些行很乱
//            for(int i = 0;i<sheet.getLastRowNum();i++){
//                int cellNum = sheet.getRow(i).getLastCellNum();
//                System.out.println(cellNum);
//                for(int j = 0; j<cellNum ; j++){
//                    Cell cell = sheet.getRow(i).getCell(j);
//                    switch(cell.getCellTypeEnum()){
//                        case STRING:
//                            System.out.println(cell.getStringCellValue());
//                            break;
//                        case NUMERIC:
//                            System.out.println(cell.getNumericCellValue());
//                            break;
//                        case BLANK:
//                            break;
//                    }
//                }
//                System.out.println("--------------------");
//            }
            int rowNum = 0;
            int cellNum = 0;
            new HashMap<Enum,List>()


            while(true){
                Row row = sheet.getRow(rowNum++);
                if(row == null) break;

                while(true){
                    Cell cell = row.getCell(cellNum++);
                    if(cell == null) break;
                    switch(cell.getCellTypeEnum()){
                        case STRING:

                            System.out.println(cell.getStringCellValue().trim());
                            break;
                        case NUMERIC:
                            System.out.println(String.valueOf((long) cell.getNumericCellValue()).trim());
                           break;
                        case BLANK:
                            break;
                    }
                }

                System.out.println("------------------");
                cellNum = 0;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
