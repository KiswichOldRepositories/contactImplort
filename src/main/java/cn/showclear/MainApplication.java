package cn.showclear;

import cn.showclear.constant.Constant;
import cn.showclear.mapper.DeptMapper;
import cn.showclear.mapper.MemberMapper;
import cn.showclear.pojo.common.TableHeadRe;
import cn.showclear.pojo.entity.DeptEntity;
import cn.showclear.pojo.entity.MemberEntity;
import cn.showclear.service.DeptService;
import cn.showclear.util.ReadConfig;
import cn.showclear.util.StringAnalysisUtil;
import cn.showclear.service.TableHeadService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import cn.showclear.pojo.EntityManager;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.util.*;

public class MainApplication {

    public static void main(String[] args) {
        //参数检查和保存
        if (!EntityManager.checkArgs.check(args)) {
            System.out.println("参数输入错误");
            return;
        } else {
            System.out.println("参数成功");
        }
        SqlSession session = EntityManager.getSession();
        DeptMapper deptMapper = session.getMapper(DeptMapper.class);
        MemberMapper memberMapper = session.getMapper(MemberMapper.class);

        int deptIndex = 2;

        //读取配置
        ReadConfig.read();


        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("E://叙简科技内部通讯录.xlsx");
            Workbook sheets = WorkbookFactory.create(fileInputStream);
            Iterator<Sheet> sheetIterator = sheets.sheetIterator();

            //先创建总部门 叙简科技
            String mainEntityName = "叙简科技";
            DeptEntity mainEntity = deptMapper.selectByName(mainEntityName);
            if (mainEntity == null) {//不能重复
                mainEntity = new DeptEntity(0, mainEntityName, StringAnalysisUtil.getFirstLetter(mainEntityName), "", 1,
                        mainEntityName, "", "", "", Constant.DEPT_ACTIVE);
                deptMapper.insertWithName(mainEntity);
            }

            //1.遍历每个sheet
            while (sheetIterator.hasNext()) {
                Sheet sheet = sheetIterator.next();
                //将sheet名加入到叙简科技中 并创建这个部门
                String subDeptName = sheet.getSheetName();
                String subPath = mainEntityName + "." + subDeptName;
                DeptEntity subEntity = deptMapper.selectByName(subDeptName);
                if (subEntity == null) {
                    subEntity = new DeptEntity(mainEntity.getId(), subDeptName, StringAnalysisUtil.getFirstLetter(subDeptName), "", deptIndex++, subPath, "", "", "", Constant.DEPT_ACTIVE);
                    deptMapper.insertWithName(subEntity);
                }

                //2.遍历行 得到表头行数
                int tableHeadRow = 0;
                int rowIndexMark = 0;
                while (rowIndexMark <= 10) {
                    Cell cell = sheet.getRow(rowIndexMark++).getCell(0);
                    if (cell == null) break;
                    //测试写法 不能直接对比字符串
                    if (TableHeadService.getText(cell).equals("部门") || TableHeadService.getText(cell).equals("")) {
                        tableHeadRow = rowIndexMark - 1;
                        break;
                    }
                }
                int cellNum = 0;
                Row tableRow = sheet.getRow(tableHeadRow);

                //3.根据表头，开始分块
                List<TableHeadRe> tableHeadRes = new ArrayList<>();
                TableHeadRe tableHeadRe = new TableHeadRe();
                tableHeadRes.add(tableHeadRe);
                tableHeadRe.setStartCell(0);

                while (true) {
                    Cell cell = tableRow.getCell(cellNum);
                    //当遍历到行尾时停止
                    if (cell == null) {
                        tableHeadRe.setEndCell(cellNum - 1);
                        break;
                    }
                    if (!TableHeadService.add2HeadTable(cell, cellNum, tableHeadRe)) {
                        //遇到部门，分块重传
                        tableHeadRe.setEndCell(cellNum - 1);
                        tableHeadRe = new TableHeadRe();

                        tableHeadRe.setStartCell(cellNum);
                        tableHeadRes.add(tableHeadRe);
                        TableHeadService.add2HeadTable(cell, cellNum, tableHeadRe);
                    }
                    cellNum++;
                }


                //根据每个块，得到相应的部门信息和人员信息。
                for (TableHeadRe table : tableHeadRes) {
                    //从数据行开始便利
                    int rowIndex = rowIndexMark;
//                    储存多级部门的id信息
                    Integer[] deptLevel = new Integer[table.getEndCell() - table.getStartCell()];

                    //储存多级部门的信息 最多长度不会超过表格总单元格数。
//                    List<String> deptLevel = new ArrayList<>(table.getEndCell() - table.getStartCell());

                    while (true) {
                        //遍历行
                        Row row = sheet.getRow(rowIndex++);
                        if (row == null || row.getCell(table.getEndCell())==null) break;
                        //用来储存人的信息 每一块的单个行只有一个人
                        MemberEntity member = new MemberEntity();
                        //用来储存拓展信息
                        Map<String, String> expMap = new HashMap<>();
                        //遍历该块的每一个单元格
                        for (int i = table.getStartCell(); i <= table.getEndCell(); i++) {

                            //
                            String cellText = TableHeadService.getText(row.getCell(i));
                            System.out.println(cellText);
                            //该列对应的属性
                            switch (table.getTableHeadMap().get(i)) {
                                case Dept://之部分过长 应该单独封装
                                    //对部门的操作 判断是第几级部门 储存 /人物对应最下级部门
                                    //i-table.startCell 即为当前的部门级数
                                    //有一种情况 前面是3级，后面是1级/2级部门的处理方法！-待定
                                    //以及人如何确定他是属于哪个部门的


                                    //先判断空
                                    if (row.getCell(i).getCellTypeEnum() != CellType.BLANK) {

                                        int level = i - table.getStartCell();//确定表等级
                                        if (level == 0) {
//                                        deptLevel.clear();//第0级出现时，清空等级记录
                                            deptLevel = new Integer[table.getEndCell() - table.getStartCell()];
                                            //0级表的上级部门是sheet部门
                                            DeptEntity deptEntity = new DeptEntity();
                                            deptEntity.setDeptName(cellText);
                                            deptEntity.setParentId(subEntity.getId());
                                            deptEntity.setFirstLetter(PinyinHelper.getShortPinyin(cellText));
                                            deptEntity.setPathName(subPath + "." + cellText);
                                            deptEntity.setIsActive(Constant.DEPT_ACTIVE);
                                            deptEntity.setSortIndex(deptIndex++);

                                            deptMapper.insertWithName(deptEntity);
                                            deptLevel[level] = deptEntity.getId();

                                        } else {
                                            DeptEntity deptEntity = new DeptEntity();
                                            deptEntity.setDeptName(cellText);
                                            deptEntity.setFirstLetter(PinyinHelper.getShortPinyin(cellText));
                                            deptEntity.setParentId(deptLevel[level - 1]);

                                            deptEntity.setPathName(DeptService.getDeptPath(deptMapper,deptLevel,subPath));
                                            deptEntity.setIsActive(Constant.DEPT_ACTIVE);
                                            deptEntity.setSortIndex(deptIndex++);

                                            deptMapper.insertWithName(deptEntity);
                                            deptLevel[level] = deptEntity.getId();
                                        }

                                    }

                                    break;
                                case name:
                                    member.setMemName(cellText);
                                    break;
                                case phoneNumber:
                                    if(StringUtils.isNoneBlank(cellText)){
                                        String[] split = cellText.split("/");
                                        for(String number:split){
                                            member.addPhoneNumber(number);
                                        }
                                    }
                                    break;
                                case tellphoneNumber:
                                    member.setMemMobile(cellText);
                                    break;
                                case email:
                                    member.setMemEmail(cellText);
                                    break;
                                case fex:
                                    member.setMemFax(cellText);
                                    break;
                                case deptExt:
                                    //这里怎们拿到key sheet.getRow(rowIndexMark).getCell(i) 可以拿到 不够优雅2333
                                    expMap.put(TableHeadService.getText(sheet.getRow(rowIndexMark - 1).getCell(i)), cellText);
                                    break;
                            }


                        }
                        if (member.getMemName() != null) {//以姓名作为标志，没有姓名就认为扫描到了无效的行（存在空字符串，不是空表格）
                            //遍历结束 储存人物信息和人物的拓展信息（事后处理）
                            //拓展信息保存
                            ObjectMapper objectMapper = new ObjectMapper();
                            member.setDeptExt(objectMapper.writeValueAsString(expMap));
                            //创建日期保存
                            member.setCreateTime(new Date());
                            //姓名拼音首字母保存
                            member.setFirstLetter(StringAnalysisUtil.getFirstLetter(member.getMemName()));
                            //人员的部门保存
                            member.setDeptId(DeptService.getLastDept(deptLevel));

                            memberMapper.insertByMenber(member);
                            System.out.println(member);
                            System.out.println("----------------------------");
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
        //1.遍历每个sheet
        //2.遍历行 得到表头行数
        //3.根据表头，开始分块
        //4.按块遍历
        // 所在列在部门列内 进行部门结构保存
        //所在列不在... 进行人员结构的保存
        //.4
        //.1
    }
}
