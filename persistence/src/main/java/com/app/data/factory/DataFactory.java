package com.app.data.factory;

import com.app.data.converter.Converter;
import com.app.data.loader.Loader;
import com.app.data.validator.Validator;

public interface DataFactory<S, T> {
    Loader<S> createLoader();

    Validator<S> createValidator();

    Converter<S, T> createConverter();
}
