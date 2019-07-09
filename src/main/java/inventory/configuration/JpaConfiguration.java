package inventory.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationVersion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@EnableTransactionManagement
@Configuration
@PropertySource("classpath:database.properties")
public class JpaConfiguration {

    @Value("${db.host}")
    private String host;
    @Value("${db.name}")
    private String dbName;
    @Value("${db.port}")
    private String dbPort;
    @Value("${db.username}")
    private String dbUser;
    @Value("${db.password}")
    private String dbPassword;
    @Value("${db.version}")
    private String dbVersion;

    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername(dbUser);
        dataSource.setPassword(dbPassword);
        dataSource.setJdbcUrl(String.format("jdbc:postgresql://%s:%s/%s", host, dbPort, dbName));
        return dataSource;
    }

    @Bean(initMethod = "migrate")
    public Flyway flyway(DataSource dataSource){
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.setTarget(MigrationVersion.fromVersion(dbVersion));
        return flyway;
    }

}
