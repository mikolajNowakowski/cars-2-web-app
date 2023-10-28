package com.app.data.loader;

import com.app.data.loader.Loader;
import com.app.data.repository.cars_data.transaction.impl.CarDataDbRepositoryImpl;
import com.app.data.model.car_data.CarData;
import com.app.data.model.filename.CarDbConnection;
import com.app.data.model.filename.DataSource;
import org.springframework.stereotype.Component;

import java.util.List;


public final class CarDbLoader implements Loader<List<CarData>> {
    @Override
    public List<CarData> load(DataSource dataSource) {
        var dbConnection = (CarDbConnection) dataSource;
        return new CarDataDbRepositoryImpl(dbConnection.getJdbi()).findAllCarData();
    }
}
