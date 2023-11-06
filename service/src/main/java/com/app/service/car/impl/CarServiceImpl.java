package com.app.service.car.impl;

import com.app.data.repository.cars_data.transaction.impl.CarDataDbRepositoryImpl;
import com.app.model.car.Car;
import com.app.model.car_body.color.CarBodyColor;
import com.app.model.car_body.type.CarBodyType;
import com.app.model.dto.car.CreateCarDto;
import com.app.model.engine.type.EngineType;
import com.app.model.wheel.type.WheelType;
import com.app.service.car.CarService;
import com.app.service.car.provider.CarProvider;
import com.app.service.car.statistics.Statistics;
import com.app.service.car.utilities.BigDecimalSummaryStatistics;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.app.data.validator.Validator.*;
import static java.util.EnumSet.*;
import static java.util.stream.Collectors.*;
import static com.app.model.car.CarConverter.*;

@Service
public final class CarServiceImpl implements CarService {
    private final ArrayList<Car> cars;

    private final CarDataDbRepositoryImpl carDataDbRepositoryImpl;

    public CarServiceImpl(CarProvider carProvider, CarDataDbRepositoryImpl carDataDbRepositoryImpl) {
        this.carDataDbRepositoryImpl = carDataDbRepositoryImpl;
        cars = new ArrayList<>(carProvider.provide());
    }

    /***
     *
     * @param createCarDto car's data
     * @return ID of added car
     */
    @Override
    public Long save(CreateCarDto createCarDto) {

        if (createCarDto == null) {
            throw new IllegalArgumentException("Inputted instance of CreateCarDto is null.");
        }

        if (!validateCreateCarDto(createCarDto)) {
            throw new IllegalArgumentException("Wrong input data.");
        }

        var addedCar = carDataDbRepositoryImpl.save(createCarDto).toCar();
        this.cars.add(addedCar);
        return toId.apply(addedCar);
    }

    /***
     *
     * @param comparator Car comparator.
     * @return Collection of sorted cars
     */
    @Override
    public List<Car> sortBy(Comparator<Car> comparator) {
        if (comparator == null) {
            throw new IllegalArgumentException("Inputted comparator is null");
        }
        return cars
                .stream()
                .sorted(comparator)
                .toList();
    }


    /***
     *
     * @param bodyType The type of car body with which the cars will be returned.
     * @param priceMin Min price of teh cheapest returned car.
     * @param priceMax Max price of teh most expensive returned car.
     * @return Collection of cars with specified car body and price form inputted range.
     */
    @Override
    public List<Car> carsWithSpecifiedBodyAndPriceBetween(CarBodyType bodyType, BigDecimal priceMin, BigDecimal priceMax) {
        if (bodyType == null || priceMin == null || priceMax == null) {
            throw new IllegalArgumentException("At least one of input arguments equals null");
        }

        if (!isGreaterThanOrEqual(priceMax, priceMin) || !isGreaterThanOrEqual(priceMin, BigDecimal.valueOf(0)) || !isGreaterThanOrEqual(priceMax, BigDecimal.valueOf(0))) {
            throw new IllegalArgumentException("Inputted prices are not correct.");
        }

        return cars
                .stream()
                .filter(car -> car.hasPriceBetween(priceMin, priceMax) && car.hasBodyTypeAs(bodyType))
                .toList();
    }

    /***
     *
     * @param comparator Car comparator.
     * @param engineType TThe type of engine with which the cars will be returned.
     * @return Sorted cars with specified type of engine.
     */
    @Override
    public List<Car> sortedCarsWithSpecifiedTypeOfEngine(Comparator<Car> comparator, EngineType engineType) {
        if (comparator == null || engineType == null) {
            throw new IllegalArgumentException("At least one of input arguments equals null");
        }

        return cars
                .stream()
                .filter(car -> car.hasEngineType(engineType))
                .sorted(comparator)
                .toList();
    }

    /***
     *
     * @return Statistics of car's prices.
     */
    @Override
    public Statistics<BigDecimal, BigDecimal> priceStatistic() {

        if (cars.isEmpty()) {
            return Statistics.create(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        }
        return (cars.stream().map(toPrice).collect(new BigDecimalSummaryStatistics()));
    }

    /***
     *
     * @return Statistics of car's mileage.
     */
    @Override
    public Statistics<Long, Double> mileageStatistic() {
        if (cars.isEmpty()) {
            return Statistics.create(0L, 0L, 0.0);
        }

        return Statistics.of(cars.stream().collect(summarizingLong(toMileage)));
    }

    /***
     *
     * @return Statistics of car's power.
     */
    @Override
    public Statistics<Double, Double> enginePowerStatistic() {
        if (cars.isEmpty()) {
            return Statistics.create(0.0, 0.0, 0.0);
        }

        return Statistics.of(cars.stream().collect(summarizingDouble(Car::getPowerOfEngine)));
    }

    /***
     *
     * @return Map where key is car and value its mileage.
     */
    @Override
    public Map<Car, Long> carsWithMileage() {
        return cars
                .stream()
                .collect(toMap(Function.identity(), toMileage::applyAsLong))
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> v1,
                        LinkedHashMap::new));
    }

    /***
     *
     * @return Map with wheel type as key and list of cars with that wheel as value.
     */
    @Override
    public Map<WheelType, List<Car>> wheelTypeWithCars() {
        return cars
                .stream()
                .collect(groupingBy(carToWheelType))
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue(Comparator.comparing(List::size))))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));
    }

    /***
     *
     * @param comparator Car comparator.
     * @param components List of components.
     * @return Collection of sorted cars with specified components.
     */
    @Override
    public List<Car> carsWithSpecifiedComponents(Comparator<Car> comparator, List<String> components) {

        if (components == null || comparator == null) {
            throw new IllegalArgumentException("At least one inputted argument is null");
        }

        if (components.size() == 0) {
            throw new IllegalArgumentException("Inputted list of components is empty");
        }

        return cars
                .stream()
                .filter(car -> carToComponents.apply(car).stream().anyMatch(components::contains))
                .sorted(comparator)
                .toList();
    }

    private boolean validateCreateCarDto(CreateCarDto createCarDto) {
        return createCarDto.model().matches("[A-Z ]+") &&
                createCarDto.price().matches("^(?!0\\.00$)\\d+\\.\\d{2}$") &&
                createCarDto.mileage().matches("[0-9]+") &&
                createCarDto.createEngineDto().enginePower().matches("^(?!0\\.00$)\\d+\\.\\d{1}$") &&
                createCarDto.createEngineDto().engineType().matches("(%s)".formatted(allOf(EngineType.class).stream().map(Enum::toString).collect(Collectors.joining("|")))) &&
                createCarDto.createCarBodyDto().carBodyColor().matches("(%s)".formatted(allOf(CarBodyColor.class).stream().map(Enum::toString).collect(Collectors.joining("|")))) &&
                createCarDto.createCarBodyDto().carBodyType().matches("(%s)".formatted(allOf(CarBodyType.class).stream().map(Enum::toString).collect(Collectors.joining("|")))) &&
                createCarDto.createCarBodyDto().components().replaceAll(",", "").replaceAll(" ", "").matches("[A-Z]+") &&
                createCarDto.createWheelDto().wheelType().matches("(%s)".formatted(allOf(WheelType.class).stream().map(Enum::toString).collect(Collectors.joining("|")))) &&
                createCarDto.createWheelDto().wheelSize().matches("[0-9]+") &&
                createCarDto.createWheelDto().wheelModel().matches("[A-Z ]+");
    }
}
