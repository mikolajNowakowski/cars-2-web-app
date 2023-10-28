package com.app.data;

import com.app.data.model.car_body.CarBodyData;
import com.app.data.model.car_data.CarData;
import com.app.data.model.engine_data.EngineData;
import com.app.data.model.wheel_data.WheelData;

import java.util.List;

public interface CarsDataModels {

     CarData HONDA = CarData.of(3L,"HONDA", "29.00", "270000",
            EngineData.of(3L,"LPG", "141.0"),
            CarBodyData.of(3L,"WHITE", "COMBI", List.of("ABS", "AIR CONDITIONING", "CAMERA", "CAR AUDIO")),
            WheelData.of(3L,"PIRELLI", "17", "WINTER"));

     CarData AUDI = CarData.of(1L,"AUDI", "120.00", "12000",
             EngineData.of(1L,"DIESEL", "210.0"),
             CarBodyData.of(1L,"BLACK", "HATCHBACK", List.of("ABS", "AIR CONDITIONING")),
             WheelData.of(1L,"PIRELLI", "18", "SUMMER"));

     CarData BMW = CarData.of(2L,"BMW", "170.00", "14000",
             EngineData.of(2L,"GASOLINE", "170.0"),
             CarBodyData.of(2L,"BLUE", "SEDAN", List.of("ABS", "AIR CONDITIONING", "CAMERA")),
             WheelData.of(2L,"MICHELIN", "19", "WINTER"));




}
