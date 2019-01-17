package com.example.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@EnableTransactionManagement
public class DruidDataSourceConfig implements EnvironmentAware {
    @Autowired
    private Environment environment;

    @Override
    public void setEnvironment(Environment env) {

    }

    @Bean
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(environment.getProperty("spring.datasource.url"));
        datasource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        datasource.setUsername(environment.getProperty("spring.datasource.username"));
        datasource.setPassword(environment.getProperty("spring.datasource.password"));
        datasource.setInitialSize(Integer.valueOf(environment.getProperty("spring.datasource.initial-size")));
        datasource.setMinIdle(Integer.valueOf(environment.getProperty("spring.datasource.min-idle")));
        datasource.setMaxWait(Long.valueOf(environment.getProperty("spring.datasource.max-wait")));
        datasource.setMaxActive(Integer.valueOf(environment.getProperty("spring.datasource.max-active")));
        datasource.setMinEvictableIdleTimeMillis(
                Long.valueOf(environment.getProperty("spring.datasource.min-evictable-idle-time-millis")));
        try {
            datasource.setFilters("stat,wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datasource;
    }
}
