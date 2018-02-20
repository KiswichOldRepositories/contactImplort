package cn.showclear.entity.common.excel;

import cn.showclear.entity.base.OrgDeptEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 整个表格
 * 包括了一个List<sheet> 和 表格的部门信息
 */
@Component
public class Excel {
    private List<SheetPlus> sheets;
    private OrgDeptEntity deptEntity;

    public List<SheetPlus> getSheets() {
        return sheets;
    }

    public void setSheets(List<SheetPlus> sheets) {
        this.sheets = sheets;
    }

    public OrgDeptEntity getDeptEntity() {
        return deptEntity;
    }

    public void setDeptEntity(OrgDeptEntity deptEntity) {
        this.deptEntity = deptEntity;
    }
}
