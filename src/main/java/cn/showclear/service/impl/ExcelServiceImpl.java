package cn.showclear.service.impl;

import cn.showclear.entity.base.OrgDeptEntity;
import cn.showclear.entity.base.OrgMemberEntity;
import cn.showclear.entity.common.excelsplit.Excel;
import cn.showclear.entity.common.excelsplit.SheetPlus;
import cn.showclear.entity.common.excelsplit.TableBlock;
import cn.showclear.exception.TableStandardException;
import cn.showclear.init.InitBean;
import cn.showclear.repository.jpa.DeptRepository;
import cn.showclear.repository.jpa.MemberRepository;
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
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

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
    @Autowired
    DeptRepository deptRepository;
    @Autowired
    MemberRepository memberRepository;

    @Override
    public OrgDeptEntity getExcel(String excelPath) throws IOException, InvalidFormatException, TableStandardException {
        Excel block = blockService.getBlock();

        //关键道具
        ArrayList<OrgDeptEntity> levelDepts = new ArrayList<>();

        levelDepts.add(mainDept);

        for (SheetPlus sheetPlus : block.getSheetPluses()) {
            Sheet sheet = workbook.getSheetAt(sheetPlus.getSheetIndex());

            OrgDeptEntity subDept = new OrgDeptEntity(sheet.getSheetName());

            deptService.addDept(levelDepts, subDept, 1);

            for (TableBlock tableBlock : sheetPlus.getTableBlocks()) {

                int columnIndex = tableBlock.getHeadRow() + 1;

                OrgDeptEntity orgDeptEntity = null;


                while (sheet.getRow(columnIndex) != null  && StringUtils.isNoneBlank(cellService.getText(sheet.getRow(columnIndex).getCell(tableBlock.getNameCell()))) ) {
                    //记录父子关系

                    OrgMemberEntity orgMemberEntity = new OrgMemberEntity();
                    Map<String, String> memExt = new HashMap<>();

                    for (int cellIndex = tableBlock.getStartRow(); cellIndex < tableBlock.getEndRow()+1; cellIndex++) {

                        Cell cell = sheet.getRow(columnIndex).getCell(cellIndex);
                        String text = cellService.getText(cell);

                        switch (tableBlock.getTableEnumMap().get(cellIndex)) {

                            case Dept:
                                if (StringUtils.isNoneBlank(text)) {//部门名字非空

                                    Integer deptlevel = cellIndex - tableBlock.getStartRow() + 2; //based 2
                                    orgDeptEntity = new OrgDeptEntity(text);
                                    deptService.addDept(levelDepts, orgDeptEntity, deptlevel);
                                }
                                break;
                            case orgCode:
                                levelDepts.get(levelDepts.size() - 1).setOrgCode(text);
                                break;
                            case name:
                                orgMemberEntity.setMemName(text);
                                break;
                            case phoneNumber:
                                String[] split = text.split("/");
                                Arrays.stream(split).forEach(orgMemberEntity::addPhoneNum);
                                break;
                            case tellphoneNumber:
                                orgMemberEntity.setMemMobile(text);
                                break;
                            case fex:
                                orgMemberEntity.setMemFax(text);
                                break;
                            case type:
                                orgMemberEntity.setMemType(Integer.parseInt(text));
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
                                orgMemberEntity.setSex(Integer.parseInt(text));
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

    /**
     * 将一个excel实体存到数据库里
     * 广度优先遍历
     *
     * @param excel
     */
    @Override
    @Transactional
    public void saveExcel(OrgDeptEntity excel) {
        OrgDeptEntity loop = excel;
        Stack<OrgDeptEntity> stack = new Stack<>();
        //第一层先入栈
        stack.push(excel);

        while (!stack.isEmpty()) {
            OrgDeptEntity peek = stack.pop();

            saveDeptAndMember(peek);
            //子元素入栈
            peek.getChildDept().forEach(stack::push);
        }
    }

    /**
     * 存储
     * @param orgDeptEntity
     */
    public void saveDeptAndMember(OrgDeptEntity orgDeptEntity){
        List<OrgDeptEntity> deptEntities = deptRepository.findByPathName(orgDeptEntity.getPathName());
        if (!deptEntities.isEmpty()) {
            deptService.copyDept(deptEntities.get(0),orgDeptEntity);
        }
        deptRepository.save(orgDeptEntity);

        List<OrgMemberEntity> childMember = orgDeptEntity.getChildMember();

        for (int i = 0; i < childMember.size(); i++) {
            OrgMemberEntity orgMemberEntity = childMember.get(i);
            List<OrgMemberEntity> member = memberRepository.findByMemEmail(orgMemberEntity.getMemEmail());

            if (!member.isEmpty()) {
                deptService.copyMember(member.get(0),orgMemberEntity);
            }
            memberRepository.save(orgMemberEntity);
        }
    }

    /**
     * @param excel
     * @deprecated 递归爆栈了！！！
     */
    @Transactional
    @Deprecated
    public void saveExcelde(OrgDeptEntity excel) {
        //理论上至多只会找到一个
        saveDeptAndMember(excel);

        for (OrgDeptEntity orgDeptEntity : excel.getChildDept()) {
            saveExcel(orgDeptEntity);
        }
    }
}
