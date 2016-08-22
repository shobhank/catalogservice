package org.readme.test.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author shsharma
 */

@Configuration
@EnableTransactionManagement
@ComponentScan("org.readme")
public class ConfigureTest {
	
	 
    @Autowired
    private Environment environment;
 
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
    	System.out.println("Loading Session Factory");
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "org.readme.model" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        System.out.println("Loaded Session Factory");
        return sessionFactory;
    }
 
    @Bean(name = "dataSource")
    public DataSource dataSource() {
    	System.out.println("Loading Data Source");
    	DataSource dataSource = new EmbeddedDatabaseBuilder().
    			setType(EmbeddedDatabaseType.H2).setName("catalog").
    			addScript("db-schema.sql").
    			addScript("db-test-data.sql").build();
    	
    	try {
			Connection conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement("select * from book");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				System.out.println("Shobhank " + rs.getString("title"));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("Loaded Data Source and inserted data");
    	return dataSource;
    }
 
    private Properties hibernateProperties() {
    	System.out.println("Pupulatting properties");
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
    	System.out.println("Populated properties");
        return properties;
    }
 
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
    	System.out.println("Creating Tx Manage");
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
    	System.out.println("Created Tx Manager");
        return txManager;
    }

}
