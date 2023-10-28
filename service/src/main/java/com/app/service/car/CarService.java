package com.app.service.car;

import com.app.model.car.Car;
import com.app.model.car_body.type.CarBodyType;
import com.app.model.dto.car.CreateCarDto;
import com.app.model.engine.type.EngineType;
import com.app.model.wheel.type.WheelType;
import com.app.service.car.statistics.Statistics;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;


public  interface CarService   {

    Long save(CreateCarDto createCarDto);
    List<Car> sortBy(Comparator<Car> comparator);
    List<Car> carsWithSpecifiedBodyAndPriceBetween(CarBodyType bodyType, BigDecimal priceMin, BigDecimal priceMax);

    List<Car> sortedCarsWithSpecifiedTypeOfEngine(Comparator<Car> comparator, EngineType engineType);

     Statistics<BigDecimal,BigDecimal> priceStatistic();
     Statistics<Long,Double> mileageStatistic();

     Statistics<Double,Double> enginePowerStatistic();

    Map<Car,Long> carsWithMileage();

    Map<WheelType,List<Car>> wheelTypeWithCars();

    List<Car> carsWithSpecifiedComponents(Comparator<Car> comparator,List<String> components);
}
