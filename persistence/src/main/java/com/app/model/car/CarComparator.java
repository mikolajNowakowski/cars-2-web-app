package com.app.model.car;

import java.util.Comparator;

public interface CarComparator {
    Comparator<Car> byNumberOfComponents = Comparator.comparing(Car::getNumberOfComponents);

    Comparator<Car> byEnginePower = Comparator.comparing(Car::getPowerOfEngine);

    Comparator<Car> byWheelSize = Comparator.comparing(Car::getWheelSize);

    Comparator<Car> byModel = Comparator.comparing(car -> car.model);
}
