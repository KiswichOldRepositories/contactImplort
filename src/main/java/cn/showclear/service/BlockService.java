package cn.showclear.service;

import cn.showclear.entity.common.excelsplit.Excel;

/**
 * 根据excel的路径分表块
 */
public interface BlockService {
    public Excel getBlock();
}
