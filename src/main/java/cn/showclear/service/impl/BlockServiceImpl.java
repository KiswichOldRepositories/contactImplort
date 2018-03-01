package cn.showclear.service.impl;

import cn.showclear.entity.common.excelsplit.Excel;
import cn.showclear.entity.common.excelsplit.SheetPlus;
import cn.showclear.entity.common.excelsplit.TableBlock;
import cn.showclear.entity.common.excelsplit.TableEnum;
import cn.showclear.exception.TableStandardException;
import cn.showclear.service.BlockService;
import cn.showclear.service.CellService;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class BlockServiceImpl implements BlockService {

    @Autowired
    Workbook workbook;
    @Autowired
    CellService cellService;

    @Override
    public Excel getBlock() throws TableStandardException {
        final Excel excel = new Excel();

        Iterator<Sheet> iterator = workbook.iterator();
        int sheetIndex = 0;

        Sheet sheet = null;
        while (iterator.hasNext()) {
            sheet = iterator.next();


            SheetPlus sheetPlus = new SheetPlus();
            sheetPlus.setSheetIndex(sheetIndex);
            excel.getSheetPluses().add(sheetPlus);


            //确定表头所在的位置
            int rowIndex = 0;
            while (cellService.changeCell(sheet.getRow(rowIndex).getCell(0)) != TableEnum.Dept) {
                rowIndex++;
            }

            int cellIndex = 0;
            TableBlock tableBlock = new TableBlock();
            tableBlock.setStartRow(0);
            tableBlock.setHeadRow(rowIndex);

            sheetPlus.getTableBlocks().add(tableBlock);

            boolean newBlockFlag = false;

            //这样写感觉要被打23333
            for (TableEnum tableEnum = cellService.changeCell(sheet.getRow(rowIndex).getCell(cellIndex));
                 tableEnum != null;
                 cellIndex++, tableEnum = cellService.changeCell(sheet.getRow(rowIndex).getCell(cellIndex))) {

                if (newBlockFlag && tableEnum == TableEnum.Dept) {//当遇到新的dept，开启一个新的分块
                    tableBlock.setEndRow(cellIndex - 1);
                    newBlockFlag = false;

                    tableBlock = new TableBlock();
                    tableBlock.setStartRow(cellIndex);
                    tableBlock.setHeadRow(rowIndex);

                    sheetPlus.getTableBlocks().add(tableBlock);
                } else if (tableEnum != TableEnum.Dept) {
                    newBlockFlag = true;
                }

                tableBlock.getTableEnumMap().put(cellIndex, tableEnum);
                if (tableEnum == TableEnum.name) tableBlock.setNameCell(cellIndex);
            }
            tableBlock.setEndRow(cellIndex - 1);

//            if (cellService.changeCell(sheet.getRow(rowIndex).getCell(cellIndex - 1)) != TableEnum.email)
//                throw new TableStandardException("sheet[" + sheetIndex + "]中表格应以email作为结尾，请检查表格是否有多余的空单元格;或者检查该列是否已配置成email");

            sheetIndex++;
        }


        return excel;
    }
}
