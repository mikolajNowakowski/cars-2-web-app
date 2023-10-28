package com.app.model.wheel;

import com.app.model.wheel.type.WheelType;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode
public class Wheel {

    final Long id;
    private final String model;
    final int size;
    final WheelType type;

    private Wheel(Long id, String model, int size, WheelType type) {
        this.id = id;
        this.model = model;
        this.size = size;
        this.type = type;
    }

    @Override
    public String toString() {
        return "WHEEL => [MODEL = %s, SIZE = %d, TYPE = %s]".formatted(model, size, type);
    }

    public static Wheel of(Long id,String model, int size, WheelType type) {
        return new Wheel(id, model, size, type);
    }


}
