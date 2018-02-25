package cn.showclear.service.impl;

import cn.showclear.entity.base.OrgDeptEntity;
import cn.showclear.entity.common.excelsplit.Excel;
import cn.showclear.init.InitBean;
import cn.showclear.service.BlockService;
import cn.showclear.service.ExcelService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Iterator;

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

    @Override
    public OrgDeptEntity getExcel(String excelPath) throws IOException, InvalidFormatException {
        Excel block = blockService.getBlock();

        Iterator<Sheet> iterator = workbook.iterator();
        iterator.forEachRemaining(sheet -> {



        });

        return null;
    }




    @Override
    public void saveExcel(OrgDeptEntity excel) {

    }
}
