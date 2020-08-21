package com.restapi.hartjo.dbconfig;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@Component
public class HikariDatasource {

	@Value("${spring.datasource.hikari.username}")
	private String user;
	
	@Value("${spring.datasource.hikari.password}")
	private String password;
	
	@Value("${spring.datasource.hikari.jdbc-url}")
	private String dataSourceUrl;
	
	@Value("${spring.datasource.hikari.pool-name}")
	private String poolName;
	
	@Value("${spring.datasource.hikari.connection-timeout}")
	private int connectionTimeout;
	
	@Value("${spring.datasource.hikari.max-lifetime}")
	private int maxLifetime;
	
	@Value("${spring.datasource.hikari.maximum-pool-size}")
	private int maximumPoolSize;
	
	@Value("${spring.datasource.hikari.minimum-idle}")
	private int minimumIdle;
	@Value("${spring.datasource.hikari.idle-timeout}")
	private int idleTimeout;
	public DataSource primaryDataSource() {
	
	    HikariConfig config = new HikariConfig();
	    config.setPoolName(poolName);
	    config.setJdbcUrl(dataSourceUrl);
	    config.setUsername(user);
	    config.setPassword(password);
	    config.setConnectionTimeout(connectionTimeout);
	    config.setMinimumIdle(minimumIdle);
	    config.setIdleTimeout(idleTimeout);
	    config.setMaximumPoolSize(maximumPoolSize);
	    config.setMaxLifetime(idleTimeout);
	    config.addDataSourceProperty("cachePrepStmts", "true");
	    config.addDataSourceProperty("prepStmtCacheSize", "250");
	    config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
	
	    HikariDataSource ds = new HikariDataSource(config);
	    return ds;
	}
}
