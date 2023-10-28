package com.app.service.car;

import com.app.config.AppTestBeansConfig;
import com.app.service.car.CarService;
import com.app.service.car.statistics.Statistics;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.LongSummaryStatistics;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppTestBeansConfig.class)
@TestPropertySource("classpath:application_test.properties")
public class MileageStatisticTest {
    @Autowired
    @Qualifier("carService")
    private CarService carService;

    @Test
    @DisplayName("When mileageStatistic() method is called and it should generate specified output")
    void test1() {
        Assertions
                .assertThat(carService.mileageStatistic())
                .isEqualTo(Statistics.of(new LongSummaryStatistics(3L,12000L,270000L,296000L)));
    }
}
