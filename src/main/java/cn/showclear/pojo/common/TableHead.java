package cn.showclear.pojo.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 单个分块表中应该具有的元素,及其所在的列数（这样储存索引的时候会很花时间）
 * 用另一种储存方式
 */
public class TableHead {
    //部门所在的列数
    private List<Integer> Dept;
    //姓名所在的列数
    private Integer name;
    //手机号码所在的列数
    private Integer telephoneNumber;
    //电话号码所在的列数
    private List<Integer> phoneNumber;
    //邮箱所在的列数
    private Integer email;
    //传真所在的列数
    private Integer fex;
    //表开始的列数
    private Integer startCell;
    //表结束的列数
    private Integer endCell;

    public TableHead() {
        this.Dept = new ArrayList<>();
        this.phoneNumber = new ArrayList<>();
    }

    public List<Integer> getDept() {
        return Dept;
    }

    public void setDept(List<Integer> dept) {
        Dept = dept;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public List<Integer> getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(List<Integer> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getEmail() {
        return email;
    }

    public void setEmail(Integer email) {
        this.email = email;
    }

    public Integer getFex() {
        return fex;
    }

    public void setFex(Integer fex) {
        this.fex = fex;
    }

    public Integer getStartCell() {
        return startCell;
    }

    public void setStartCell(Integer startCell) {
        this.startCell = startCell;
    }

    public Integer getEndCell() {
        return endCell;
    }

    public void setEndCell(Integer endCell) {
        this.endCell = endCell;
    }

    public Integer getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(Integer telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
}
