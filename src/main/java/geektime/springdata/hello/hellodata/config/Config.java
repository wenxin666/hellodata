package geektime.springdata.hello.hellodata.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
public class Config {
    @Bean(name = "fooDataSourceProperties")
    @Qualifier("fooDataSource")
    @Primary
    @ConfigurationProperties("foo.datasource")
    public DataSourceProperties fooDataSourceProperties(){
        return new DataSourceProperties();
    }


    @Bean(name =  "fooDataSource")
    @Qualifier("fooDataSource")
    @Primary
    @ConfigurationProperties("foo.datasource")
    public DataSource fooDataSource(){
        DataSourceProperties dataSourceProperties = fooDataSourceProperties();
        return  dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "fooJdbcTemplate")
    public JdbcTemplate fooJdbcTemplate(@Qualifier(value = "fooDataSource") DataSource dataSource ){
        return new JdbcTemplate(dataSource );
    }

    @Bean(name = "fooTxManager")
    @Resource
    public PlatformTransactionManager fooTxManager(DataSource fooDataSource){
        return new DataSourceTransactionManager(fooDataSource);
    }


    @Bean(name = "barJdbcTemplate")
    public JdbcTemplate barJdbcTemplate(@Qualifier("barDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "barDataSourceProperties")
    @ConfigurationProperties("bar.datasource")
    @Qualifier("barDataSource")
    public DataSourceProperties barDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean(name = "barTxManager")
    @Resource
    public PlatformTransactionManager barTxManager(DataSource barDataSource){
        return new DataSourceTransactionManager(barDataSource);
    }

    @Bean(name = "barDataSource")
    @Qualifier("barDataSource")
    @ConfigurationProperties("bar.datasource")
    public DataSource barDataSource(){
        DataSourceProperties dataSourceProperties = barDataSourceProperties();
        return  dataSourceProperties.initializeDataSourceBuilder().build();
    }
}
