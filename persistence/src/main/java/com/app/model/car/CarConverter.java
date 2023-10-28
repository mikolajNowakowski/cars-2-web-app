package com.app.model.car;

import com.app.model.wheel.type.WheelType;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToLongFunction;

import static com.app.model.car_body.CarBodyConverter.*;
import static com.app.model.wheel.WheelConverter.*;

public interface CarConverter {

    ToLongFunction<Car> toMileage = car -> car.mileage;
    Function<Car, BigDecimal> toPrice = car -> car.price;
    Function<Car, WheelType> carToWheelType = car -> toWheelType.apply(car.wheel);
    Function<Car, List<String>> carToComponents = car -> toComponents.apply(car.carBody);
    Function<Car,Long> toId = car -> car.id;


}
