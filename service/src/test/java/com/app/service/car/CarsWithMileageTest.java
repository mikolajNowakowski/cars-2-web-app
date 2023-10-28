package com.app.service.car;

import com.app.config.AppTestBeansConfig;
import com.app.model.car.Car;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.app.service.car.CarsModels.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppTestBeansConfig.class)
@TestPropertySource("classpath:application_test.properties")
public class CarsWithMileageTest {
    @Autowired
    @Qualifier("carService")
    private CarService carService;

    private final Map<Car, Long> expectedOutput = new LinkedHashMap<>(Map.of(
            AUDI, 12000L,
            BMW, 14000L,
            HONDA, 270000L));


    @Test
    @DisplayName("When method carsWithMileage() is called with specified arguments.")
    void test7() {
        Assertions
                .assertThat(carService.carsWithMileage())
                .isEqualTo(expectedOutput);
    }




}
