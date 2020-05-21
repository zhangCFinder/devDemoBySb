package com.zcFinder.common.util.MultipleDataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;

/**
 * Created by zhangc on 2020/5/8.
 */
@Configuration
public class MyBatisConfig {
	@Resource(name = "routingDataSource")
	private RoutingDataSource routingDataSource;

	/**
	 * routingDataSource sqlSessionFactory
	 *
	 * @return
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(routingDataSource);
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/*.xml"));
//		sqlSessionFactoryBean.setTypeAliasesPackage("com.wch.financial.domain");
		return sqlSessionFactoryBean.getObject();
	}

	/**
	 * 注册 sqlSessionTemplate
	 *
	 * @param sqlSessionFactory
	 * @return
	 */
	@Bean
	public SqlSessionTemplate financialMasterSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
