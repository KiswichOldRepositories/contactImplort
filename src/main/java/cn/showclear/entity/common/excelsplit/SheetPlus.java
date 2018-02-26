package cn.showclear.entity.common.excelsplit;

import java.util.ArrayList;
import java.util.List;

/**
 * 即其中一个分公司
 */
public class SheetPlus {
    private List<TableBlock> tableBlocks;
    private Integer rowCount;
    private Integer sheetIndex;

    public SheetPlus() {
        tableBlocks = new ArrayList<>();
    }

    public List<TableBlock> getTableBlocks() {
        return tableBlocks;
    }

    public void setTableBlocks(List<TableBlock> tableBlocks) {
        this.tableBlocks = tableBlocks;
    }

    public Integer getRowCount() {
        return rowCount;
    }

    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }

    public Integer getSheetIndex() {
        return sheetIndex;
    }

    public void setSheetIndex(Integer sheetIndex) {
        this.sheetIndex = sheetIndex;
    }
}
