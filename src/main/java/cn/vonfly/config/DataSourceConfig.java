package cn.vonfly.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Value("${read.database.url}")
    private String readUrl;
    @Value("${read.database.username}")
    private String readUsername;
    @Value("${read.database.password}")
    private String readPassword;
    @Value("${read.database.driver}")
    private String readDriver;
    @Value("${write.database.url}")
    private String writeUrl;
    @Value("${write.database.username}")
    private String writeUsername;
    @Value("${write.database.password}")
    private String writePassword;
    @Value("${write.database.driver}")
    private String writeDriver;
    @Bean
    @Qualifier("readJdbcOperations")
    public JdbcOperations readJdbcOperations(@Qualifier("readDataSource") DataSource readDataSource) {
        return new JdbcTemplate(readDataSource);
    }

    @Bean
    @Qualifier("writeJdbcOperations")
    public JdbcOperations writeJdbcOperations(@Qualifier("writeDataSource") DataSource writeDataSource) {
        return new JdbcTemplate(writeDataSource);
    }

    @Bean
    @Qualifier("readDataSource")
    public DataSource readDataSource() {
        BasicDataSource readDataSource = new BasicDataSource();
        readDataSource.setUrl(readUrl);
        readDataSource.setUsername(readUsername);
        readDataSource.setPassword(readPassword);
        readDataSource.setDriverClassName(readDriver);
        return readDataSource;
    }

    @Bean
    @Qualifier("writeDataSource")
    public DataSource writeDataSource() {
        BasicDataSource writeDataSource = new BasicDataSource();
        writeDataSource.setDriverClassName(writeDriver);
        writeDataSource.setUrl(writeUrl);
        writeDataSource.setUsername(writeUsername);
        writeDataSource.setPassword(writePassword);
        return writeDataSource;
    }

    /**
     * 事务管理
     *
     * @param readDataSource
     * @return
     */
    @Bean
    public DataSourceTransactionManager readDataSourceTransactionManager(@Qualifier("readDataSource") DataSource readDataSource) {
        DataSourceTransactionManager manager = new DataSourceTransactionManager(readDataSource);
        return manager;
    }

    /**
     * 事务管理
     *
     * @param writeDataSource
     * @return
     */
    @Bean
    public DataSourceTransactionManager writeDataSourceTransactionManager(@Qualifier("writeDataSource") DataSource writeDataSource) {
        DataSourceTransactionManager manager = new DataSourceTransactionManager(writeDataSource);
        return manager;
    }
}