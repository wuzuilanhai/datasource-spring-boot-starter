package com.biubiu;

import com.biubiu.constants.DatabaseType;
import com.biubiu.datasource.DynamicDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Haibiao.Zhang on 2019-03-26 09:05
 */
@Configuration
@ConditionalOnProperty(name = "biubiu.multi.datasource", havingValue = "true")
public class DataSourceAutoConfiguration {

    @Bean("firstDataSource")
    @ConfigurationProperties("spring.datasource")
    public DataSource firstDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("secondDataSource")
    @ConfigurationProperties("spring.slave")
    public DataSource secondDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("dynamicDataSource")
    @Primary
    public DynamicDataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DatabaseType.MASTER, firstDataSource());
        targetDataSources.put(DatabaseType.SLAVE, secondDataSource());

        dynamicDataSource.setDefaultTargetDataSource(firstDataSource());
        dynamicDataSource.setTargetDataSources(targetDataSources);
        return dynamicDataSource;
    }

}
