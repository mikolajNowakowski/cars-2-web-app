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

import java.util.List;

import static com.app.model.car.CarComparator.*;
import static com.app.service.car.CarsModels.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppTestBeansConfig.class)
@TestPropertySource("classpath:application_test.properties")
public class CarsWithSpecifiedComponentsTest {
    @Autowired
    @Qualifier("carService")
    private CarService carService;


    List<Car> expectedOutput5 = List.of(
            HONDA,BMW);


    List<Car> expectedOutput6 = List.of(AUDI,BMW,HONDA);


    @Test
    @DisplayName("When inputted comparator is null")
    void test1() {
        Assertions
                .assertThatThrownBy(
                        () -> carService
                                .carsWithSpecifiedComponents(null, List.of("A", "B"))
                )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("At least one inputted argument is null");
    }

    @Test
    @DisplayName("When inputted list of components is null")
    void test2() {
        Assertions
                .assertThatThrownBy(
                        () -> carService
                                .carsWithSpecifiedComponents(byEnginePower, null)
                )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("At least one inputted argument is null");
    }

    @Test
    @DisplayName("When inputted data has correct type but list of components is empty")
    void test3() {
        Assertions
                .assertThatThrownBy(
                        () -> carService
                                .carsWithSpecifiedComponents(byEnginePower, List.of())
                )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Inputted list of components is empty");
    }

    @Test
    @DisplayName("When method carWithSpecifiedComponents() is call with inputted list of components which doesn't exist")
    void test4() {
        Assertions
                .assertThat(
                        carService.carsWithSpecifiedComponents(byEnginePower, List.of("ZZZZZZ")))
                .isEmpty();

    }

    @Test
    @DisplayName("When method carWithSpecifiedComponents() is called with correct comparator and correct list of components")
    void test5() {
        Assertions
                .assertThat(carService.carsWithSpecifiedComponents(byWheelSize, List.of( "CAMERA")))
                .isEqualTo(expectedOutput5);
    }
    @Test
    @DisplayName("When method carWithSpecifiedComponents() is called with correct comparator and correct list of components")
    void test6() {
        Assertions
                .assertThat(carService.carsWithSpecifiedComponents(byNumberOfComponents, List.of("ABS")))
                .isEqualTo(expectedOutput6);
    }



}
