package com.app.data.process;


import com.app.data.model.filename.DataSource;

public sealed interface DataProcess<T> permits CarDataProcess {
    T process(DataSource dataSource);
}
