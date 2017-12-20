package cn.showclear.service;

import cn.showclear.pojo.EntityManager;
import cn.showclear.pojo.common.TableHeadEnum;
import cn.showclear.pojo.common.TableHeadRe;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.util.LocaleUtil;

import java.text.SimpleDateFormat;

/**
 * 用于对表头操作的一串工具
 */
public class TableHeadService {

    /**
     * 识别单元格类型，返回其中的值（string）
     * 数字单元格就把数字转换成string返回，字符串单元格trim()后直接返回，空格（一般出现在合并的单元格中）返回null
     *
     * @param cell
     * @return
     */
    public static String getText(Cell cell) {
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
                    text= sdf.format(cell.getDateCellValue());
                }else {//就是一个数字格式的单元格
                    text = String.valueOf((long) cell.getNumericCellValue()).trim();
                }
                break;
            case BLANK:
                break;
        }
        return text;
    }

    /**
     * 根据表头的字符串，填充对应的map集合
     *
     * @param cell    单元格
     * @param cellNum 单元格所在的列位置
     * @return {true:继续，false：需要新建一个集合}
     */
    public static boolean add2HeadTable(Cell cell, Integer cellNum, TableHeadRe tableHeadRe) {
        //在map头中写入表头信息
//        tableHeadRe.getTableHeadMap().put(cellNum, EntityManager.tableHeadDictory.get(getText(cell)));
        switch (cell.getCellTypeEnum()) {
            case STRING:
                TableHeadEnum headEnum = EntityManager.tableHeadDictory.get(cell.getStringCellValue().trim());
                //当传入的集合不为空而且传入了一个部门单元格的时候，即发现了新的部门单元格后
                if (headEnum == TableHeadEnum.Dept && !tableHeadRe.getTableHeadMap().isEmpty()) return false;
                tableHeadRe.getTableHeadMap().put(cellNum, headEnum);
                break;

            case NUMERIC://理论上是不会有数字的
                tableHeadRe.getTableHeadMap()
                        .put(cellNum, EntityManager.tableHeadDictory
                                .get(String.valueOf((long) cell.getNumericCellValue()).trim()));
                break;
            case BLANK:
                //对于空表 ，因为是合并单元格的结果，直接使其值等于上一个单元格就好了
                tableHeadRe.getTableHeadMap()
                        .put(cellNum, tableHeadRe.getTableHeadMap().get(cellNum - 1));
                break;
        }
        return true;
    }


}
