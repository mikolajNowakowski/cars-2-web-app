package com.app.data.factory;

import com.app.data.converter.Converter;
import com.app.data.converter.ToCarConverter;
import com.app.data.loader.CarJsonLoader;
import com.app.data.loader.Loader;
import com.app.data.model.car_data.CarData;
import com.app.data.validator.CarDataValidator;
import com.app.data.validator.Validator;
import com.app.model.car.Car;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FromJsonToCarWithRegexValidation implements DataFactory<List<CarData>, List<Car>>{

    private final CarDataValidator carDataValidator;

    @Override
    public Loader<List<CarData>> createLoader() {
        return new CarJsonLoader(new GsonBuilder().setPrettyPrinting().create());
    }

    @Override
    public Validator<List<CarData>> createValidator() {
        return carDataValidator;
    }

    @Override
    public Converter<List<CarData>,List<Car>> createConverter() {
        return new ToCarConverter();
    }
}
