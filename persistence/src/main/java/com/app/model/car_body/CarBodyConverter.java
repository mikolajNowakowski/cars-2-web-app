package com.app.model.car_body;

import com.app.model.car.Car;
import com.app.model.car_body.type.CarBodyType;

import java.util.List;
import java.util.function.Function;

public interface CarBodyConverter {
    Function<CarBody,Integer> toNumberOfComponents = carBody -> carBody.components.size();
    Function<CarBody, CarBodyType> toBodyType = carBody -> carBody.type;
    Function<CarBody, List<String>> toComponents = carBody -> carBody.components;
    Function<CarBody,Long> toId = carBody -> carBody.id;
}
