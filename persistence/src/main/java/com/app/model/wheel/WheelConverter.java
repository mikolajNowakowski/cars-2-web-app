package com.app.model.wheel;

import com.app.model.car_body.CarBody;
import com.app.model.wheel.type.WheelType;

import java.util.function.Function;

public interface WheelConverter {

    Function<Wheel,Integer> toWheelSize = wheel -> wheel.size;
    Function<Wheel, WheelType> toWheelType = wheel -> wheel.type;
    Function<Wheel,Long> toId = wheel -> wheel.id;

}
