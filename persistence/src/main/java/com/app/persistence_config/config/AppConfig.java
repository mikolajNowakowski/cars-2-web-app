package com.app.persistence_config.config;

import com.app.data.factory.FromDbToCarWithRegexValidation;
import com.app.data.factory.FromJsonToCarWithRegexValidation;
import com.app.data.factory.FromTxtToCarWithRegexValidation;
import com.app.data.model.filename.CarDbConnection;
import com.app.data.process.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import org.jdbi.v3.core.Jdbi;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;



@Configuration
@ComponentScan("com.app")
@PropertySource({"classpath:application.properties"})
@RequiredArgsConstructor
public class AppConfig {

    private final Environment environment;

    @Bean
    public Jdbi jdbi() {
        var URL = environment.getRequiredProperty("db.url");
        var USERNAME = environment.getRequiredProperty("db.username");
        var PASSWORD = environment.getRequiredProperty("db.password");
        return Jdbi.create(URL, USERNAME, PASSWORD);
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }


    @Bean
    public CarDbConnection carDbConnection(Jdbi jdbi) {
        return new CarDbConnection(jdbi);
    }

    @Bean
    public CarDataProcess carDataDbProcess(FromDbToCarWithRegexValidation factory) {
        return new CarDataDbProcessImpl(factory);
    }

    @Bean
    public CarDataProcess carDataJsonProcess(FromJsonToCarWithRegexValidation factory) {
        return new CarDataJsonProcessImpl(factory);
    }

    @Bean
    public CarDataProcess carDataTxtProcess(FromTxtToCarWithRegexValidation factory) {
        return new CarDataTxtProcessImpl(factory);
    }
}
