package com.app.service.car;


import com.app.config.AppTestBeansConfig;
import com.app.model.car.Car;
import com.app.model.wheel.type.WheelType;
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
import java.util.List;
import java.util.Map;
import static com.app.model.wheel.type.WheelType.SUMMER;
import static com.app.model.wheel.type.WheelType.WINTER;
import static com.app.service.car.CarsModels.*;
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppTestBeansConfig.class)
@TestPropertySource("classpath:application_test.properties")
public class WheelTypeWithCarsTest {
    @Autowired
    @Qualifier("carService")
    private CarService carService;

    private final Map<WheelType, List<Car>> expectedOutput = new LinkedHashMap<>(Map.of(
            SUMMER, List.of(AUDI),
            WINTER, List.of(BMW, HONDA)
    ));

    @Test
    @DisplayName("When wheelTypeWithCars() method is called with specified argument and it should produce the expected output.")
    void test1() {
        Assertions
                .assertThat(carService.wheelTypeWithCars())
                .isEqualTo(this.expectedOutput);
    }
}
