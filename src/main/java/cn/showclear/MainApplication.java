package cn.showclear;


import cn.showclear.controller.ErrorControllerImpl;
import cn.showclear.controller.ExcelControllerImpl;
import cn.showclear.controller.HelpControllerImpl;
import cn.showclear.controller.IController;
import cn.showclear.pojo.EntityEnManager;

public class MainApplication {

    public static void main(String[] args) {
        IController mainApplication = null;

        //参数检查和保存

        switch (EntityEnManager.checkArgs.check(args)) {
            case help:
                mainApplication = new HelpControllerImpl();
                break;
            case save:
                mainApplication = new ExcelControllerImpl();
                break;
            case error:
                mainApplication = new ErrorControllerImpl();
                break;
        }

        if (mainApplication != null) mainApplication.method();

    }
}
