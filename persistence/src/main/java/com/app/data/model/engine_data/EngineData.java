package com.app.data.model.engine_data;

import com.app.model.engine.Engine;
import com.app.model.engine.type.EngineType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EngineData {
    long id;
    String type;
    String power;


    public static EngineData of(long id,String type, String power) {
        return new EngineData(id,type, power);
    }

    public Engine toEngine() {
        return Engine.of(id,EngineType.valueOf(type), Double.parseDouble(power));
    }

}
