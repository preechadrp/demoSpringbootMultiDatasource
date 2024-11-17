package com.example.demoSpringbootMultiDatasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(
  entityManagerFactoryRef = "db2EntityManagerFactory", 
  transactionManagerRef = "db2TransactionManager",
  basePackages = {"com.example.demoSpringbootMultiDatasource.db2repos"}
)
@EnableTransactionManagement
public class DB2 {

	@Bean("db2Properties")
	@ConfigurationProperties("database.db2")
	DataSourceProperties dataSourceProperties() {
		
		DataSourceProperties properties = new DataSourceProperties();
		
        //== สามารถกำหนดเองได้
		//properties.setDriverClassName("org.firebirdsql.jdbc.FBDriver");
        //properties.setUrl("jdbc:firebirdsql:localhost/3050:E:/javaDemo1/FIREBIRDDBTEST.FDB?encoding=UTF8");
        //properties.setUsername("SYSDBA");
        //properties.setPassword("masterkey");
		
		return properties;
		
	}

	@Bean("db2Datasource")
	DataSource dataSource(
			@Qualifier("db2Properties") DataSourceProperties properties) {
		
		return properties
				.initializeDataSourceBuilder()
				.build();
		
	}

	@Bean("db2EntityManagerFactory")
	LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
			EntityManagerFactoryBuilder builder,
			@Qualifier("db2Datasource") DataSource dataSource) {
		
		Map<String, String> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.show_sql", "true");
		//properties.put("hibernate.dialect", "org.hibernate.dialect.FirebirdDialect"); //ใช้ไม่ได้
		properties.put("hibernate.dialect", "org.hibernate.community.dialect.FirebirdDialect");//อ้างจาก https://stackoverflow.com/questions/74735125/unable-to-resolve-name-org-hibernate-dialect-firebirddialect
		
		return builder
				.dataSource(dataSource)
				.packages("com.example.demoSpringbootMultiDatasource.db2")
				.properties(properties)
				.build();
		
	}

	@Bean(name = "db2TransactionManager")
	PlatformTransactionManager platformTransactionManager(
			@Qualifier("db2EntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory.getObject());
	}

}
