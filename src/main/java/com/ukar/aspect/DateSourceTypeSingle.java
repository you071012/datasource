package com.ukar.aspect;

import com.ukar.enums.DataSourceEnum;

import java.util.ArrayList;
import java.util.List;

public class DateSourceTypeSingle {

    private int index = 0;

    private static DateSourceTypeSingle single = new DateSourceTypeSingle();

    private DateSourceTypeSingle() {
    }

    public static DateSourceTypeSingle getSingle(){
        return single;
    }

    private List<String> filterSlave(){
        DataSourceEnum[] values = DataSourceEnum.values();
        List<String> list = new ArrayList<>();
        for(DataSourceEnum dataSourceEnum : values){
            if(!DataSourceEnum.Master.equals(dataSourceEnum) && !DataSourceEnum.Slave.equals(dataSourceEnum)){
                list.add(dataSourceEnum.name());
            }
        }
        return list;
    }

    public String getDataSourceEnum(DataSourceEnum dataSourceEnum){
        if(dataSourceEnum == DataSourceEnum.Master){
            return DataSourceEnum.Master.name();
        }

        if(dataSourceEnum != null && dataSourceEnum != DataSourceEnum.Slave){
            return dataSourceEnum.name();
        }

        System.out.println("--------------------------------轮询前index=" + index);
        List<String> slaves = filterSlave();
        String slave = slaves.get(index);
        if(index == slaves.size() - 1){
            index = 0;
        }else{
            index = index + 1;
        }
        System.out.println("--------------------------------轮询后index=" + index);
        return slave;
    }
}
