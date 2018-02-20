package cn.showclear.entity.common.excel;

import cn.showclear.entity.base.OrgDeptEntity;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

/**
 * 一个sheet，包括了若干个
 */
public class SheetPlus {

    private List<TableBlock> tableBlocks;

    private Excel parentExcel;
    /* sheet对应的部门 */
    private OrgDeptEntity deptEntity;

    /* 一个sheet的起止列数 */
    private int columCount;

    /* 对应的在poi中的sheet */
    private Sheet sheet;



}
