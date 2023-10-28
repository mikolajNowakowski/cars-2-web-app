package com.app.data.process;

import com.app.model.car.Car;

import java.util.List;

public sealed interface CarDataProcess extends DataProcess<List<Car>> permits CarDataDbProcessImpl, CarDataJsonProcessImpl, CarDataTxtProcessImpl {
}
