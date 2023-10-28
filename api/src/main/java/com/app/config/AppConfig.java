package com.app.config;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("com.app")
@Import({
        com.app.service.service_config.config.AppConfig.class,
        com.app.persistence_config.config.AppConfig.class
})
public class AppConfig {

}
