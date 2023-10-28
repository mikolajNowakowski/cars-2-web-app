package com.app.service.car.provider.impl;

import com.app.data.model.filename.CarDbConnection;
import com.app.data.model.filename.CarFilePath;
import com.app.data.process.CarDataDbProcessImpl;
import com.app.data.process.CarDataJsonProcessImpl;
import com.app.data.process.CarDataProcess;
import com.app.data.process.CarDataTxtProcessImpl;
import com.app.data.process.type.ProcessType;
import com.app.model.car.Car;
import com.app.service.car.provider.CarProvider;
import com.app.service.car.provider.Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CarProviderImpl implements CarProvider {

    @Value("${process.type}")
    private ProcessType processType;

    @Value("${car_file_path.json}")
    private String jsonFilePath;

    @Value("${car_file_path.txt}")
    private String txtFilePath;

    private final CarDataProcess carDataDbProcess;
    private final CarDataProcess carDataTxtProcess;
    private final CarDataProcess carDataJsonProcess;
    private final CarDbConnection carDbConnection;


    @Override
    public List<Car> provide() {
        return switch (processType) {
            case FROM_TXT_FILE_TO_CAR_COLLECTION -> carDataTxtProcess.process(new CarFilePath(txtFilePath));
            case FROM_JSON_FILE_TO_CAR_COLLECTION -> carDataJsonProcess.process(new CarFilePath(jsonFilePath));
            case FROM_DB_TO_CAR_COLLECTION -> carDataDbProcess.process(carDbConnection);

        };
    }
}
