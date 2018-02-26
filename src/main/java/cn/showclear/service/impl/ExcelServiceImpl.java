package cn.showclear.service.impl;

import cn.showclear.entity.base.OrgDeptEntity;
import cn.showclear.entity.base.OrgMemberEntity;
import cn.showclear.entity.common.excelsplit.Excel;
import cn.showclear.entity.common.excelsplit.SheetPlus;
import cn.showclear.entity.common.excelsplit.TableBlock;
import cn.showclear.exception.TableStandardException;
import cn.showclear.init.InitBean;
import cn.showclear.service.BlockService;
import cn.showclear.service.CellService;
import cn.showclear.service.DeptService;
import cn.showclear.service.ExcelService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    InitBean initBean;
    @Autowired
    OrgDeptEntity mainDept;
    @Autowired
    Workbook workbook;
    @Autowired
    BlockService blockService;
    @Autowired
    CellService cellService;
    @Autowired
    DeptService deptService;

    @Override
    public OrgDeptEntity getExcel(String excelPath) throws IOException, InvalidFormatException, TableStandardException {
        Excel block = blockService.getBlock();

        //关键道具
        ArrayList<OrgDeptEntity> levelDepts = new ArrayList<>();

        levelDepts.add(mainDept);

        for (SheetPlus sheetPlus : block.getSheetPluses()) {
            Sheet sheet = workbook.getSheetAt(sheetPlus.getSheetIndex());

            OrgDeptEntity subDept = new OrgDeptEntity(sheet.getSheetName());

            deptService.addDept(levelDepts,subDept,1);

            for (TableBlock tableBlock : sheetPlus.getTableBlocks()) {

                int columnIndex = tableBlock.getHeadRow() + 1;

                OrgDeptEntity orgDeptEntity = null;


                while (sheet.getRow(columnIndex)!=null && sheet.getRow(columnIndex).getCell(0) != null) {
                    //记录父子关系

                    OrgMemberEntity orgMemberEntity = new OrgMemberEntity();
                    Map<String, String> memExt = new HashMap<>();

                    for (int cellIndex = tableBlock.getStartRow(); cellIndex < tableBlock.getEndRow() ; cellIndex++) {

                        Cell cell = sheet.getRow(columnIndex).getCell(cellIndex);
                        String text = cellService.getText(cell);

                        switch (tableBlock.getTableEnumMap().get(cellIndex)) {

                            case Dept:
                                if (StringUtils.isNoneBlank(text)) {//部门名字非空

                                    Integer deptlevel = cellIndex - tableBlock.getStartRow() + 2; //based 2
                                    orgDeptEntity = new OrgDeptEntity(text);
                                    deptService.addDept(levelDepts, orgDeptEntity,deptlevel);
                                }
                                break;
                            case orgCode:
                                levelDepts.get(levelDepts.size() - 1).setOrgCode(text);
                                break;
                            case name:
                                orgMemberEntity.setMemName(text);
                                break;
                            case phoneNumber:
                                orgMemberEntity.addPhoneNum(text);
                                break;
                            case tellphoneNumber:
                                orgMemberEntity.setMemMobile(text);
                                break;
                            case fex:
                                orgMemberEntity.setMemFax(text);
                                break;
                            case type:
                                orgMemberEntity.setMemType((byte) Integer.parseInt(text));
                                break;
                            case memCode:
                                orgMemberEntity.setMemCode(text);
                                break;
                            case deptExt:
                                memExt.put(
                                        cellService.getText(sheet.getRow(tableBlock.getHeadRow()).getCell(cellIndex)),
                                        text
                                );
                                break;
                            case sex:
                                orgMemberEntity.setSex((byte) Integer.parseInt(text));
                                break;
                            case email:
                                orgMemberEntity.setMemEmail(text);
                                break;
                            default:
                                break;
                        }


                    }
                    try {
                        if (!memExt.isEmpty())
                            orgMemberEntity.setDeptExt(new ObjectMapper().writeValueAsString(memExt));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }

                    deptService.addMember(levelDepts.get(levelDepts.size() - 1), orgMemberEntity);
                    columnIndex++;
                }
            }
        }

        return mainDept;
    }


    @Override
    public void saveExcel(OrgDeptEntity excel) {

    }
}
