package com.ukar.enums;

/**
 * Created by jyou on 2018/9/11.
 *
 * @author jyou
 */
public enum DataSourceEnum {
    //主库
    Master("master"),

    //从库01
    Slave01("slave01");

    private DataSourceEnum(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
