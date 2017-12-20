package cn.showclear.pojo;

import cn.showclear.mapper.DeptMapper;
import cn.showclear.mapper.MemberMapper;
import cn.showclear.pojo.common.TableHeadEnum;
import cn.showclear.pojo.common.TableHeadRe;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import cn.showclear.pojo.common.ApplicationInfomation;
import cn.showclear.pojo.entity.DeptEntity;
import cn.showclear.pojo.entity.MemberEntity;
import cn.showclear.service.CheckArgs;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

/**
 * 用来管理实例（如service和ApplicationInfomation 都应该是单实例的）
 */
public class EntityEnManager {
    //储存命令行输入的参数
    public static ApplicationInfomation applicationInfomation;
    //储存基本的配置信息
    public static Map<String, TableHeadEnum> tableHeadDictory;
    public static Map<String, Integer> tableCellValueDictory;
    //
    public static CheckArgs checkArgs = new CheckArgs();
    //mybatis的factory
    private static SqlSessionFactory sqlSessionFactory;


    public static void setSession() throws Exception {
        try {
            sqlSessionFactory = getSqlSessionFactory();
        } catch (Exception e) {
            throw e;
        }

    }

    /**
     * 检查输入的参数
     */
    public static void checkApplicationInfomation() throws Exception{

        setSession();
        File file = new File(applicationInfomation.getExcelLocalPath());
        if(!file.exists()) throw new Exception("文件不存在");
    }

    /**
     * 获取session
     */
    public static SqlSession getSession() {
        try {
            if (sqlSessionFactory == null) {
//                    sqlSessionFactory =  new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis.xml")) ;
                sqlSessionFactory = getSqlSessionFactory();
            }
            return sqlSessionFactory.openSession();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取sheets
     */
    public static Workbook getEcxel() {
        try {
            FileInputStream fileInputStream = new FileInputStream(applicationInfomation.getExcelLocalPath());
            return WorkbookFactory.create(fileInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取sqlsessionfactory（java配置mybatis）【没找到改变datasource的api】
     */
    private static SqlSessionFactory getSqlSessionFactory() {
        //配置url、username、password
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://" + applicationInfomation.getDatabaseIp() + ":" + applicationInfomation.getDatabasePort() + "/DB_SC_CORE?useSSL=true";
        String username = applicationInfomation.getDatabaseUsername();
        String password = applicationInfomation.getDatabasePassword();
        PooledDataSource dataSource = new PooledDataSource(driver, url, username, password);

        TransactionFactory factory = new JdbcTransactionFactory();
        Environment development = new Environment("development", factory, dataSource);

        Configuration configuration = new Configuration(development);
        //别名
        configuration.getTypeAliasRegistry().registerAlias("DeptEntity", DeptEntity.class);
        configuration.getTypeAliasRegistry().registerAlias("MemberEntity", MemberEntity.class);
        //cn.showclear.mapper
        configuration.addMapper(DeptMapper.class);
        configuration.addMapper(MemberMapper.class);

        return new SqlSessionFactoryBuilder().build(configuration);
    }
}
