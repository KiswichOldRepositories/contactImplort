package cn.showclear.pojo.common;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用来代替TableHead类,储存了各类基本的信息
 */
public class TableHeadRe {
    //储存了表结构 ,这样在遍历表的时候可以根据列数找到列对应的含义
    private Map<Integer,TableHeadEnum> tableHeadMap;
    //用arraylist实现的储存表结构，因为列数是唯一的
    @Deprecated
    private List<TableHeadEnum> tableHeadList;
    //开始列数
    private Integer startCell;
    //结束列数
    private Integer endCell;

    public TableHeadRe() {
        this.tableHeadList = new ArrayList<>();
        this.tableHeadMap = new HashMap<>();
    }

    public Map<Integer, TableHeadEnum> getTableHeadMap() {
        return tableHeadMap;
    }

    public void setTableHeadMap(Map<Integer, TableHeadEnum> tableHeadMap) {
        this.tableHeadMap = tableHeadMap;
    }

    public List<TableHeadEnum> getTableHeadList() {
        return tableHeadList;
    }

    public void setTableHeadList(List<TableHeadEnum> tableHeadList) {
        this.tableHeadList = tableHeadList;
    }


    public Integer getStartCell() {
        return startCell;
    }

    public void setStartCell(Integer startCell) {
        this.startCell = startCell;
    }

    public Integer getEndCell() {
        return endCell;
    }

    public void setEndCell(Integer endCell) {
        this.endCell = endCell;
    }
}
