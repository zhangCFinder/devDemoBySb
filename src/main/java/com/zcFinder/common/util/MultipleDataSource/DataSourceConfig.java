package com.zcFinder.common.util.MultipleDataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangc on 2020/5/8.
 */
@Configuration
public class DataSourceConfig {

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.druid.one")
	public DataSource financialMasterDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.druid.two")
	public DataSource financialSlaveDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "routingDataSource")
	public RoutingDataSource routingDataSource() {
		Map<Object, Object> dataSourceMap = new HashMap<>();
		dataSourceMap.put("master", financialMasterDataSource());
		dataSourceMap.put("slave", financialSlaveDataSource());

		RoutingDataSource routingDataSource = new RoutingDataSource();
		routingDataSource.setTargetDataSources(dataSourceMap);
		routingDataSource.setDefaultTargetDataSource(financialMasterDataSource());
		return routingDataSource;
	}
}