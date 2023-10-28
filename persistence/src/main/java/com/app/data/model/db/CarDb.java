package com.app.data.model.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public final class CarDb implements Identifiable {

    private int id;
    private String model;
    private String price;
    private Long mileage;
    private int engineId;
    private int carBodyId;
    private int wheelId;


    public static CarDb carDbWithoutId(String model, String price, Long mileage, int engineId, int carBodyId, int wheelId){
        return CarDb.builder()
                .model(model)
                .price(price)
                .mileage(mileage)
                .engineId(engineId)
                .carBodyId(carBodyId)
                .wheelId(wheelId)
                .build();
    }


}

