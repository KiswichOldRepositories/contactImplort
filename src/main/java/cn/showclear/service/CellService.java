package cn.showclear.service;

import cn.showclear.entity.common.excelsplit.TableEnum;
import org.apache.poi.ss.usermodel.Cell;

/**
 * 用来整备单元格的业务
 */
public interface CellService {
    /**
     * 将单元格转换成枚举的类型
     * @return
     */
    public TableEnum changeCell(Cell cell);

    /**
     * 读取单元格中的字符串
     * @param cell
     * @return
     */
    public String getText(Cell cell);
}
