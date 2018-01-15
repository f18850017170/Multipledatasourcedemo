package cn.vonfly.config.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Qualifier("readJdbcOperations")
@Autowired
public @interface ReadJdbcAutowire {
}
