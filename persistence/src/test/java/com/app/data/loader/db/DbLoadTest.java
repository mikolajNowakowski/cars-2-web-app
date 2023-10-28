package com.app.data.loader.db;

import com.app.config.AppTestBeansConfig;
import com.app.data.loader.CarDbLoader;
import com.app.data.loader.Loader;
import com.app.data.model.car_data.CarData;
import com.app.data.model.filename.CarDbConnection;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.app.data.CarsDataModels.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppTestBeansConfig.class)
@TestPropertySource("classpath:application_test.properties")
public class DbLoadTest {
    @Autowired
    private Loader<List<CarData>> carDbLoader;
    @Autowired
    private CarDbConnection carDbConnection;

    @Test
    @DisplayName("When load() method is called with correct argument. (db_connection file path)")
    void test1() {
        Assertions
                .assertThat(this.carDbLoader.load(carDbConnection))
                .isEqualTo(List.of(AUDI, BMW, HONDA));
    }
}
