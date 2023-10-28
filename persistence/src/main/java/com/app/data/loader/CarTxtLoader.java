package com.app.data.loader;

import com.app.data.model.car_body.CarBodyData;
import com.app.data.model.car_data.CarData;
import com.app.data.model.engine_data.EngineData;
import com.app.data.model.filename.CarFilePath;
import com.app.data.model.filename.DataSource;
import com.app.data.model.wheel_data.WheelData;

import java.util.List;


public final class CarTxtLoader implements Loader<List<CarData>> {

    @Override
    public List<CarData> load(DataSource dataSource) {
        var carFilePath = (CarFilePath)dataSource;
        return Loader.read(carFilePath.path(), line -> carLineToCarData(List.of(line.split(";"))));
    }

    private CarData carLineToCarData(List<String> data) {
        return CarData.of(
                Long.parseLong(data.get(0)),
                data.get(1),
                data.get(2),
                data.get(3),
                EngineData.of(Long.parseLong(data.get(4)),data.get(5), data.get(6)),
                CarBodyData.of(Long.parseLong(data.get(7)),data.get(8), data.get(9), List.of(data.get(10).split(","))),
                WheelData.of(Long.parseLong(data.get(11)),data.get(12), data.get(13), data.get(14)));
    }
}




