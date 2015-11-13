package com.epam.sc.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan({ "com.epam.sc.repository" })
@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@ImportResource("classpath:context.xml")
public class DataConfig {
  
  @Autowired
  DataSource dataSource;

  @Bean
  public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
    return new NamedParameterJdbcTemplate(dataSource);
  }
  
  @Bean
  public JdbcTemplate getJdbcTemplate() {
    return new JdbcTemplate(dataSource);
  }
  
  @Bean
  public DataSource dataSource(){
    //jdbc:hsqldb:mem:testdb    
    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
    EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.HSQL)
      .addScript("db/sql/create-db.sql")
      .addScript("db/sql/insert-data.sql")
      .build();
    return db;
  }
  
  @PostConstruct
  public void startDBManager() {
    
    //hsqldb
    DatabaseManagerSwing.main(new String[] { "--url", "jdbc:hsqldb:mem:testdb", "--user", "sa", "--password", "" });

    //derby
    //DatabaseManagerSwing.main(new String[] { "--url", "jdbc:derby:memory:testdb", "--user", "", "--password", "" });

    //h2
    //DatabaseManagerSwing.main(new String[] { "--url", "jdbc:h2:mem:testdb", "--user", "sa", "--password", "" });

  }
}
