package com.app.data.process;

import com.app.config.AppTestBeansConfig;
import com.app.data.model.filename.CarDbConnection;
import com.app.data.model.filename.CarFilePath;
import java.util.List;
import static com.app.data.CarsModels.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppTestBeansConfig.class)
@TestPropertySource("classpath:application_test.properties")
public class ProcessTest {
    @Value("${car_file_path.json}")
    private String carJsonPath;
    @Value("${car_file_path.txt}")
    private String carTxtPath;
    @Autowired
    private CarDbConnection carDbConnection;
    @Autowired
    private CarDataJsonProcessImpl carDataJsonProcess;
    @Autowired
    private CarDataTxtProcessImpl carDataTxtProcess;
    @Autowired
    private CarDataDbProcessImpl carDataDbProcess;



    @Test
    @DisplayName("When process method from CarDataJsonProcessImpl class is called and it produces expected output.")
    void test1(){
        Assertions.assertThat(carDataJsonProcess.process(new CarFilePath(carJsonPath)))
                .isEqualTo(List.of(AUDI,BMW,HONDA));
    }

    @Test
    @DisplayName("When process method from CarDataDbProcessImpl class is called and it produces expected output.")
    void test2(){
        Assertions.assertThat(carDataDbProcess.process(carDbConnection))
                .isEqualTo(List.of(AUDI,BMW,HONDA));
    }

    @Test
    @DisplayName("When process method from CarDataTxtProcessImpl class is called and it produces expected output.")
    void test3(){
        Assertions.assertThat(carDataTxtProcess.process(new CarFilePath(carTxtPath)))
                .isEqualTo(List.of(HONDA));
    }
}
