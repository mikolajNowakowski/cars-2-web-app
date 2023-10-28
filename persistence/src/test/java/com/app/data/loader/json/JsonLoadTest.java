package com.app.data.loader.json;

import com.app.config.AppTestBeansConfig;
import com.app.data.loader.CarJsonLoader;
import com.app.data.model.filename.CarFilePath;
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
public class JsonLoadTest {

    @Autowired
    private CarJsonLoader carJsonLoader;
    @Value("${car_file_path.json}")
    private String carJsonPath;

    @Test
    @DisplayName("When load() method is called with correct argument. (json. file path)")
    void test1() {

        Assertions
                .assertThat(carJsonLoader.load(new CarFilePath(carJsonPath)))
                .isEqualTo(List.of(AUDI,BMW,HONDA));
    }
}
