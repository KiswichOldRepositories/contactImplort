package cn.showclear.init;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 储存配置在application.properties中的配置项
 */
@ConfigurationProperties(prefix = "excelsplit")
@Component
public class ExcelHeadConfig {
    private List<String> dept;
    private List<String> name;
    private List<String> phoneNumber;
    private List<String> tellphoneNumber;
    private List<String> email;
    private List<String> fex;
    private List<String> ext;

    public List<String> getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = Arrays.asList(dept.split(";"));
    }

    public List<String> getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Arrays.asList(name.split(";"));
    }

    public List<String> getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = Arrays.asList(phoneNumber.split(";"));
    }

    public List<String> getTellphoneNumber() {
        return tellphoneNumber;
    }

    public void setTellphoneNumber(String tellphoneNumber) {
        this.tellphoneNumber = Arrays.asList(tellphoneNumber.split(";"));
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = Arrays.asList(email.split(";"));
    }

    public List<String> getFex() {
        return fex;
    }

    public void setFex(String fex) {
        this.fex = Arrays.asList(fex.split(";"));
    }

    public List<String> getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = Arrays.asList(ext.split(";"));
    }

    @Override
    public String toString() {
        return "ExcelHeadConfigProperties{" +
                "dept=" + Arrays.toString(dept.toArray()) +
                ", name=" + Arrays.toString(name.toArray()) +
                ", phoneNumber=" + Arrays.toString(phoneNumber.toArray()) +
                ", tellphoneNumber=" + Arrays.toString(tellphoneNumber.toArray()) +
                ", email=" + Arrays.toString(email.toArray()) +
                ", fex=" + Arrays.toString(fex.toArray()) +
                ", ext=" + Arrays.toString(ext.toArray()) +
                '}';
    }
}
