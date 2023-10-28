package com.app.model.dto.car;

import com.app.data.model.db.WheelDb;
import com.app.model.wheel.Wheel;
import com.app.model.wheel.type.WheelType;

import static com.app.model.wheel.type.WheelType.*;

public record CreateWheelDto(
        String wheelModel,
        String wheelSize,
        String wheelType) {

    public Wheel toWheel(){
        return Wheel.of(null,wheelModel,Integer.parseInt(wheelSize), valueOf(wheelType));
    }

    public WheelDb toWheelDb(){
        return WheelDb.wheelDbWithoutId(wheelType,wheelModel,Integer.parseInt(wheelSize));
    }
}
