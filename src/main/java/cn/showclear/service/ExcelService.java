package cn.showclear.service;

import cn.showclear.entity.base.OrgDeptEntity;
import cn.showclear.exception.TableStandardException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;

/**
 * 这个程序要完成的根本性的任务
 * 就是将一个excel表格里面的数据存到数据库里面
 * <p>
 * 为了将excel数据存到数据库里，首先要按照一定的规则读取excel，并生成相应的实体，然后将实体、实体与实体的关系存入数据库
 */
public interface ExcelService {
    /**
     * 将一个路径上的.excel文件转换成对应的对象
     * 相当于把磁盘上的文件按一定的格式读到了内存里
     * @param excelPath
     * @return
     */
    public OrgDeptEntity getExcel(String excelPath) throws IOException, InvalidFormatException, TableStandardException;

    /**
     *
     * 将一个excel实体存到数据库里
     * @param excel
     */
    public void saveExcel(OrgDeptEntity excel);
}
