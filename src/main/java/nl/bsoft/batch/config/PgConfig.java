package nl.bsoft.batch.config;

import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@EnableJpaRepositories(
        basePackages = {"nl.bsoft.batch.repo.pg"},
        entityManagerFactoryRef = "entityManagerFactoryPg",
        transactionManagerRef = "transactionManagerPg")
@EnableTransactionManagement
public class PgConfig extends DbConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(PgConfig.class);

    @Bean(name = "transactionManagerPg")
    public PlatformTransactionManager transactionManagerPg() {
        return new JpaTransactionManager(entityManagerFactoryPg().getObject());
    }

    @Bean(name = "entityManagerFactoryPg")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPg() {

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource(dataSourcePg(dataSourcePropertiesPg()));

        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setJpaProperties(hibernateProperties());

        factoryBean.setPackagesToScan("nl.bsoft.batch.model.pg");
        factoryBean.setPersistenceUnitName("pg");

        return factoryBean;
    }


    @Bean
    @ConfigurationProperties(prefix = "datasourcepg")
    public DataSourceProperties dataSourcePropertiesPg() {
        return new DataSourceProperties();
    }

    @Bean(name = "dataSourcePg")
    @ConfigurationProperties(prefix = "datasourcepg")
    public HikariDataSource dataSourcePg(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class)
                .build();
    }

    private Properties hibernateProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        return hibernateProperties;
    }

    @Bean
    @ConfigurationProperties(prefix = "datasourcepg.secondary-liquibase.liquibase")
    public LiquibaseProperties secondaryLiquibaseProperties() {
        return new LiquibaseProperties();
    }


    public SpringLiquibase secondaryLiquibase() {
        return springLiquibase(dataSourcePg(dataSourcePropertiesPg()), secondaryLiquibaseProperties());
    }


}
