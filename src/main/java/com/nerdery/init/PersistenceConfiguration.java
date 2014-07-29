package com.nerdery.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate3.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableWebMvc
@EnableJpaRepositories(basePackages = {"com.nerdery.voting.repository"})
@PropertySource({ "classpath:/env/${ENV:test}/persistence.properties" })
@ComponentScan(basePackages = { "com.nerdery", "com.nerdery.voting" }) // TODO: be more specific, add multiple
public class PersistenceConfiguration {
    private static final String JDBC_DRIVER = "jdbc.driver";
    private static final String JDBC_URL = "jdbc.url";
    private static final String JDBC_USERNAME = "jdbc.username";
    private static final String JDBC_PASSWORD = "jdbc.password";
    private static final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";

    @Autowired
    private Environment environment;

    @Bean
    public PlatformTransactionManager transactionManager() {
        EntityManagerFactory factory = entityManagerFactory().getObject();
        return new JpaTransactionManager(factory);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setShowSql(true);

        factoryBean.setDataSource(dataSource());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setPackagesToScan("com.nerdery"); // TODO: should be more specific

        Properties properties = new Properties();
        properties.put(HIBERNATE_HBM2DDL_AUTO, environment.getProperty(HIBERNATE_HBM2DDL_AUTO));
        properties.put(HIBERNATE_DIALECT, environment.getProperty(HIBERNATE_DIALECT));
        properties.put(HIBERNATE_SHOW_SQL, environment.getProperty(HIBERNATE_SHOW_SQL));

        factoryBean.setJpaProperties(properties);

        factoryBean.afterPropertiesSet();
        factoryBean.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());

        return factoryBean;
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(environment.getProperty(JDBC_DRIVER));
        dataSource.setUrl(environment.getProperty(JDBC_URL));
        dataSource.setUsername(environment.getProperty(JDBC_USERNAME));
        dataSource.setPassword(environment.getProperty(JDBC_PASSWORD));

        return dataSource;
    }

    @Bean
    public UrlBasedViewResolver setUpViewResolver() {
        UrlBasedViewResolver urlBasedViewResolver = new UrlBasedViewResolver();
        urlBasedViewResolver.setPrefix("/WEB-INF/jsp/");
        urlBasedViewResolver.setSuffix(".jsp");
        urlBasedViewResolver.setViewClass(JstlView.class);

        return urlBasedViewResolver;
    }
}
