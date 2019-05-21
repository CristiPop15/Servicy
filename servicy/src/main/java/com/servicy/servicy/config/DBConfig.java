package com.servicy.servicy.config;

import com.zaxxer.hikari.HikariDataSource;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(value = {"com.servicy.servicy.db"}, sqlSessionFactoryRef = "postgreSqlSessionFactory")
public class DBConfig {

  private static final Logger log = LoggerFactory.getLogger(DBConfig.class);

  @Value("${spring.datasource.url}")
  String dbUrl;

  @Value("${spring.datasource.username}")
  String dbUser;

  @Value("${spring.datasource.password}")
  String dbPass;

  @Value("${spring.datasource.driverClassName}")
  String dbDriver;

  @Value("${spring.datasource.pool.size}")
  Integer maxPoolSize;

  private HikariDataSource hikariDataSource = new HikariDataSource();

  private SqlSessionTemplate sessionTemplate;

  @PostConstruct
  public void init() {
    hikariDataSource.setDriverClassName(dbDriver);
    hikariDataSource.setJdbcUrl(dbUrl);
    hikariDataSource.setConnectionTestQuery("select 1");
    hikariDataSource.setUsername(dbUser);
    hikariDataSource.setPassword(dbPass);
    hikariDataSource.setMaximumPoolSize(maxPoolSize);
  }

  @Bean(name = "postgreSqlDataSource")
  @Primary
  @ConditionalOnProperty(name = "dblog.enabled", havingValue = "false", matchIfMissing = true)
  public DataSource getPostgresDataSource() {
    return hikariDataSource;
  }

  @Bean(name = "postgreSqlSessionFactory")
  @Primary
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
    sessionFactory.setDataSource(getPostgresDataSource());
    sessionFactory.setTypeHandlers(new TypeHandler[]{ //add type handlers if needed.

    });
    sessionFactory.setMapperLocations(
        new PathMatchingResourcePatternResolver().getResources(
            "classpath:com.servicy.servicy.db/*.xml")
    );
    return sessionFactory.getObject();
  }

  @PreDestroy
  public void cleanup() {
    if (sessionTemplate != null) {
      sessionTemplate.close();
    }
  }
}
