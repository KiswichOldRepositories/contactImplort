package cn.showclear.entity.common.excel;

import java.util.List;

/**
 * 表块，即一个sheet中的多个并列信息
 */
public class TableBlock {
    private SheetPlus parentSheet;

    private int startColum;
    private int endColum;

    List<ColumPlus> columPluses;


}
