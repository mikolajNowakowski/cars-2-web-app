package com.app.service.car;


import com.app.config.AppTestBeansConfig;
import com.app.model.car.Car;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.app.model.car.CarComparator.*;
import static com.app.service.car.CarsModels.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppTestBeansConfig.class)
@TestPropertySource("classpath:application_test.properties")
public class SortByTest {
    @Autowired
    @Qualifier("carService")
    private CarService carService;


    Map<Comparator<Car>, List<Car>> expectedValue = Map.of(
            byNumberOfComponents, List.of(AUDI,BMW,HONDA),
            byModel, List.of(AUDI,BMW,HONDA),
            byEnginePower, List.of(HONDA,BMW,AUDI),
            byWheelSize, List.of(HONDA,AUDI,BMW));


    @Test
    @DisplayName("When the inputted comparator is null")
    void test1() {
        Assertions
                .assertThatThrownBy(
                        () -> carService
                                .sortBy(null)
                )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Inputted comparator is null");
    }


    @TestFactory
    @DisplayName("When sortBy() method is call with specified and correct arguments.")
    Stream<DynamicNode> test2() {
        return expectedValue.keySet().stream().map(i -> DynamicTest.dynamicTest("Test for: " + i, () -> Assertions.assertThat(carService.sortBy(i)).isEqualTo(expectedValue.get(i))));
    }
}
