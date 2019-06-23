package geektime.springdata.hello.hellodata;
import geektime.springdata.hello.hellodata.config.Config;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
@Slf4j
@EnableAutoConfiguration
public class HelloDataApplication implements CommandLineRunner{

    @Autowired
    private Config config;
//    @Autowired
//    @Qualifier("fooJdbcTemplate")
//    private  JdbcTemplate fooJdbcTemplate;

    public static void main(String[] args) {
		SpringApplication.run(HelloDataApplication.class, args);
	}


    @Override
    public void run(String... args) throws Exception {

        log.info("------------------------------------------");
        JdbcTemplate barJdbcTemplate = config.barJdbcTemplate(config.barDataSource());
        JdbcTemplate fooJdbcTemplate = config.fooJdbcTemplate(config.fooDataSource());

        fooJdbcTemplate.queryForList("SELECT * FROM FOO")
                .forEach(row -> log.info(row.toString()));

        barJdbcTemplate.queryForList("select * from BAR").
                forEach(row -> log.info(row.toString()));
    }
}
