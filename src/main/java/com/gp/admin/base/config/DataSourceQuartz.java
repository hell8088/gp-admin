package com.gp.admin.base.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

/**
 * 
 * @author wangjiehan
 *
 */

@Configuration
public class DataSourceQuartz {

	@Bean(name = "quartzDataSource")
	@Qualifier("quartzDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.quartz")
	@QuartzDataSource
	public DataSource dataSource() {
		DataSourceBuilder<HikariDataSource> hikariDataSourceBuilder = DataSourceBuilder.create()
				.type(HikariDataSource.class);
		HikariDataSource ds = hikariDataSourceBuilder.build();

		ds.setAutoCommit(true);
		ds.setConnectionTestQuery("select 1");
		ds.setConnectionTimeout(3000);
		ds.setIdleTimeout(3000);
		ds.setIsolateInternalQueries(false);
		ds.setMaximumPoolSize(3000);
		ds.setMaxLifetime(1800000);
		ds.setMinimumIdle(10);
		ds.setPoolName("quartz_dsPool");

		return ds;
	}

	@Bean(name = "quartzSqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("quartzDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:sqlmap/*.xml"));
		return bean.getObject();
	}

	@Bean(name = "quartzTransactionManager")
	public DataSourceTransactionManager transactionManager(@Qualifier("quartzDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "quartzSqlSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate(
			@Qualifier("quartzSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
