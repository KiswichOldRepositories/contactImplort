package cn.showclear.service;

import cn.showclear.pojo.common.TableHeadRe;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.ArrayList;
import java.util.List;

public class ExclService {

    public static List<TableHeadRe> getTableHeadSplit(Row tableRow){

        int cellNum = 0;
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
            if (!TableHeadService.add2HeadTable(cell, cellNum, tableHeadRe)) {
                //遇到部门，分块重传
                tableHeadRe.setEndCell(cellNum - 1);//记录上一块的结尾
                tableHeadRe = new TableHeadRe();    //开启新的块
                tableHeadRe.setStartCell(cellNum);
                tableHeadRes.add(tableHeadRe);
                TableHeadService.add2HeadTable(cell, cellNum, tableHeadRe);
            }
            cellNum++;
        }
        return tableHeadRes;
    }
}
