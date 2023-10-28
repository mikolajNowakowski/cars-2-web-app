package com.app.data.converter;

public sealed interface Converter<S, T> permits ToCarConverter {

    T convert(S data);

}
