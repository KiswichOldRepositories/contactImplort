package cn.showclear.entity.common.excelsplit;

import java.util.ArrayList;
import java.util.List;

/**
 * 扫描得出的最终实体
 */
public class Excel {
    private List<SheetPlus> sheetPluses;

    public Excel() {
        sheetPluses = new ArrayList<>();
    }

    public List<SheetPlus> getSheetPluses() {
        return sheetPluses;
    }

    public void setSheetPluses(List<SheetPlus> sheetPluses) {
        this.sheetPluses = sheetPluses;
    }
}
