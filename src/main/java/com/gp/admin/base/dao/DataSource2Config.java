package com.gp.admin.base.dao;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
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
 * 数据源2 配置，对应
 * 
 * @author wangjiehan
 *
 */

@Configuration
@MapperScan(basePackages = "com.gp.admin.schedule.dao", sqlSessionTemplateRef = "db2SqlSessionTemplate")
public class DataSource2Config {
	
	@Bean(name = "db2DataSource")
	@Qualifier("db2DataSource")
	@ConfigurationProperties(prefix = "spring.datasource.db2")
	@QuartzDataSource
	public DataSource dataSource2() {
		DataSourceBuilder<HikariDataSource> hikariDataSourceBuilder = DataSourceBuilder.create()
				.type(HikariDataSource.class);
		HikariDataSource ds = hikariDataSourceBuilder.build();

		// 配置 Hikari 连接池
		ds.setAutoCommit(true); // update自动提交设置
		ds.setConnectionTestQuery("select 1"); // 连接查询语句设置
		ds.setConnectionTimeout(3000); // 连接超时时间设置
		ds.setIdleTimeout(3000); // 连接空闲生命周期设置
		ds.setIsolateInternalQueries(false); // 执行查询启动设置
		ds.setMaximumPoolSize(3000); // 连接池允许的最大连接数量
		ds.setMaxLifetime(1800000); // 检查空余连接优化连接池设置时间,单位毫秒
		ds.setMinimumIdle(10); // 连接池保持最小空余连接数量
		ds.setPoolName("ds2Pool"); // 连接池名称

		return ds;
	}

	@Bean(name = "db2SqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory2(@Qualifier("db2DataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:sqlmap/*.xml"));
		return bean.getObject();
	}

	@Bean(name = "db2TransactionManager")
	public DataSourceTransactionManager transactionManager2(@Qualifier("db2DataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "db2SqlSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate2(
			@Qualifier("db2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
