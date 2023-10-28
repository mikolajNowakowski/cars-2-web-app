package com.app.data.model.wheel_data;

import com.app.model.wheel.Wheel;
import com.app.model.wheel.type.WheelType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class WheelData {

    private long id;

    private String model;

    private String size;

    private String type;

    public static WheelData of(long id,String model, String size, String type) {
        return new WheelData(id,model, size, type);
    }
    public Wheel toWheel() {
        return Wheel.of(id,model, Integer.parseInt(size), WheelType.valueOf(type));
    }
}
