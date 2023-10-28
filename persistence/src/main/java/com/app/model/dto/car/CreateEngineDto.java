package com.app.model.dto.car;

import com.app.data.model.db.EngineDb;
import com.app.model.engine.Engine;
import com.app.model.engine.type.EngineType;

import static com.app.data.model.db.EngineDb.*;
import static com.app.model.engine.type.EngineType.*;

public record CreateEngineDto(
        String engineType,
        String enginePower) {

    public Engine toEngine(){
        return Engine.of(null, valueOf(engineType),Double.parseDouble(enginePower));
    }

    public EngineDb toEngineDb(){
        return engineDbWithoutId(engineType,Double.parseDouble(enginePower));
    }

}
