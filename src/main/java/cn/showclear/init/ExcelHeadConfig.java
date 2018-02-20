package cn.showclear.init;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 储存配置在application.properties中的配置项
 */
@ConfigurationProperties(prefix = "excel")
@Component
public class ExcelHeadConfig {
    private String[] dept;
    private String[] name;
    private String[] phoneNumber;
    private String[] tellphoneNumber;
    private String[] email;
    private String[] fex;
    private String[] ext;

    public String[] getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept.split(";");
    }

    public String[] getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.split(";");
    }

    public String[] getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber.split(";");
    }

    public String[] getTellphoneNumber() {
        return tellphoneNumber;
    }

    public void setTellphoneNumber(String tellphoneNumber) {
        this.tellphoneNumber = tellphoneNumber.split(";");
    }

    public String[] getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.split(";");
    }

    public String[] getFex() {
        return fex;
    }

    public void setFex(String fex) {
        this.fex = fex.split(";");
    }

    public String[] getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext.split(";");
    }

    @Override
    public String toString() {
        return "ExcelHeadConfigProperties{" +
                "dept=" + Arrays.toString(dept) +
                ", name=" + Arrays.toString(name) +
                ", phoneNumber=" + Arrays.toString(phoneNumber) +
                ", tellphoneNumber=" + Arrays.toString(tellphoneNumber) +
                ", email=" + Arrays.toString(email) +
                ", fex=" + Arrays.toString(fex) +
                ", ext=" + Arrays.toString(ext) +
                '}';
    }
}
