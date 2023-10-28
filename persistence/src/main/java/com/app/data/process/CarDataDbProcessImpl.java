package com.app.data.process;

import com.app.data.converter.Converter;
import com.app.data.factory.DataFactory;
import com.app.data.loader.Loader;
import com.app.data.model.car_data.CarData;
import com.app.data.model.filename.DataSource;
import com.app.data.validator.Validator;
import com.app.model.car.Car;

import java.util.List;

public final class CarDataDbProcessImpl implements CarDataProcess{

    private final Loader<List<CarData>> loader;
    private final Validator<List<CarData>> validator;
    private final Converter<List<CarData>, List<Car>> converter;


    public CarDataDbProcessImpl(DataFactory<List<CarData>, List<Car>> dataFactory) {
        this.loader = dataFactory.createLoader();
        this.validator = dataFactory.createValidator();
        this.converter = dataFactory.createConverter();
    }

    @Override
    public List<Car> process(DataSource dataSource) {
        var loadedData = loader.load(dataSource);
        var validatedData = validator.validate(loadedData);
        return converter.convert(validatedData);
    }
}
