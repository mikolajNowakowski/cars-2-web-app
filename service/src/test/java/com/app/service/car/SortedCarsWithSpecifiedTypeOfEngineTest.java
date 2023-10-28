package com.app.service.car;

import com.app.config.AppTestBeansConfig;
import com.app.model.car.Car;
import com.app.model.engine.type.EngineType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static com.app.model.car.CarComparator.*;
import static com.app.model.engine.type.EngineType.*;
import static com.app.service.car.CarsModels.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppTestBeansConfig.class)
@TestPropertySource("classpath:application_test.properties")
public class SortedCarsWithSpecifiedTypeOfEngineTest {
    @Autowired
    @Qualifier("carService")
    private CarService carService;

    private final Map<EngineType, List<Car>> expectedOutput = Map.of(
            DIESEL,List.of(AUDI),
            GASOLINE,List.of(BMW),
            LPG,List.of(HONDA)
    );


    @Test
    @DisplayName("When comparator is null")
    void test1() {
            Assertions
                    .assertThatThrownBy(
                            () -> carService
                                    .sortedCarsWithSpecifiedTypeOfEngine(null, LPG)
                    )
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("At least one of input arguments equals null");
        }

    @Test
    @DisplayName("When EngineType is null")
    void test2() {
        Assertions
                .assertThatThrownBy(
                        () -> carService
                                .sortedCarsWithSpecifiedTypeOfEngine(byEnginePower, null)
                )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("At least one of input arguments equals null");
    }

    @ParameterizedTest
    @EnumSource(value= EngineType.class, mode= EnumSource.Mode.MATCH_ALL)
    @DisplayName("When input arguments are correct ant method should generate specified output")
    void test3(EngineType engineType) {
        Assertions
                .assertThat(carService.sortedCarsWithSpecifiedTypeOfEngine(byEnginePower, engineType))
                .isEqualTo(expectedOutput.get(engineType));
    }
}



