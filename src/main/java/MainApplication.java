import pojo.EntityManager;

public class MainApplication {

    public static void main(String[] args){
        //参数检查和保存
        if(!EntityManager.checkArgs.check(args)) {
            System.out.println("参数输入错误");
        }else {
            System.out.println("参数成功");
        }

    }
}
