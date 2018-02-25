package cn.showclear.entity.common.excelsplit;

import java.util.HashMap;
import java.util.Map;

/**
 * 为了对应一个sheet中出现列信息时的扫描结果
 *
 * 最终要做的是将扫描的单元格存储到类里面
 */
public class TableBlock {
    //表块起止列
    private Integer startRow;
    private Integer endRow;
    //该表快的部门最高等级
    private Integer deptlevel;

    //Integer : 在excel中的列数 ；TableEnum : 列代表的含义
    private Map<Integer,TableEnum> tableEnumMap;


    public TableBlock() {
        tableEnumMap = new HashMap<>();
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public Integer getEndRow() {
        return endRow;
    }

    public void setEndRow(Integer endRow) {
        this.endRow = endRow;
    }

    public Integer getDeptlevel() {
        return deptlevel;
    }

    public void setDeptlevel(Integer deptlevel) {
        this.deptlevel = deptlevel;
    }

    public Map<Integer, TableEnum> getTableEnumMap() {
        return tableEnumMap;
    }

    public void setTableEnumMap(Map<Integer, TableEnum> tableEnumMap) {
        this.tableEnumMap = tableEnumMap;
    }
}
