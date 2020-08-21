package com.restapi.hartjo.dbconfig;

import io.ebean.EbeanServer;
import io.ebean.EbeanServerFactory;
import io.ebean.config.ServerConfig;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//import io.ebean.springsupport.txn.SpringAwareJdbcTransactionManager;

/**
 * Spring factory for creating the EbeanServer singleton.
 */
@Component
public class EbeanFactoryBean implements FactoryBean<EbeanServer> {

  @Autowired
  CurrentUser currentUser;
  
  @Autowired
  HikariDatasource dataSource;
  
  @Override
  public EbeanServer getObject() throws Exception {

    ServerConfig config = new ServerConfig();
    config.setName("db");
    config.setCurrentUserProvider(currentUser);

//    // set the spring's datasource and transaction manager.
    config.setDataSource(dataSource.primaryDataSource());
//    config.setExternalTransactionManager(new SpringAwareJdbcTransactionManager());

//    config.loadFromProperties();
//    config.loadTestProperties();

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
