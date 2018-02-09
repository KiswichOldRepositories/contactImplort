package cn.showclear.service;

import cn.showclear.constant.Constant;
import cn.showclear.mapper.DeptMapper;
import cn.showclear.pojo.common.TableHeadRe;
import cn.showclear.pojo.entity.DeptEntity;
import cn.showclear.util.StringAnalysisUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.HashMap;

/**
 * 和部门逻辑有关的方法
 */
public class DeptService {
    //获取部门的路径
    public static String getDeptPath(DeptMapper mapper, Integer[] deptLevel, String subPath) throws Exception {

        try {
            StringBuilder stringBuilder = new StringBuilder(subPath);
            for (Integer aDeptLevel : deptLevel) {
                if (aDeptLevel != null) {
                    DeptEntity deptEntity = mapper.selectById(aDeptLevel);
                    stringBuilder.append(".").append(deptEntity.getDeptName());
                } else break;
            }
            return stringBuilder.toString();

        } catch (Exception e) {
            throw e;
        }

    }

    //根据部门等级存储 获取最后一个可用部门
    public static Integer getLastDept(Integer[] deptLevel) {
        for (int i = 0; i < deptLevel.length; i++) {
            if (deptLevel[i] == null && i != 0) return deptLevel[i - 1];
        }
        return 0;//基本不会出现的情况
    }

    /**
     * 获取/保存首部门（叙简科技）
     * @param deptMapper
     * @return
     * @throws Exception
     */
    public static DeptEntity createMainEntity(DeptMapper deptMapper) throws Exception{
        try {
            DeptEntity mainEntity = deptMapper.selectByName(Constant.MAIN_DEPT_NAME);
            if (mainEntity == null) {//不能重复
                mainEntity = new DeptEntity(0, Constant.MAIN_DEPT_NAME, StringAnalysisUtil.getFirstLetter(Constant.MAIN_DEPT_NAME), 1,
                        Constant.MAIN_DEPT_NAME, "", Constant.DEPT_ACTIVE);
                deptMapper.insertWithName(mainEntity);
            }
            return mainEntity;
        }catch (Exception e){
            e.printStackTrace();
            throw  e;
        }
    }

    /**
     * 获取分部门
     * @param deptMapper
     * @return
     */
    public static DeptEntity createSubEntity(DeptMapper deptMapper,String subDeptName,DeptEntity mainEntity,Integer deptIndex){

        String subPath = Constant.MAIN_DEPT_NAME + "." + subDeptName;
        DeptEntity subEntity = new DeptEntity(mainEntity.getId(), subDeptName);
        subEntity = deptMapper.selectByParentIdAndName(subEntity);
        if (subEntity == null) {
            subEntity = new DeptEntity(mainEntity.getId(), subDeptName, StringAnalysisUtil.getFirstLetter(subDeptName), deptIndex++, subPath, "", Constant.DEPT_ACTIVE);
        }
        return subEntity;
    }

//    public static DeptEntity createTableEntity(DeptMapper deptMapper,TableHeadRe table,DeptEntity subEntity,String[] deptLevel,String cellText,){
//
//        int level = i - table.getStartCell();//确定表等级
//
//        String path = null;
//        DeptEntity deptEntity = new DeptEntity();
//        deptEntity.setDeptName(cellText);
//
//        if (level == 0) {
//            //deptLevel.clear();//第0级出现时，清空等级记录
//            deptLevel = new Integer[table.getEndCell() - table.getStartCell()];
//            //0级表的上级部门是sheet部门
//            deptEntity.setParentId(subEntity.getId());
//            path = subEntity.getPathName() + "." + cellText;
//        } else {
//            deptEntity.setParentId(deptLevel[level - 1]);
//            path = DeptService.getDeptPath(deptMapper, deptLevel, subEntity.getPathName());
//        }
//
//        DeptEntity entity = deptMapper.selectByParentIdAndName(deptEntity);
//        if (entity == null) {//该部门不存在
//            deptEntity.setIsActive(Constant.DEPT_ACTIVE);
//            deptEntity.setFirstLetter(PinyinHelper.getShortPinyin(cellText));
//            deptEntity.setSortIndex(deptIndex++);
//            deptEntity.setPathName(path);
//
//            deptMapper.insertWithName(deptEntity);
//        } else {
//            deptEntity = entity;
//        }
//    }


    public static DeptEntity addDeptExp(Integer rowIndexMark, Sheet sheet,DeptEntity subEntity) throws  Exception{
        if (rowIndexMark >= 3) {//默认第二行会有公司总机号和传真号
            HashMap<String, String> hashMap = new HashMap<>();
            Cell cell = sheet.getRow(1).getCell(0);
            String[] phoneAndFex = StringAnalysisUtil.getPhoneAndFex(TableHeadService.getText(cell));
            if (phoneAndFex != null) {
                hashMap.put("公司总机号", phoneAndFex[0]);
                hashMap.put("公司传真号", phoneAndFex[1]);
                String json = new ObjectMapper().writeValueAsString(hashMap);
                subEntity.setDeptExt(json);
            }
        }
        return subEntity;
    }

}
