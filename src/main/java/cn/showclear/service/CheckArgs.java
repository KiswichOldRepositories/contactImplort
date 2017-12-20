package cn.showclear.service;

import cn.showclear.pojo.EntityEnManager;
import cn.showclear.pojo.common.ApplicationInfomation;
import cn.showclear.pojo.common.Option;
import cn.showclear.util.AnalysisCommandArgsUtil;

/**
 * 命令行参数检查
 */
public class CheckArgs {
    /**
     * 参数匹配 通过不同的参数 进入不同的操作
     *
     * @param args
     * @return
     */
    public Option check(String... args) {


        if (args[0].equals("help")) {//help
            return Option.help;
        }


        try {
            EntityEnManager.applicationInfomation = AnalysisCommandArgsUtil.AnalByMethod2(args);
            EntityEnManager.checkApplicationInfomation();
        } catch (Exception e) {
            try {
                EntityEnManager.applicationInfomation = AnalysisCommandArgsUtil.AnalByMethod1(args);
                EntityEnManager.checkApplicationInfomation();
            } catch (Exception e1) {
                ApplicationInfomation[] infomations = AnalysisCommandArgsUtil.AnalByMethod3(args);
                if(infomations!=null){
                    //尝试着将得到information连接数据库
                    for (ApplicationInfomation infomation : infomations) {
                        try {
                            if(infomation!=null){
                                EntityEnManager.applicationInfomation = infomation;
                                EntityEnManager.checkApplicationInfomation();
                                break;
                            }
                        } catch (Exception e3) {
                        }
                    }
                }
            }
        }

        try {
            EntityEnManager.checkApplicationInfomation();
            return Option.save;
        } catch (Exception e) {
            return Option.error;
        }

//        ApplicationInfomation infomation = AnalysisCommandArgsUtil.AnalByMethod2(args);
//        if(infomation == null) {
//            infomation = AnalysisCommandArgsUtil.AnalByMethod1(args);
//            if(infomation == null) {
//                infomation = AnalysisCommandArgsUtil.AnalByMethod3(args);
//            }
//        }
//        if(infomation   == null) return false;
//        else{
//            EntityEnManager.applicationInfomation = infomation;
//            return true;
//        }
    }


}
