package cn.showclear.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "T_USER", schema = "DB_BLOG", catalog = "")
public class User {
    private Integer id;
    private String username;
    private String password;
    private Date createTime;
    private Integer active;
    private Integer auth;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(String username, String userPassword, Integer auth) {
        this.username = username;
        this.password = userPassword;
        this.auth = auth;
    }

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer userId) {
        this.id = userId;
    }

    @Basic
    @Column(name = "user_username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String userUsername) {
        this.username = userUsername;
    }

    @Basic
    @Column(name = "user_password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String userPassword) {
        this.password = userPassword;
    }


    @Basic
    @Column(name = "user_create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date userCreateTime) {
        this.createTime = userCreateTime;
    }

    @Basic
    @Column(name = "user_active")
    public Integer getActive() {
        return active;
    }

    public void setActive(Integer userActive) {
        this.active = userActive;
    }

    @Basic
    @Column(name = "user_auth")
    public Integer getAuth() {
        return auth;
    }

    public void setAuth(Integer userAuth) {
        this.auth = userAuth;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + id +
                ", userUsername='" + username + '\'' +
                ", userPassword='" + password + '\'' +
                ", userCreateTime=" + createTime +
                ", userActive=" + active +
                ", userAuth=" + auth +
                '}';
    }


    @Override
    public int hashCode() {

        return Objects.hash(id, username, password, createTime, active, auth);
    }
}
