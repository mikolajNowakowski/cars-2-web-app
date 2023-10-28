package com.app.data.model.db;

import com.app.data.model.engine_data.EngineData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public final class EngineDb implements Identifiable {
    private int id;
    private String type;
    private double power;


    public EngineData toEngineData(){
        return EngineData.of((long)id,type,String.valueOf(power));
    }

    public static EngineDb engineDbWithoutId(String engineType, Double enginePower){
        return EngineDb.builder()
                .type(engineType)
                .power(enginePower)
                .build();

    }

}
