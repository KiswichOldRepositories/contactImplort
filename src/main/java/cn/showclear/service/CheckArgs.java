package cn.showclear.service;

import cn.showclear.pojo.EntityManager;
import cn.showclear.pojo.common.ApplicationInfomation;
import cn.showclear.util.AnalysisCommandArgsUtil;

/**
 * 命令行参数检查
 */
public class CheckArgs {
    /**
     * 参数匹配 通过不同的参数 进入不同的操作
     * @param args
     * @return
     */
    public boolean check(String ... args){
        if(args.length == 1){//help

        }

        ApplicationInfomation infomation = AnalysisCommandArgsUtil.AnalByMethod2(args);
        if(infomation == null) {
            infomation = AnalysisCommandArgsUtil.AnalByMethod1(args);
            if(infomation == null) {
                infomation = AnalysisCommandArgsUtil.AnalByMethod3(args);
            }
        }
        if(infomation   == null) return false;
        else{
            EntityManager.applicationInfomation = infomation;
            return true;
        }
    }


}
