package org.example.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * The type System application.
 */
@EnableConfigurationProperties
@ComponentScan("org.example")
@SpringBootApplication
@MapperScan("org.example.**.mapper")
public class SystemApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }

}
