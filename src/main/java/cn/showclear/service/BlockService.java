package cn.showclear.service;

import cn.showclear.entity.common.excelsplit.Excel;
import cn.showclear.exception.TableStandardException;

/**
 * 根据excel的路径分表块
 */
public interface BlockService {
    /**
     * 获得分块表
     * @return
     */
    public Excel getBlock() throws TableStandardException;
}
