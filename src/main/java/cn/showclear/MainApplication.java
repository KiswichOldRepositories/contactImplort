package cn.showclear;

import cn.showclear.util.ReadConfig;
import org.apache.ibatis.session.SqlSession;
import cn.showclear.pojo.EntityManager;

public class MainApplication {

    public static void main(String[] args){
        //参数检查和保存
        if(!EntityManager.checkArgs.check(args)) {
            System.out.println("参数输入错误");
            return;
        }else {
            System.out.println("参数成功");
        }
        SqlSession session = EntityManager.getSession();
        //读取配置
        ReadConfig.read();


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
