package com.app.model.engine;

import com.app.model.car_body.CarBody;
import com.app.model.engine.type.EngineType;

import java.util.function.Function;

public interface EngineConverter {
    Function<Engine,Double> toEnginePower = engine -> engine.power;
    Function<Engine, EngineType> toEngineType = engine -> engine.type;
    Function<Engine,Long> toId = engine -> engine.id;
}
