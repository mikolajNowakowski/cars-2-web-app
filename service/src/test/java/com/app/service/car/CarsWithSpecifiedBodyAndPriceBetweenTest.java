package com.app.service.car;


import com.app.config.AppTestBeansConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static com.app.model.car_body.type.CarBodyType.*;
import static com.app.service.car.CarsModels.AUDI;
import static java.math.BigDecimal.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppTestBeansConfig.class)
@TestPropertySource("classpath:application_test.properties")
public class CarsWithSpecifiedBodyAndPriceBetweenTest {
    @Autowired
    @Qualifier("carService")
    private CarService carService;


    @Test
    @DisplayName("When input range is not correct.")
    void test1(){
        Assertions
                .assertThatThrownBy(
                        () -> carService
                                .carsWithSpecifiedBodyAndPriceBetween(COMBI, TEN, ZERO)
                )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Inputted prices are not correct.");
    }

    @Test
    @DisplayName("When CarBodyType is null")
    void test2(){
        Assertions
                .assertThatThrownBy(
                        () -> carService
                                .carsWithSpecifiedBodyAndPriceBetween(null, ZERO, TEN)
                )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("At least one of input arguments equals null");
    }

    @Test
    @DisplayName("When priceMin is null")
    void test3(){
        Assertions
                .assertThatThrownBy(
                        () -> carService
                                .carsWithSpecifiedBodyAndPriceBetween(COMBI, null, TEN)
                )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("At least one of input arguments equals null");
    }

    @Test
    @DisplayName("When priceMax is null")
    void test4(){
        Assertions
                .assertThatThrownBy(
                        () -> carService
                                .carsWithSpecifiedBodyAndPriceBetween(COMBI, ZERO, null)
                )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("At least one of input arguments equals null");
    }

    @Test
    @DisplayName("When method carsWithSpecifiedBodyAndPriceBetween() is called with specified arguments, and there is a car with specified price, but no with specified carBody.")
    void test5(){
        Assertions
                .assertThat(carService.carsWithSpecifiedBodyAndPriceBetween(SEDAN, BigDecimal.valueOf(110), BigDecimal.valueOf(130)))
                .isEmpty();
    }

    @Test
    @DisplayName("When method carsWithSpecifiedBodyAndPriceBetween() is called with specified arguments, and there is a car with specified carBody, but no with specified price.")
    void test6(){
        Assertions
                .assertThat(carService.carsWithSpecifiedBodyAndPriceBetween(HATCHBACK, BigDecimal.valueOf(100), BigDecimal.valueOf(105)))
                .isEmpty();
    }

    @Test
    @DisplayName("When method carsWithSpecifiedBodyAndPriceBetween() is called with specified arguments.")
    void test7(){
        Assertions
                .assertThat(carService.carsWithSpecifiedBodyAndPriceBetween(HATCHBACK, BigDecimal.valueOf(110), BigDecimal.valueOf(130)))
                .isEqualTo(List.of(AUDI));
    }


}
