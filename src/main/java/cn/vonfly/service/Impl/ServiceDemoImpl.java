package cn.vonfly.service.Impl;

import cn.vonfly.config.annotation.WriteJdbcAutowire;
import cn.vonfly.service.ServiceDemo;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceDemoImpl implements ServiceDemo {
    @WriteJdbcAutowire
    private JdbcOperations jdbcOperations;
    @Override
    @Transactional(transactionManager = "writeDataSourceTransactionManager")//指定事务处管理器
    public void transactionMethod() {
        jdbcOperations.update("xxxx");
        jdbcOperations.update("yyyy");
    }
}
