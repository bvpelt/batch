package nl.bsoft.batch.config;

import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
        basePackages = {"nl.bsoft.batch.repo.h2"},
        entityManagerFactoryRef = "entityManagerFactoryH2",
        transactionManagerRef = "transactionManagerH2")
@EnableTransactionManagement
public class H2Config {

    @Bean(name = "transactionManagerH2")
    @Primary
    public PlatformTransactionManager transactionManagerH2() {
        return new JpaTransactionManager(entityManagerFactoryH2().getObject());
    }

    @Bean(name = "entityManagerFactoryH2")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryH2() {

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        //factoryBean.setDataSource(dataSourceH2());

        factoryBean.setDataSource(dataSourceH2(dataSourcePropertiesH2()));
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setJpaProperties(hibernateProperties());

        factoryBean.setPackagesToScan("nl.bsoft.batch.model.h2");
        factoryBean.setPersistenceUnitName("h2");

        return factoryBean;
    }

    /*
    @Primary
    @Bean(name = "dataSourceH2")
    @ConfigurationProperties(prefix = "datasourceh2")
    public DataSource dataSourceH2() {
        return DataSourceBuilder
                .create()
                .build();
    }
*/


    @Primary
    @Bean
    @ConfigurationProperties(prefix = "datasourceh2")
    public DataSourceProperties dataSourcePropertiesH2() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "dataSourceH2")
    @ConfigurationProperties(prefix = "datasourceh2")
    public HikariDataSource dataSourceH2(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class)
                .build();
    }

    private Properties hibernateProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        return hibernateProperties;
    }

    @Bean
    @ConfigurationProperties(prefix = "datasourceh2.primary-liquibase.liquibase")
    public LiquibaseProperties primaryLiquibaseProperties() {
        return new LiquibaseProperties();
    }

    @Bean("liquibase")
    public SpringLiquibase primaryLiquibase() {
        //return springLiquibase(dataSourceH2(), primaryLiquibaseProperties());

        return springLiquibase(dataSourceH2(dataSourcePropertiesH2()), primaryLiquibaseProperties());
    }

    private static SpringLiquibase springLiquibase(DataSource dataSource, LiquibaseProperties properties) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(properties.getChangeLog());
        liquibase.setContexts(properties.getContexts());
        liquibase.setDefaultSchema(properties.getDefaultSchema());
        liquibase.setDropFirst(properties.isDropFirst());
        liquibase.setShouldRun(properties.isEnabled());
        liquibase.setLabels(properties.getLabels());
        liquibase.setChangeLogParameters(properties.getParameters());
        liquibase.setRollbackFile(properties.getRollbackFile());
        return liquibase;
    }
}
