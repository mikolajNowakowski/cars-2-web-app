package com.app.data.converter;

import com.app.data.model.car_data.CarData;
import com.app.model.car.Car;

import java.util.List;

public final class ToCarConverter implements Converter<List<CarData>, List<Car>> {
    @Override
    public List<Car> convert(List<CarData> data) {
        if(data==null){
            throw new IllegalArgumentException("Inputted data is null.");
        }

        return data
                .stream()
                .map(CarData::toCar)
                .toList();
    }
}
