package com.app.data.converter;

import com.app.config.AppTestBeansConfig;
import com.app.data.CarsDataModels;
import com.app.data.CarsModels;
import com.app.data.model.car_data.CarData;
import com.app.model.car.Car;
import com.app.model.car_body.CarBody;
import com.app.model.engine.Engine;
import com.app.model.wheel.Wheel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static com.app.data.CarsDataModels.*;
import static com.app.model.car_body.color.CarBodyColor.WHITE;
import static com.app.model.car_body.type.CarBodyType.COMBI;
import static com.app.model.engine.type.EngineType.LPG;
import static com.app.model.wheel.type.WheelType.WINTER;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppTestBeansConfig.class)
@TestPropertySource("classpath:application_test.properties")
public class ConvertTest {

    @Autowired
    private Converter<List<CarData>,List<Car>> toCarConverter;

    @Test
    @DisplayName("When method convert() is called with null as an argument.")
    void test(){
        Assertions
                .assertThatThrownBy(
                        () -> toCarConverter.
                            convert(null)

                ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Inputted data is null.");
    }

    @Test
    @DisplayName("When convert() method is called with correct argument.")
    void test2(){
        Assertions
                .assertThat(toCarConverter.convert(List.of(HONDA)))
                .isEqualTo(List.of(CarsModels.HONDA));
    }

    @Test
    @DisplayName("When convert() method is called with empty argument.")
    void test3(){
        Assertions
                .assertThat(toCarConverter.convert(List.of()))
                .isEmpty();
    }


}
