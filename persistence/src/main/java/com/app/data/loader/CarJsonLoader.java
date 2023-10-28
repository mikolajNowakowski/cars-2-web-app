package com.app.data.loader;

import com.app.data.loader.Loader;
import com.app.data.loader.json_loader.FromJsonToObjectLoader;
import com.app.data.model.car_data.CarData;
import com.app.data.model.filename.CarFilePath;
import com.app.data.model.filename.DataSource;
import com.google.gson.Gson;

import java.util.List;


public final class CarJsonLoader extends FromJsonToObjectLoader<List<CarData>> implements Loader<List<CarData>> {

    public CarJsonLoader(Gson gson) {
        super(gson);
    }

    @Override
    public List<CarData> load(DataSource dataSource){
        var carFilePath = (CarFilePath)dataSource;

        return loadObject(carFilePath.path());
    }
}
