package com.app.data.model.db;

import com.app.data.model.wheel_data.WheelData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public final class WheelDb implements Identifiable {
    private int id;
    private String type;
    private String model;
    private int size;


    public WheelData toWheelData(){
        return WheelData.of(id,model,String.valueOf(size),type);
    }

    public static WheelDb wheelDbWithoutId(String type,String model, int size){
    return WheelDb.builder()
            .model(model)
            .type(type)
            .size(size)
            .build();
    }

}
