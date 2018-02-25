package cn.showclear.service.impl;

import cn.showclear.entity.common.excelsplit.Excel;
import cn.showclear.entity.common.excelsplit.SheetPlus;
import cn.showclear.entity.common.excelsplit.TableBlock;
import cn.showclear.entity.common.excelsplit.TableEnum;
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
    public Excel getBlock() {
        final Excel excel = new Excel();

        Iterator<Sheet> iterator = workbook.iterator();
        iterator.forEachRemaining(sheet -> {
            SheetPlus sheetPlus = new SheetPlus();
            excel.getSheetPluses().add(sheetPlus);

            //确定表头所在的位置
            int rowIndex = 0;
            while (cellService.changeCell(sheet.getRow(rowIndex).getCell(0)) != TableEnum.Dept) {
                rowIndex++;
            }

            int cellIndex = 0;
            TableBlock tableBlock = new TableBlock();
            tableBlock.setStartRow(0);
            sheetPlus.getTableBlocks().add(tableBlock);

            //这样写感觉要被打23333
            for (TableEnum tableEnum = cellService.changeCell(sheet.getRow(rowIndex).getCell(cellIndex));
                 tableEnum != null;
                 cellIndex++, tableEnum = cellService.changeCell(sheet.getRow(rowIndex).getCell(cellIndex))){



            }



        });

        return excel;
    }
}
