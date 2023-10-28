package com.app.data.repository.cars_data.transaction.impl;

import com.app.data.repository.cars_data.impl.CarBodyRepositoryImpl;
import com.app.data.repository.cars_data.impl.CarRepositoryImpl;
import com.app.data.repository.cars_data.impl.EngineRepositoryImpl;
import com.app.data.repository.cars_data.impl.WheelRepositoryImpl;
import com.app.data.repository.cars_data.transaction.CarDataDbRepository;
import com.app.data.model.car_data.CarData;
import com.app.data.model.db.Identifiable;
import com.app.model.dto.car.CreateCarDto;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class CarDataDbRepositoryImpl implements CarDataDbRepository<CarData> {
    private final CarRepositoryImpl carsRepository;
    private final CarBodyRepositoryImpl carBodiesRepository;
    private final WheelRepositoryImpl wheelsRepository;
    private final EngineRepositoryImpl enginesRepository;

    public CarDataDbRepositoryImpl(Jdbi jdbi){
        carsRepository = new CarRepositoryImpl(jdbi);
        carBodiesRepository = new CarBodyRepositoryImpl(jdbi);
        wheelsRepository = new WheelRepositoryImpl(jdbi);
        enginesRepository = new EngineRepositoryImpl(jdbi);
    }

    @Override
    public List<CarData> findAllCarData() {
            var wheelMap = getCarElementsWithIds(wheelsRepository.findAll());
            var carBodyMap = getCarElementsWithIds(carBodiesRepository.findAll());
            var engineMap = getCarElementsWithIds(enginesRepository.findAll());

            return carsRepository
                    .findAll()
                    .stream()
                    .map(dto -> CarData.of(
                            dto.getId(),
                            dto.getModel(),
                            dto.getPrice(),
                            dto.getMileage().toString(),
                            engineMap.get(dto.getEngineId()).toEngineData(),
                            carBodyMap.get(dto.getCarBodyId()).toCarBodyData(),
                            wheelMap.get(dto.getWheelId()).toWheelData()))
                    .toList();
    }

    @Override
    public CarData save(CreateCarDto data) {

        var engine = enginesRepository.save(data.createEngineDto().toEngineDb());
        var wheel = wheelsRepository.save(data.createWheelDto().toWheelDb());
        var carBody = carBodiesRepository.save(data.createCarBodyDto().toCarBodyDb());
        var car = carsRepository.save(data.toCarDb(engine.getId(),carBody.getId(),wheel.getId()));

        return CarData.of(car.getId()
                ,car.getModel(),
                car.getPrice(),
                car.getMileage().toString(),
                engine.toEngineData(),
                carBody.toCarBodyData(),
                wheel.toWheelData());
    }


    public static <T extends Identifiable> Map<Integer, T> getCarElementsWithIds(List<T> data){
        return data
                .stream()
                .collect(Collectors.toMap(Identifiable::getId,
                        Function.identity()));
    }
}
