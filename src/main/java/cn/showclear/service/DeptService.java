package cn.showclear.service;

import cn.showclear.mapper.DeptMapper;
import cn.showclear.pojo.entity.DeptEntity;
import org.apache.ibatis.session.SqlSession;

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
}
