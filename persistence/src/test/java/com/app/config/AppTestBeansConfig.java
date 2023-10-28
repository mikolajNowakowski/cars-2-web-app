package com.app.config;

import com.app.data.converter.Converter;
import com.app.data.converter.ToCarConverter;
import com.app.data.factory.FromDbToCarWithRegexValidation;
import com.app.data.factory.FromJsonToCarWithRegexValidation;
import com.app.data.factory.FromTxtToCarWithRegexValidation;
import com.app.data.loader.CarJsonLoader;
import com.app.data.loader.CarTxtLoader;
import com.app.data.loader.Loader;
import com.app.data.loader.CarDbLoader;
import com.app.data.model.car_body.CarBodyData;
import com.app.data.model.car_data.CarData;
import com.app.data.model.engine_data.EngineData;
import com.app.data.model.filename.CarDbConnection;
import com.app.data.model.wheel_data.WheelData;
import com.app.data.process.CarDataDbProcessImpl;
import com.app.data.process.CarDataJsonProcessImpl;
import com.app.data.process.CarDataTxtProcessImpl;
import com.app.data.repository.cars_data.impl.CarBodyRepositoryImpl;
import com.app.data.repository.cars_data.impl.CarRepositoryImpl;
import com.app.data.repository.cars_data.impl.EngineRepositoryImpl;
import com.app.data.repository.cars_data.impl.WheelRepositoryImpl;
import com.app.data.validator.CarDataValidator;
import com.app.model.car.Car;
import com.google.gson.GsonBuilder;
import org.jdbi.v3.core.Jdbi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;


import java.util.List;


//@Configuration
//@ComponentScan("com.app")
@TestPropertySource({"classpath:application_test.properties"})
public class AppTestBeansConfig {

    private final Environment environment;

    public AppTestBeansConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public Converter<List<CarData>, List<Car>> toCarConverter() {
        return new ToCarConverter();
    }

    @Bean
    public Jdbi jdbi() {
        var URL = environment.getRequiredProperty("db.url");
        var USERNAME = environment.getRequiredProperty("db.username");
        var PASSWORD = environment.getRequiredProperty("db.password");
        return Jdbi.create(URL, USERNAME, PASSWORD);
    }

    @Bean
    public CarDbConnection carDbConnection(Jdbi jdbi) {
        return new CarDbConnection(jdbi);
    }

    @Bean
    public Loader<List<CarData>> carDbLoader() {
        return new CarDbLoader();
    }

    @Bean
    public CarJsonLoader carJsonLoader() {
        return new CarJsonLoader(new GsonBuilder().setPrettyPrinting().create());
    }

    @Bean
    public CarTxtLoader carTxtLoader() {
        return new CarTxtLoader();
    }

    @Bean
    public CarDataValidator carDataValidator() {
        return new CarDataValidator();
    }



    @Bean
    public FromDbToCarWithRegexValidation fromDbToCarWithRegexValidation(CarDataValidator carDataValidator) {
        return new FromDbToCarWithRegexValidation(carDataValidator);
    }

    @Bean
    public FromTxtToCarWithRegexValidation fromTxtToCarWithRegexValidation(CarDataValidator carDataValidator) {
        return new FromTxtToCarWithRegexValidation(carDataValidator);
    }

    @Bean
    public FromJsonToCarWithRegexValidation fromJsonToCarWithRegexValidation(CarDataValidator carDataValidator) {
        return new FromJsonToCarWithRegexValidation(carDataValidator);
    }

    @Bean
    public CarDataJsonProcessImpl carDataJsonProcess(FromJsonToCarWithRegexValidation fromJsonToCarWithRegexValidation) {
        return new CarDataJsonProcessImpl(fromJsonToCarWithRegexValidation);
    }

    @Bean
    public CarDataDbProcessImpl carDataDbProcess(FromDbToCarWithRegexValidation fromDbToCarWithRegexValidation) {
        return new CarDataDbProcessImpl(fromDbToCarWithRegexValidation);
    }

    @Bean
    public CarDataTxtProcessImpl carDataTxtProcess(FromTxtToCarWithRegexValidation fromTxtToCarWithRegexValidation) {
        return new CarDataTxtProcessImpl(fromTxtToCarWithRegexValidation);
    }

}
