package app.restapi.hartjo.configuration;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.zaxxer.hikari.HikariDataSource;

import io.ebean.EbeanServer;
import io.ebean.EbeanServerFactory;
import io.ebean.config.CurrentUserProvider;
import io.ebean.config.ServerConfig;

public class EbeanConfiguration implements FactoryBean<EbeanServer> {
	
	@Autowired
	CurrentUserProvider currentUser;
	
	@Autowired
	HikariDataSource dataSource;
	
	@Override
	public EbeanServer getObject() throws Exception {

		ServerConfig config = new ServerConfig();
		config.setName("db");
		config.setDefaultServer(true);
		config.setDataSource(dataSource);
		config.setCurrentUserProvider(currentUser);

		return EbeanServerFactory.create(config);
	}

	@Override
	public Class<?> getObjectType() {
		return EbeanServer.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
