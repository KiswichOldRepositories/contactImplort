package cn.showclear.pojo.common;

/**
 * 储存传入的基本配置
 */
public class ApplicationInfomation {
    //数据库ip
    private String databaseIp;
    //数据库端口
    private Integer databasePort;
    //数据库用户名
    private String databaseUsername;
    //数据库密码
    private String databasePassword;
    //要访问的文件名
    private String excelLocalPath;

    public ApplicationInfomation() {
    }

    public ApplicationInfomation(String databaseIp, int databasePort, String databaseUsername, String databasePassword, String excelLocalPath) {
        this.databaseIp = databaseIp;
        this.databasePort = databasePort;
        this.databaseUsername = databaseUsername;
        this.databasePassword = databasePassword;
        this.excelLocalPath = excelLocalPath;
    }

    public String getDatabaseIp() {
        return databaseIp;
    }

    public void setDatabaseIp(String databaseIp) {
        this.databaseIp = databaseIp;
    }

    public Integer getDatabasePort() {
        return databasePort;
    }

    public void setDatabasePort(int databasePort) {
        this.databasePort = databasePort;
    }

    public String getDatabaseUsername() {
        return databaseUsername;
    }

    public void setDatabaseUsername(String databaseUsername) {
        this.databaseUsername = databaseUsername;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public void setDatabasePassword(String databasePassword) {
        this.databasePassword = databasePassword;
    }

    public String getExcelLocalPath() {
        return excelLocalPath;
    }

    public void setExcelLocalPath(String excelLocalPath) {
        this.excelLocalPath = excelLocalPath;
    }
}
