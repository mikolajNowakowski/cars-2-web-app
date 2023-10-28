package com.app.data.loader;

import com.app.data.model.filename.DataSource;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;

public sealed interface Loader<T> permits CarDbLoader, CarJsonLoader, CarTxtLoader {

    T load(DataSource dataSource);

    public static <T> List<T> read(String filename, Function<String, T> converter) {
        if (converter == null) {
            throw new IllegalArgumentException("Converter is null");
        }
        try (var lines = Files.lines(Paths.get(filename))) {
            return lines
                    .map(converter::apply)
                    .toList();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }


}
