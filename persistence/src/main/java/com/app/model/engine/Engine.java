package com.app.model.engine;

import com.app.model.engine.type.EngineType;
import lombok.EqualsAndHashCode;



@EqualsAndHashCode
public class Engine {
    final Long id;
    final EngineType type;
    final double power;

    private Engine(Long id, EngineType type, double power) {
        this.id = id;
        this.type = type;
        this.power = power;
    }


    @Override
    public String toString() {
        return "ENGINE => [TYPE = %s, POWER = %f]".formatted(type, power);
    }

    public static Engine of(Long id,EngineType engineType, double power) {
        return new Engine(id, engineType, power);
    }

}
