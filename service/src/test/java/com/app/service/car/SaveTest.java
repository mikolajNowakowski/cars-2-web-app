package com.app.service.car;

import com.app.config.AppTestBeansConfig;
import com.app.data.model.car_body.CarBodyData;
import com.app.data.model.car_data.CarData;
import com.app.data.model.engine_data.EngineData;
import com.app.data.model.wheel_data.WheelData;
import com.app.data.repository.cars_data.impl.CarBodyRepositoryImpl;
import com.app.data.repository.cars_data.impl.CarRepositoryImpl;
import com.app.data.repository.cars_data.impl.EngineRepositoryImpl;
import com.app.data.repository.cars_data.impl.WheelRepositoryImpl;
import com.app.data.repository.cars_data.transaction.impl.CarDataDbRepositoryImpl;
import com.app.model.dto.car.CreateCarBodyDto;
import com.app.model.dto.car.CreateCarDto;
import com.app.model.dto.car.CreateEngineDto;
import com.app.model.dto.car.CreateWheelDto;
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

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppTestBeansConfig.class)
@TestPropertySource("classpath:application_test.properties")
public class SaveTest {
    @Autowired
    @Qualifier("carServiceSaveTest")
    private CarService carServiceSaveTest;
    @Autowired
    private CarDataDbRepositoryImpl carRepository;
    @Autowired
    private CarRepositoryImpl carsRepository;
    @Autowired
    private CarBodyRepositoryImpl carBodiesRepository;
    @Autowired
    private WheelRepositoryImpl wheelsRepository;
    @Autowired
    private EngineRepositoryImpl enginesRepository;


    @Test
    @DisplayName("When inputted argument is null.")
    void test1() {
        Assertions
                .assertThatThrownBy(
                        () -> carServiceSaveTest
                                .save(null)
                )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Inputted instance of CreateCarDto is null.");
    }

    @Test
    @DisplayName("When inputted data is not correct.")
    void test2() {
        var inputData = new CreateCarDto(
                "asasasas",
                "25000",
                "11111",
                new CreateEngineDto("LPG", "150.0"),
                new CreateCarBodyDto("BLACK", "COMBI", "A,B,C,Z"),
                new CreateWheelDto("PIRELLI", "18", "WINTER"));


        Assertions
                .assertThatThrownBy(
                        () -> carServiceSaveTest
                                .save(inputData)
                )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Wrong input data.");
    }

    @Test
    @DisplayName("When inputted data is correct.")
    void test3() {

        var inputData = new CreateCarDto(
                "ZZZ",
                "25000.00",
                "11111",
                new CreateEngineDto("LPG", "150.0"),
                new CreateCarBodyDto("BLACK", "COMBI", "A,B,C,Z"),
                new CreateWheelDto("PIRELLI", "18", "WINTER"));

        carServiceSaveTest.save(inputData);

        var cars = carRepository.findAllCarData();
        var lastCarId = cars.get(cars.size() - 1).getId();
        var lastEngineId = cars.get(cars.size() - 1).getEngine().getId();
        var lastCarBodyId = cars.get(cars.size() - 1).getCarBody().getId();
        var lastWheelId = cars.get(cars.size() - 1).getWheel().getId();

        carServiceSaveTest.save(inputData);

        var updatedCars = carRepository.findAllCarData();
        var addedCar = updatedCars.get(updatedCars.size() - 1);

        removeCar(lastEngineId,lastWheelId,lastCarBodyId,lastCarId);
        removeCar(lastEngineId+1,lastWheelId+1,lastCarBodyId+1,lastCarId+1);

        Assertions
                .assertThat(addedCar)
                .isEqualTo(CarData.of(lastCarId + 1, "ZZZ", "25000.00", "11111",
                        EngineData.of(lastEngineId + 1, "LPG", "150.0"),
                        CarBodyData.of(lastCarBodyId + 1, "BLACK", "COMBI", List.of("A", "B", "C", "Z")),
                        WheelData.of(lastWheelId + 1, "PIRELLI", "18", "WINTER")));
    }

    private void removeCar(Long engineId, Long wheelId, Long carBodyId, Long carId ){
        carsRepository.delete(carId);
        carBodiesRepository.delete(carBodyId);
        wheelsRepository.delete(wheelId);
        enginesRepository.delete(engineId);
    }
}
