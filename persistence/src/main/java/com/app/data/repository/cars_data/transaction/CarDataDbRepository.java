package com.app.data.repository.cars_data.transaction;

import com.app.model.dto.car.CreateCarDto;

import java.util.List;

public interface CarDataDbRepository<T> {

    List<T> findAllCarData();
    T save(CreateCarDto data);


}
