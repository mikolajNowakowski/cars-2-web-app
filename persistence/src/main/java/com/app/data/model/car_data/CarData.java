package com.app.data.model.car_data;

import com.app.data.model.car_body.CarBodyData;
import com.app.data.model.engine_data.EngineData;
import com.app.data.model.wheel_data.WheelData;
import com.app.model.car.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.math.RoundingMode;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarData {

    long id;

    String model;

    String price;

    String mileage;

    EngineData engine;

    CarBodyData carBody;

    WheelData wheel;


    public Car toCar() {
        return Car.of(
                id,
                model,
                new BigDecimal(price).setScale(2, RoundingMode.HALF_UP),
                Long.parseLong(mileage),
                engine.toEngine(),
                carBody.toCarBody(),
                wheel.toWheel()
        );
    }

    public static CarData of(long id,String model, String price, String mileage, EngineData engine, CarBodyData carBody, WheelData wheel) {
        return new CarData(id,model, price, mileage, engine, carBody, wheel);
    }
}
