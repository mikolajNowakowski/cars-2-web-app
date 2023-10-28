package com.app.data;

import com.app.model.car.Car;
import com.app.model.car_body.CarBody;
import com.app.model.engine.Engine;
import com.app.model.wheel.Wheel;

import java.math.BigDecimal;
import java.util.List;

import static com.app.model.car_body.color.CarBodyColor.*;
import static com.app.model.car_body.type.CarBodyType.*;
import static com.app.model.engine.type.EngineType.*;
import static com.app.model.wheel.type.WheelType.*;

public interface CarsModels {


     Car HONDA = Car.of(3L, "HONDA", new BigDecimal("29.00"), 270000L,
             Engine.of(3L, LPG, 141.0),
             CarBody.of(3L, WHITE, COMBI, List.of("ABS", "AIR CONDITIONING", "CAMERA", "CAR AUDIO")),
             Wheel.of(3L, "PIRELLI", 17, WINTER));

     Car AUDI = Car.of(1L, "AUDI", new BigDecimal("120.00"), 12000L,
             Engine.of(1L, DIESEL, 210.0),
             CarBody.of(1L, BLACK, HATCHBACK, List.of("ABS", "AIR CONDITIONING")),
             Wheel.of(1L, "PIRELLI", 18, SUMMER));
     Car BMW = Car.of(2L, "BMW", new BigDecimal("170.00"), 14000L,
             Engine.of(2L, GASOLINE, 170.0),
             CarBody.of(2L, BLUE, SEDAN, List.of("ABS", "AIR CONDITIONING", "CAMERA")),
             Wheel.of(2L, "MICHELIN", 19, WINTER));
}