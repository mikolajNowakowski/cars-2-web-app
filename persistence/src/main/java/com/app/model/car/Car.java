package com.app.model.car;

import com.app.model.car_body.CarBody;
import com.app.model.car_body.type.CarBodyType;
import com.app.model.engine.Engine;
import com.app.model.engine.type.EngineType;
import com.app.model.wheel.Wheel;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;


import static com.app.model.car_body.CarBodyConverter.*;
import static com.app.model.engine.EngineConverter.*;
import static com.app.model.wheel.WheelConverter.*;
import static com.app.utilities.MathOperations.*;


@EqualsAndHashCode
public class Car {
    final long id;
    final String model;
    final BigDecimal price;
    final long mileage;
    final Engine engine;
    final CarBody carBody;
    final Wheel wheel;

    private Car(long id, String model, BigDecimal price, long mileage, Engine engine, CarBody carBody, Wheel wheel) {
        this.id = id;
        this.model = model;
        this.price = price;
        this.mileage = mileage;
        this.engine = engine;
        this.carBody = carBody;
        this.wheel = wheel;
    }

    public boolean hasEngineType(EngineType engineType) {
        return toEngineType.apply(this.engine).equals(engineType);
    }

    public boolean hasPriceBetween(BigDecimal priceMin, BigDecimal priceMax) {
        return isBetween(this.price, priceMin, priceMax);
    }

    public boolean hasBodyTypeAs(CarBodyType carBodyType) {
        return toBodyType.apply(this.carBody).equals(carBodyType);
    }

    public int getWheelSize() {
        return toWheelSize.apply(this.wheel);
    }

    public double getPowerOfEngine() {
        return toEnginePower.apply(this.engine);
    }

    public int getNumberOfComponents() {
        return toNumberOfComponents.apply(this.carBody);
    }

    public boolean hasComponent(String component) {
        return carBody.hasComponent(component);
    }


    @Override
    public String toString() {
        return "CAR =>\nMODEL = %s\nPRICE = %s \nMILEAGE = %d \nENGINE = %s \nCARBODY = %s \nWHEEL = %s".formatted(
                model, price, mileage, engine.toString(), carBody.toString(), wheel.toString());
    }


    public static Car of(long id,String model, BigDecimal price, long mileage, Engine engine, CarBody carBody, Wheel wheel) {
        return new Car(id, model, price, mileage, engine, carBody, wheel);
    }
}
