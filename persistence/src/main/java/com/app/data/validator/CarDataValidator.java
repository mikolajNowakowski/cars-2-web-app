package com.app.data.validator;

import com.app.data.model.car_data.CarData;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.app.data.validator.Validator.*;
import static java.util.Arrays.stream;


@Component
@RequiredArgsConstructor
public class CarDataValidator implements Validator<List<CarData>> {

    @Value("${validator.modelRegex}")
    private String componentsRegex;
    @Value("${validator.modelRegex}")
    private String modelRegex;

    private static final Logger logger = LogManager.getLogger("DebugLogger");

    @Override
    public List<CarData> validate(List<CarData> data) {
        return data
                .stream()
                .filter(this::validateCarData)
                .toList();

    }

    private boolean validateCarData(CarData data) {
        if(!isLongNumberGreaterThan(data.getMileage(), 0)){
            logger.debug("Impossible to get car with id: "+data.getId()+ ", due to wrong mileage form.");
            return false;
        }

        if(!isBigDecimalPositive(data.getPrice())){
            logger.debug("Impossible to get car with id: "+data.getId()+ ", due to negative value of price.");
            return false;
        }

        if(!validateExpressionWithRegex(data.getModel(), modelRegex)){
            logger.debug("Impossible to get car with id: "+data.getId()+ ", due to wrong model form.");
            return false;
        }
        if(!data.getCarBody().getComponents().stream().allMatch(element -> element.matches(componentsRegex))){
            logger.debug("Impossible to get car with id: "+data.getId()+ ", due to wrong at least one component form.");
            return false;
        }

        if(!isDoubleNumberGreaterThan(data.getEngine().getPower(), 0.0)){
            logger.debug("Impossible to get car with id: "+data.getId()+ ", due to negative value of engine power.");
            return false;
        }

        if(!isLongNumberGreaterThan(data.getWheel().getSize(), 0)){
            logger.debug("Impossible to get car with id: "+data.getId()+ ", due to negative value of wheel size.");
            return false;
        }

        return true;


//        return isLongNumberGreaterThan(data.getMileage(), 0) &&
//                isBigDecimalPositive(data.getPrice()) &&
//                validateExpressionWithRegex(data.getModel(), modelRegex) &&
//                data.getCarBody().getComponents().stream().allMatch(element -> element.matches(componentsRegex)) &&
//                isDoubleNumberGreaterThan(data.getEngine().getPower(), 0.0) &&
//                isLongNumberGreaterThan(data.getWheel().getSize(), 0);
    }
}
