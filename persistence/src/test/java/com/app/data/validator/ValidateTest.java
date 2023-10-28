package com.app.data.validator;

import com.app.config.AppTestBeansConfig;
import com.app.data.model.car_body.CarBodyData;
import com.app.data.model.car_data.CarData;
import com.app.data.model.engine_data.EngineData;
import com.app.data.model.wheel_data.WheelData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.app.data.CarsDataModels.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppTestBeansConfig.class)
@TestPropertySource("classpath:application_test.properties")
public class ValidateTest {
    @Value("${validator.modelRegex}")
    private String modelRegex;
    @Value("${validator.componentRegex}")
    private String componentRegex;
    @Autowired
    private CarDataValidator carDataValidator;

    @Test
    @DisplayName("When validate() is called with correct arguments and every object has correct fields.")
    void test1(){

        List<CarData> inputData = List.of(AUDI,BMW,HONDA);

        Assertions
                .assertThat(carDataValidator.validate(inputData))
                .isEqualTo(inputData);
    }


    @Test
    @DisplayName("When validate() is called with correct arguments and not every object has correct fields.")
    void test2(){

        List<CarData> inputData = List.of(
                CarData.of(1L,"AuDI", "120", "12000",
                        EngineData.of(1L,"DIESEL", "210.0"),
                        CarBodyData.of(1L,"BLACK", "HATCHBACK", List.of("ABS", "AIR CONDITIONING")),
                        WheelData.of(1L,"PIRELLI", "18", "SUMMER")),
                CarData.of(2L,"", "170", "14000",
                        EngineData.of(2L,"GASOLINE", "170.0"),
                        CarBodyData.of(2L,"BLUE", "SEDAN", List.of("ABS", "air CONDITIONING", "CAMERA")),
                        WheelData.of(2L,"MICHELIN", "19", "WINTER")),
                HONDA,
                CarData.of(3L,"MERCEDES", "50", "290000",
                        EngineData.of(3L,"DIESEL", "240.0"),
                        CarBodyData.of(3L,"WHITE", "COMBI", List.of("ABS2", "AIR CONDITIONING", "CAMERA4", "CAR AUDIO")),
                        WheelData.of(3L,"PIRELLI", "18", "SUMMER")));

        Assertions
                .assertThat(carDataValidator.validate(inputData))
                .isEqualTo(List.of(HONDA));
    }
}
