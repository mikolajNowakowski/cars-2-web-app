package com.app.service.car.utilities;

import com.app.service.car.statistics.Statistics;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class BigDecimalSummaryStatistics implements Collector<BigDecimal, List<BigDecimal>, Statistics<BigDecimal,BigDecimal>>{

    @Override
    public Supplier<List<BigDecimal>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<BigDecimal>, BigDecimal> accumulator() {
        return List::add;
    }

    @Override
    public BinaryOperator<List<BigDecimal>> combiner() {
        return (acc1, acc2) -> acc1;
    }

    @Override
    public Function<List<BigDecimal>, Statistics<BigDecimal,BigDecimal>> finisher() {
        return (acc) -> Statistics.of(getMinPrice(acc), getMaxPrice(acc), getAvgPrice(acc));
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.UNORDERED);
    }

    private BigDecimal getAvgPrice(List<BigDecimal> data) {
        return data
                .stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add).divide(new BigDecimal(data.size()), 2,
                        RoundingMode.CEILING);
    }

    private BigDecimal getMaxPrice(List<BigDecimal> data) {
        return data
                .stream()
                .max(BigDecimal::compareTo)
                .orElseThrow();
    }

    private BigDecimal getMinPrice(List<BigDecimal> data) {
        return data
                .stream()
                .min(BigDecimal::compareTo)
                .orElseThrow();
    }

}
