package cn.showclear.entity.common;

import org.apache.commons.lang3.StringUtils;

/**
 * 本程序相关的配置的相关信息
 */
public class ExcelConfig {

    private String url;
    private String user;
    private String password;
    private String port;
    private String fileLocation;

    public ExcelConfig() {
    }

    public ExcelConfig(String user, String password, String port) {
        this.user = user;
        this.password = password;
        this.port = port;
    }

    public ExcelConfig(String url, String user, String password, String port, String fileLocation) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.port = port;
        this.fileLocation = fileLocation;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    //检查空值 true为空
    public boolean checkNone() {
        return StringUtils.isBlank(this.url) || StringUtils.isBlank(this.fileLocation);
    }
}
