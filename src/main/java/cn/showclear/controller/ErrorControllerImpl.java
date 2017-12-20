package cn.showclear.controller;

public class ErrorControllerImpl implements  IController {

    @Override
    public void method() {
        System.out.println("命令错误，请检查输入参数或输入help查看详情");
    }
}
