package cn.vonfly.job;


import cn.vonfly.config.annotation.ReadJdbcAutowire;
import cn.vonfly.config.annotation.WriteJdbcAutowire;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TestJob {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @ReadJdbcAutowire
    private JdbcOperations readJdbcAutowire;
    @WriteJdbcAutowire
    private JdbcOperations writeJdbcAutowire;

    @Scheduled(cron = "${test.job.cron}")
    public void sayHello() {
        Integer integer = readJdbcAutowire.queryForObject("select COUNT(*) from api_order o where o.PLATFORM_OID='D2018011011134900003810306'", Integer.class);
        logger.info("hello read:COUNT={},thread-name={},time={}", integer, Thread.currentThread().getName(), new Date());
        Integer temp = writeJdbcAutowire.queryForObject("select COUNT(*) from api_order o where o.PLATFORM_OID='D2018011011134900003810306'", Integer.class);
        logger.info("hello read:COUNT={},thread-name={},time={}", temp, Thread.currentThread().getName(), new Date());
    }
}
