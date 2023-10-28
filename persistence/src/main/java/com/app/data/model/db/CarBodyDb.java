package com.app.data.model.db;

import com.app.data.model.car_body.CarBodyData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public final class CarBodyDb implements Identifiable {
    private int id;
    private String color;
    private String type;
    private String components;


    public CarBodyData toCarBodyData(){
        return CarBodyData.of(id,color,type, Arrays.stream(components.split(",")).toList());
    }

    public static CarBodyDb carBodyDbWithoutId(String color, String type, String components){
        return CarBodyDb.builder()
                .color(color)
                .type(type)
                .components(components)
                .build();
    }
}
