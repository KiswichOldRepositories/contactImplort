package pojo.common;

public class ApplicationInfomation {
    private String databaseIp;
    private Integer databasePort;
    private String databaseUsername;
    private String databasePassword;
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
