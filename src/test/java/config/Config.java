package config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author xp
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"xp.wheel.easydao"})
public class Config {

}
