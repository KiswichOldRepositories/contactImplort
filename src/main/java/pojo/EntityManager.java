package pojo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.common.ApplicationInfomation;
import service.CheckArgs;

import java.io.IOException;

/**
 * 用来管理实例（如service和ApplicationInfomation 都应该是单实例的）
 */
public class EntityManager {
    public static ApplicationInfomation applicationInfomation;
    public static CheckArgs checkArgs = new CheckArgs();
    private static SqlSessionFactory sqlSessionFactory;


    /**
     * 获取session
     */
    public static SqlSession getSession(){
            try {
                if(sqlSessionFactory == null)   sqlSessionFactory =  new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis.xml")) ;
                    return sqlSessionFactory.openSession();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
    }
}
