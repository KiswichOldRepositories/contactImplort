package cn.showclear.controller;

import cn.showclear.constant.Constant;
import cn.showclear.mapper.DeptMapper;
import cn.showclear.mapper.MemberMapper;
import cn.showclear.pojo.EntityEnManager;
import cn.showclear.pojo.common.TableHeadRe;
import cn.showclear.pojo.entity.DeptEntity;
import cn.showclear.pojo.entity.MemberEntity;
import cn.showclear.service.DeptService;
import cn.showclear.service.ExclService;
import cn.showclear.service.TableHeadService;
import cn.showclear.util.ReadConfig;
import cn.showclear.util.StringAnalysisUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.util.*;

public class ExcelControllerImpl implements IController {
    @Override
    public void method() {
        SqlSession session = EntityEnManager.getSession();
        DeptMapper deptMapper = session.getMapper(DeptMapper.class);
        MemberMapper memberMapper = session.getMapper(MemberMapper.class);

        int deptIndex = 2;

        //读取配置
        ReadConfig.read();


        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(EntityEnManager.applicationInfomation.getExcelLocalPath());
            Workbook sheets = WorkbookFactory.create(fileInputStream);
            Iterator<Sheet> sheetIterator = sheets.sheetIterator();

            //先创建总部门 叙简科技
            DeptEntity mainEntity = DeptService.createMainEntity(deptMapper);

            //1.遍历每个sheet
            while (sheetIterator.hasNext()) {
                Sheet sheet = sheetIterator.next();

                int memberIndex = 1;

                //将sheet名加入到叙简科技中 并创建这个部门
                String subDeptName = sheet.getSheetName();
                DeptEntity subEntity = DeptService.createSubEntity(deptMapper, subDeptName, mainEntity, deptIndex);


                //2.遍历行 得到表头行数

                int rowIndexMark = 0;
                rowIndexMark = TableHeadService.getHeadRow(sheet);
                int tableHeadRow = rowIndexMark - 1;


                //2.5 若部门有特殊的拓展信息，则取出来加入之
                subEntity = DeptService.addDeptExp(rowIndexMark,sheet,subEntity);
                //保存分部门
                deptMapper.insertWithName(subEntity);

                //3.根据表头，开始分块
                List<TableHeadRe> tableHeadRes = ExclService.getTableHeadSplit(sheet.getRow(tableHeadRow));

                //根据每个块，得到相应的部门信息和人员信息。
                for (TableHeadRe table : tableHeadRes) {
                    //从存在数据的行开始遍历
                    int rowIndex = rowIndexMark;
//                    储存多级部门的id信息 最多长度不会超过块的总列数。
                    Integer[] deptLevel = new Integer[table.getEndCell() - table.getStartCell()];

                    while (true) {
                        //遍历行
                        Row row = sheet.getRow(rowIndex++);
                        if (row == null || row.getCell(table.getEndCell()) == null) break;

                        //用来储存人的信息 每一块的单个行只有一个人
                        MemberEntity member = new MemberEntity();
                        //用来储存拓展信息
                        Map<String, String> expMap = new HashMap<>();

                        //遍历该块的每一个单元格
                        for (int i = table.getStartCell(); i <= table.getEndCell(); i++) {

                            String cellText = TableHeadService.getText(row.getCell(i));
                            //该列对应的属性
                            switch (table.getTableHeadMap().get(i)) {
                                //这么长的switch-case有没有简化的方法。
                                case Dept://这部分过长 应该单独封装
                                    //先判断空
                                    if (row.getCell(i).getCellTypeEnum() != CellType.BLANK) {

                                        int level = i - table.getStartCell();//确定表等级

                                        String path = null;
                                        DeptEntity deptEntity = new DeptEntity();
                                        deptEntity.setDeptName(cellText);

                                        if (level == 0) {
                                            //deptLevel.clear();//第0级出现时，清空等级记录
                                            deptLevel = new Integer[table.getEndCell() - table.getStartCell()];
                                            //0级表的上级部门是sheet部门
                                            deptEntity.setParentId(subEntity.getId());
                                            path = subEntity.getPathName() + "." + cellText;
                                        } else {
                                            deptEntity.setParentId(deptLevel[level - 1]);
                                            path = DeptService.getDeptPath(deptMapper, deptLevel, subEntity.getPathName());
                                        }

                                        DeptEntity entity = deptMapper.selectByParentIdAndName(deptEntity);
                                        if (entity == null) {//该部门不存在
                                            deptEntity.setIsActive(Constant.DEPT_ACTIVE);
                                            deptEntity.setFirstLetter(PinyinHelper.getShortPinyin(cellText));
                                            deptEntity.setSortIndex(deptIndex++);
                                            deptEntity.setPathName(path);

                                            deptMapper.insertWithName(deptEntity);
                                        } else {
                                            deptEntity = entity;
                                        }
                                        deptLevel[level] = deptEntity.getId();
                                    }

                                    break;
                                //
                                case name:
                                    member.setMemName(cellText);
                                    break;
                                //
                                case phoneNumber:
                                    if (StringUtils.isNoneBlank(cellText)) {
                                        //对应一个单元格内写了多个电话号码的处理
                                        String[] split = cellText.split("/");
                                        for (String number : split) {
                                            member.addPhoneNumber(number);
                                        }
                                    }
                                    break;
                                //
                                case tellphoneNumber:
                                    member.setMemMobile(cellText);
                                    break;
                                //
                                case email:
                                    member.setMemEmail(cellText);
                                    break;
                                //
                                case fex:
                                    member.setMemFax(cellText);
                                    break;
                                //
                                case deptExt:
                                    //拓展字段，取到键值对加入到map集合
                                    expMap.put(TableHeadService.getText(sheet.getRow(rowIndexMark - 1).getCell(i)), cellText);
                                    break;
                                //
                                case sex:
                                    member.setSex(EntityEnManager.tableCellValueDictory.get(cellText));
                                    break;
                                //
                                case type:
                                    member.setMemType(EntityEnManager.tableCellValueDictory.get(cellText));
                                    break;
                                //
                                case memCode:
                                    member.setMemCode(cellText);
                                    break;
                                //
                                case orgCode:
                                    member.setOrgCode(cellText);
                                    break;

                            }
                        }

                        if (StringUtils.isNoneBlank(member.getMemName())) {//以姓名作为标志，没有姓名就认为扫描到了无效的行（存在空字符串，不是空表格）
                            //遍历结束 储存人物信息和人物的拓展信息（事后处理）
                            //拓展信息保存
                            if (!expMap.isEmpty()) {
                                ObjectMapper objectMapper = new ObjectMapper();
                                member.setDeptExt(objectMapper.writeValueAsString(expMap));
                            }
                            //姓名拼音首字母保存
                            member.setFirstLetter(StringAnalysisUtil.getFirstLetter(member.getMemName()));
                            //人员的部门保存
                            member.setDeptId(DeptService.getLastDept(deptLevel));
                            //人员的索引保存
                            member.setSortIndex(memberIndex++);

                            memberMapper.insertByMember(member);
                        }
                    }
                }
            }
            session.commit();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            try {
                if (fileInputStream != null) fileInputStream.close();
            } catch (Exception ignored) {
            }

        }


    }
}
