package com.app.model.dto.car;

import com.app.data.model.db.CarBodyDb;
import com.app.model.car_body.CarBody;
import com.app.model.car_body.color.CarBodyColor;
import com.app.model.car_body.type.CarBodyType;

import java.util.Arrays;

public record CreateCarBodyDto(
        String carBodyColor,
        String carBodyType,
        String components) {

    public CarBody toCarBody(){
        return  CarBody.of(null,CarBodyColor.valueOf(carBodyColor), CarBodyType.valueOf(carBodyType), Arrays.stream(components.split(",")).toList());
    }

    public CarBodyDb toCarBodyDb(){
        return CarBodyDb.carBodyDbWithoutId(carBodyColor,carBodyType,components);
    }

}
