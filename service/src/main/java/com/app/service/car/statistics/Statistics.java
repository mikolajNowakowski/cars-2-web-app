package com.app.service.car.statistics;

import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.DoubleSummaryStatistics;
import java.util.LongSummaryStatistics;

@EqualsAndHashCode
public class Statistics<S,T> {

    public final S min;
    public final S max;
    public final T avg;

    private Statistics(S min, S max, T avg ) {
        this.min = min;
        this.max = max;
        this.avg = avg;
    }

    public static <S, T> Statistics<S, T> create(S min, S max, T avg) {
        return new Statistics<>(min, max, avg);
    }

    @Override
    public String toString() {
        return "MIN -> %s\nMAX -> %s\nAVERAGE -> %s\n".formatted(min,max,avg);
    }

    public static Statistics<Double,Double> of(DoubleSummaryStatistics data) {
        return new Statistics<>(
                data.getMin(),
                data.getMax(),
                data.getAverage()
        );
    }

    public static Statistics<Long,Double> of(LongSummaryStatistics data) {
        return new Statistics<>(
                data.getMin(),
                data.getMax(),
                data.getAverage()
        );
    }
    public static Statistics<BigDecimal,BigDecimal> of(BigDecimal min, BigDecimal max, BigDecimal avg){
        return new Statistics<>(min, max, avg);
    }
}
