package cn.vonfly;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * Hello world!
 *
 */
@Configuration
public class App 
{
    public static void main( String[] args ) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RootConfig.class);
        context.start();
    }
}
