package com.coder.config;



import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.coder.model.ConnectionSpeed;
import com.coder.model.DailyReport;




@Configuration//spring.xml
@ComponentScan(basePackages={"com.coder"}) //<context:component-scan
@PropertySources({ //resources/database.properties
		@PropertySource("classpath:application.properties")
})
@EnableTransactionManagement
public class SpringDSXmlConfig {
	@Autowired
	private Environment environment;	
	
	@Autowired
	@Bean(name="dataSource")
	public DataSource getDataSource(){
		DriverManagerDataSource ds=new DriverManagerDataSource();
		ds.setDriverClassName(
		this.environment.getProperty("jdbc.driverClassName"));
		ds.setUrl(this.environment.getProperty("jdbc.url"));
		ds.setUsername(this.environment.getProperty("jdbc.username"));
		ds.setPassword(this.environment.getProperty("jdbc.password"));
		return ds;
	}
	
	@Autowired
	@Bean(name="transactionManager")
	public HibernateTransactionManager getTransactionManager(
			SessionFactory sf){
		HibernateTransactionManager tm=new HibernateTransactionManager(sf);
		return tm;
	}
	
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory(){
		LocalSessionFactoryBuilder b=new LocalSessionFactoryBuilder(getDataSource());
		
		b.addAnnotatedClass(ConnectionSpeed.class);
		b.addAnnotatedClass(DailyReport.class);
		
		return b.buildSessionFactory();
	}
}




