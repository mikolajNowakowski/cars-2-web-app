package com.app.model.dto.car;


import com.app.data.model.db.CarDb;

import static com.app.data.model.db.CarDb.*;

public record CreateCarDto(
        String model,
        String price,
        String mileage,
        CreateEngineDto createEngineDto,
        CreateCarBodyDto createCarBodyDto,
        CreateWheelDto createWheelDto) {

    public CarDb toCarDb(int engineId, int carBodyId, int wheelId){
        return carDbWithoutId(model,price,Long.parseLong(mileage),engineId,carBodyId,wheelId);
    }


}
