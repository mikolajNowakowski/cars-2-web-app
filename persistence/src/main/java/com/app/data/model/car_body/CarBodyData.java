package com.app.data.model.car_body;

import com.app.model.car_body.CarBody;
import com.app.model.car_body.color.CarBodyColor;
import com.app.model.car_body.type.CarBodyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarBodyData {
    long id;

    String color;

    String type;

    List<String> components;

    public static CarBodyData of(long id, String color, String type, List<String> components) {
        return new CarBodyData(id,color, type, components);
    }

    public CarBody toCarBody() {
        return CarBody.of(id,CarBodyColor.valueOf(color), CarBodyType.valueOf(type), components);
    }
}
