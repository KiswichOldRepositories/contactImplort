package cn.showclear.pojo.common;

import cn.showclear.constant.EnumMark;

/**
 * 根据不同的传入参数，进入不同的业务
 */
public enum Option implements EnumMark{
    help,//帮助 显示各种命令
    save,//将excel的数据存入数据库
    error,//错误

}
