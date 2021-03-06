package com.ukar.entity;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by jyou on 2017/9/15.
 */
@Table(name = "t_user")
public class User implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;

    private String name;

    private String password;

    public User() {
    }

    public User(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
