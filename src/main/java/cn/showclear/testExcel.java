package cn.showclear;

import cn.showclear.pojo.common.TableHeadEnum;
import cn.showclear.pojo.common.TableHeadRe;
import cn.showclear.pojo.entity.MemberEntity;
import cn.showclear.util.ReadConfig;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;
import cn.showclear.pojo.EntityManager;
import cn.showclear.util.TableHeadUtil;

import java.io.FileInputStream;
import java.util.*;


public class testExcel {

    @Test
    public void testExcel1() {
        SqlSession session = EntityManager.getSession();
        try {
            FileInputStream fileInputStream = new FileInputStream("E://叙简科技内部通讯录.xlsx");
            Workbook sheets = WorkbookFactory.create(fileInputStream);

            Iterator<Sheet> sheetIterator = sheets.sheetIterator();
            while (sheetIterator.hasNext()) {
                Sheet next = sheetIterator.next();
                System.out.println(next.getSheetName());
            }

            Sheet sheet = sheets.getSheetAt(3);

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
            ReadConfig.read();
            System.out.println(EntityManager.tableHeadDictory);
            ;
            FileInputStream fileInputStream = new FileInputStream("E://叙简科技内部通讯录.xlsx");
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


            while (true) {
                Row row = sheet.getRow(rowNum++);
                if (row == null) break;

                while (true) {
                    Cell cell = row.getCell(cellNum++);
                    if (cell == null) break;
                    switch (cell.getCellTypeEnum()) {
                        case STRING:

                            System.out.println("1 " + cell.getStringCellValue().trim());
                            break;
                        case NUMERIC:
                            System.out.println(String.valueOf((long) cell.getNumericCellValue()).trim());
                            break;
                        case BLANK:
                            System.out.println("哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈");
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

    @Test
    public void finalTest() {
        ReadConfig.read();
        try {
            FileInputStream fileInputStream = new FileInputStream("E://叙简科技内部通讯录.xlsx");
            Workbook sheets = WorkbookFactory.create(fileInputStream);
            Iterator<Sheet> sheetIterator = sheets.sheetIterator();

            //1.遍历每个sheet
            while (sheetIterator.hasNext()) {
                Sheet sheet = sheetIterator.next();
                //2.遍历行 得到表头行数
                int tableHeadRow = 0;
                int rowIndexMark = 0;
                while (rowIndexMark <= 10) {
                    Cell cell = sheet.getRow(rowIndexMark++).getCell(0);
                    if (cell == null) break;
                    //测试写法 不能直接对比字符串
                    if (TableHeadUtil.getText(cell).equals("部门") || TableHeadUtil.getText(cell).equals("")) {
                        tableHeadRow = rowIndexMark - 1;
                        break;
                    }
                }
                int cellNum = 0;
                Row tableRow = sheet.getRow(tableHeadRow);

                //3.根据表头，开始分块
                List<TableHeadRe> tableHeadRes = new ArrayList<>();
                TableHeadRe tableHeadRe = new TableHeadRe();
                tableHeadRes.add(tableHeadRe);
                tableHeadRe.setStartCell(0);

                while (true) {
                    Cell cell = tableRow.getCell(cellNum);
                    //当遍历到行尾时停止
                    if (cell == null) {
                        tableHeadRe.setEndCell(cellNum - 1);
                        break;
                    }
                    if (!TableHeadUtil.add2HeadTable(cell, cellNum, tableHeadRe)) {
                        //遇到部门，分块重传
                        tableHeadRe.setEndCell(cellNum - 1);
                        tableHeadRe = new TableHeadRe();

                        tableHeadRe.setStartCell(cellNum);
                        tableHeadRes.add(tableHeadRe);
                        TableHeadUtil.add2HeadTable(cell, cellNum, tableHeadRe);
                    }
                    cellNum++;
                }


                //根据每个块，得到相应的部门信息和人员信息。
                for (TableHeadRe table : tableHeadRes) {
                    //从数据行开始便利
                    int rowIndex = rowIndexMark;
                    //储存多级部门的信息
                    String[] deptLevel = new String[table.getEndCell()-table.getStartCell()];


                    while (true) {
                        //遍历行
                        Row row = sheet.getRow(rowIndex++);
                        if (row == null) break;
                        //用来储存人的信息 每一块的单个行只有一个人
                        MemberEntity member = new MemberEntity();
                        //用来储存拓展信息
                        Map<String,String> expMap = new HashMap<>();

                        //遍历该块的每一个单元格
                        for (int i = table.getStartCell(); i <= table.getEndCell(); i++) {
                            //
                            String cellText = TableHeadUtil.getText(row.getCell(i));
                            System.out.println(cellText);
                            //该列对应的属性
                            switch ( table.getTableHeadMap().get(i)){
                                case Dept:
                                    //对部门的操作 判断是第几级部门 储存 /人物对应最下级部门
                                    //i-table.startCell 即为当前的部门级数
                                    //有一种情况 前面是3级，后面是1级/2级部门的处理方法！-待定
                                    //以及人如何确定他是属于哪个部门的


                                    break;
                                case name:
                                    member.setMemName(cellText);
                                    break;
                                case phoneNumber:
                                    member.addPhoneNumber(cellText);
                                    break;
                                case tellphoneNumber:
                                    member.setMemMobile(cellText);
                                    break;
                                case email:
                                    member.setMemEmail(cellText);
                                    break;
                                case fex:
                                    member.setMemFax(cellText);
                                    break;
                                case deptExt:
                                    //这里怎们拿到key sheet.getRow(rowIndexMark).getCell(i) 可以拿到 不够优雅2333

                                    //expMap.put(,cellText);
                                    break;
                            }


                        }
                        //遍历结束 储存人物信息和人物的拓展信息

                        System.out.println("----------------------------");

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //1.遍历每个sheet
        //2.遍历行 得到表头行数
        //3.根据表头，开始分块
        //4.按块遍历
        // 所在列在部门列内 进行部门结构保存
        //所在列不在... 进行人员结构的保存
        //.4
        //.1
    }

    @Test
    public void testDate() {
        try {
            FileInputStream fileInputStream = new FileInputStream("E://叙简科技内部通讯录.xlsx");
            Workbook sheets = WorkbookFactory.create(fileInputStream);
            Iterator<Sheet> sheetIterator = sheets.sheetIterator();

            Sheet sheet = sheets.getSheetAt(3);
            Cell cell = sheet.getRow(2).getCell(8);
            System.out.println(TableHeadUtil.getText(cell));
            System.out.println(cell);
            System.out.println(new Date(new Double(cell.getNumericCellValue()).longValue()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        System.out.println(1);
    }
}
