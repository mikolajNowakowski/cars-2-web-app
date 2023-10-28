package com.app.config;

import com.app.data.converter.Converter;
import com.app.data.converter.ToCarConverter;
import com.app.data.factory.FromDbToCarWithRegexValidation;
import com.app.data.factory.FromJsonToCarWithRegexValidation;
import com.app.data.factory.FromTxtToCarWithRegexValidation;
import com.app.data.loader.CarDbLoader;
import com.app.data.loader.CarJsonLoader;
import com.app.data.loader.CarTxtLoader;
import com.app.data.loader.Loader;
import com.app.data.model.car_data.CarData;
import com.app.data.model.filename.CarDbConnection;
import com.app.data.process.CarDataDbProcessImpl;
import com.app.data.process.CarDataJsonProcessImpl;
import com.app.data.process.CarDataProcess;
import com.app.data.process.CarDataTxtProcessImpl;
import com.app.data.repository.cars_data.impl.CarBodyRepositoryImpl;
import com.app.data.repository.cars_data.impl.CarRepositoryImpl;
import com.app.data.repository.cars_data.impl.EngineRepositoryImpl;
import com.app.data.repository.cars_data.impl.WheelRepositoryImpl;
import com.app.data.repository.cars_data.transaction.CarDataDbRepository;
import com.app.data.repository.cars_data.transaction.impl.CarDataDbRepositoryImpl;
import com.app.data.repository.user.UserRepository;
import com.app.data.repository.user.impl.UserRepositoryImpl;
import com.app.data.validator.CarDataValidator;
import com.app.model.car.Car;
import com.app.service.car.CarService;
import com.app.service.car.impl.CarServiceImpl;
import com.app.service.car.provider.CarProvider;
import com.app.service.car.provider.impl.CarProviderImpl;
import com.app.service.email.EmailService;
import com.app.service.email.impl.EmailServiceImpl;
import com.app.service.user.impl.UserServiceImpl;
import com.google.gson.GsonBuilder;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.jdbi.v3.core.Jdbi;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.config.ConfigLoader;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.TestPropertySource;

import javax.crypto.SecretKey;
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
    public CarDataDbRepositoryImpl carDataDbRepository(Jdbi jdbi) {
        return new CarDataDbRepositoryImpl(jdbi);
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
    public CarRepositoryImpl carRepository(Jdbi jdbi) {
        return new CarRepositoryImpl(jdbi);
    }

    @Bean
    public CarBodyRepositoryImpl carBodyRepository(Jdbi jdbi) {
        return new CarBodyRepositoryImpl(jdbi);
    }

    @Bean
    public WheelRepositoryImpl wheelRepository(Jdbi jdbi) {
        return new WheelRepositoryImpl(jdbi);
    }

    @Bean
    public EngineRepositoryImpl engineRepository(Jdbi jdbi) {
        return new EngineRepositoryImpl(jdbi);
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

    @Bean
    public CarProvider carProvider(CarDataDbProcessImpl carDataDbProcess,
                                   CarDataTxtProcessImpl carDataTxtProcess,
                                   CarDataJsonProcessImpl carDataJsonProcess,
                                   CarDbConnection carDbConnection) {
        return new CarProviderImpl(carDataDbProcess,
                carDataTxtProcess,
                carDataJsonProcess,
                carDbConnection);
    }

    @Bean("carService")
    public CarService carService(CarProvider carProvider, CarDataDbRepositoryImpl carDataDbRepository) {
        return new CarServiceImpl(carProvider, carDataDbRepository);
    }

    @Bean("carServiceSaveTest")
    public CarService carServiceSaveTest(CarProvider carProvider, CarDataDbRepositoryImpl carDataDbRepository) {
        return new CarServiceImpl(carProvider, carDataDbRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecretKey secretKey() {
        return Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    @Bean
    public UserRepository userRepository(Jdbi jdbi) {
        return new UserRepositoryImpl(jdbi);
    }

}
