package com.ukar.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.ukar.enums.DataSourceEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jyou on 2018/9/11.
 *
 * @author jyou
 * <p>
 * 配置数据库连接信息
 */
@Configuration
public class DynamicDataSourceConfiguration {

    @Value("${jdbc.master.driver}")
    private String masterDriver;

    @Value("${jdbc.master.username}")
    private String masterUsername;

    @Value("${jdbc.master.password}")
    private String masterPassword;

    @Value("${jdbc.master.url}")
    private String masterUrl;

    @Value("${jdbc.slave01.driver}")
    private String slave01Driver;

    @Value("${jdbc.slave01.username}")
    private String slave01Username;

    @Value("${jdbc.slave01.password}")
    private String slave01Password;

    @Value("${jdbc.slave01.url}")
    private String slave01Url;

    @Value("${jdbc.slave02.driver}")
    private String slave02Driver;

    @Value("${jdbc.slave02.username}")
    private String slave02Username;

    @Value("${jdbc.slave02.password}")
    private String slave02Password;

    @Value("${jdbc.slave02.url}")
    private String slave02Url;


    @Bean
    public DataSource dynamicDataSource() {
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setDefaultTargetDataSource(dbMaster());
        Map<Object, Object> dataSourceMap = new HashMap<>(4);
        dataSourceMap.put(DataSourceEnum.Master.name(), dbMaster());
        dataSourceMap.put(DataSourceEnum.Slave01.name(), dbSlave01());
        dataSourceMap.put(DataSourceEnum.Slave02.name(), dbSlave02());
        dataSource.setTargetDataSources(dataSourceMap);
        return dataSource;
    }

    /**
     * 写库
     *
     * @return
     */
    private DataSource dbMaster() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(masterDriver);
        dataSource.setUsername(masterUsername);
        dataSource.setPassword(masterPassword);
        dataSource.setUrl(masterUrl);
        return dataSource;
    }

    /**
     * 读库1
     *
     * @return
     */
    private DataSource dbSlave01() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(slave01Driver);
        dataSource.setUsername(slave01Username);
        dataSource.setPassword(slave01Password);
        dataSource.setUrl(slave01Url);
        return dataSource;
    }

    /**
     * 读库1
     *
     * @return
     */
    private DataSource dbSlave02() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(slave02Driver);
        dataSource.setUsername(slave02Username);
        dataSource.setPassword(slave02Password);
        dataSource.setUrl(slave02Url);
        return dataSource;
    }

}
