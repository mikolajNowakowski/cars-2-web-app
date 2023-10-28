package com.app.model.car_body;

import com.app.model.car_body.color.CarBodyColor;
import com.app.model.car_body.type.CarBodyType;
import lombok.EqualsAndHashCode;

import java.util.List;


@EqualsAndHashCode
public class CarBody {
    final Long id;
    final CarBodyColor color;
    final CarBodyType  type;
    final List<String> components;

    private CarBody(Long id,CarBodyColor color, CarBodyType type, List<String> components) {
        this.id = id;
        this.color = color;
        this.type = type;
        this.components = components;
    }

    public boolean hasComponent(String component){
        if(component == null){
            throw new IllegalArgumentException("Inputted component is null");
        }

        return this.components.contains(component);
    }

    @Override
    public String toString() {
        return "CARBODY => [COLOR = %s, TYPE = %s, COMPONENTS = %s]"
                .formatted(color,type, String.join(" ", components));
    }

    public static CarBody of(Long id, CarBodyColor color, CarBodyType type, List<String> components){
        return new CarBody(id,color,type,components);
    }




}
