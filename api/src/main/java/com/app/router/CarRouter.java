package com.app.router;

import com.app.dto.ResponseDto;
import com.app.model.car.CarComparator;
import com.app.model.car_body.type.CarBodyType;
import com.app.model.dto.car.CreateCarDto;
import com.app.model.engine.type.EngineType;
import com.app.service.car.CarService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import spark.ResponseTransformer;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static spark.Spark.*;

@Component
@RequiredArgsConstructor
public class CarRouter {

    private final CarService carService;
    private final ResponseTransformer responseTransformer;
    private final Gson gson;

    public void routes() {
        path("/admin", () -> {
            // POST http://localhost:8080/admin
            post(
                    "",
                    (request, response) -> {
                        response.header("Content-Type", "application/json;charset=utf-8");
                        var createCarDto = gson.fromJson(request.body(), CreateCarDto.class);
                        return new ResponseDto<>(carService.save(createCarDto));
                    },
                    responseTransformer
            );
            // GET http://localhost:8080/admin/price-statistic
            // Route to get price statistics
            get(
                    "/price-statistics",
                    (request, response) -> {
                        response.header("Content-Type", "application/json;charset=utf-8");
                        return new ResponseDto<>(carService.priceStatistic());
                    },
                    responseTransformer
            );
            // GET http://localhost:8080/admin/mileage-statistic
            // Route to get mileage statistics
            get(
                    "/mileage-statistics",
                    (request, response) -> {
                        response.header("Content-Type", "application/json;charset=utf-8");
                        return new ResponseDto<>(carService.mileageStatistic());
                    },
                    responseTransformer
            );
            // GET http://localhost:8080/admin/engine-power-statistic
            // Route to get engine power statistics
            get(
                    "/engine-power-statistics",
                    (request, response) -> {
                        response.header("Content-Type", "application/json;charset=utf-8");
                        return new ResponseDto<>(carService.enginePowerStatistic());
                    },
                    responseTransformer
            );
        });

        path("/user", () -> {
            path("/cars", () -> {
                path("/sorted", () -> {

                    // GET http://localhost:8080/user/cars/sorted/components
                    // Route to get cars sorted by number of components
                    get(
                            "/components",
                            (request, response) -> {
                                response.header("Content-Type", "application/json;charset=utf-8");
                                return new ResponseDto<>(carService.sortBy(CarComparator.byNumberOfComponents));
                            },
                            responseTransformer
                    );

                    // GET http://localhost:8080/user/cars/sorted/engine-model/:engineType
                    // Route to get sorted cars with specified engine type
                    get(
                            "/engine-model/:engineType",
                            (request, response) -> {
                                response.header("Content-Type", "application/json;charset=utf-8");
                                EngineType engineType = EngineType.valueOf(request.params("engineType"));
                                return new ResponseDto<>(carService.sortedCarsWithSpecifiedTypeOfEngine(CarComparator.byModel, engineType));
                            },
                            responseTransformer
                    );

                    // GET http://localhost:8080/user/cars/sorted/power
                    // Route to get cars sorted by engine power
                    get(
                            "/power",
                            (request, response) -> {
                                response.header("Content-Type", "application/json;charset=utf-8");
                                return new ResponseDto<>(carService.sortBy(CarComparator.byEnginePower));
                            },
                            responseTransformer
                    );
                    // GET http://localhost:8080/user/cars/sorted/engine-power/:engineType
                    // Route to get sorted cars with specified engine type
                    get(
                            "/engine-power/:engineType",
                            (request, response) -> {
                                response.header("Content-Type", "application/json;charset=utf-8");
                                EngineType engineType = EngineType.valueOf(request.params("engineType"));
                                return new ResponseDto<>(carService.sortedCarsWithSpecifiedTypeOfEngine(CarComparator.byEnginePower, engineType));
                            },
                            responseTransformer
                    );

                    // GET http://localhost:8080/user/cars/sorted/wheel-size
                    // Route to get cars sorted by wheel size
                    get(
                            "/wheel-size",
                            (request, response) -> {
                                response.header("Content-Type", "application/json;charset=utf-8");
                                return new ResponseDto<>(carService.sortBy(CarComparator.byWheelSize));
                            },
                            responseTransformer
                    );

                    // GET http://localhost:8080/user/cars/sorted/model
                    // Route to get cars sorted by model
                    get(
                            "/model",
                            (request, response) -> {
                                response.header("Content-Type", "application/json;charset=utf-8");
                                return new ResponseDto<>(carService.sortBy(CarComparator.byModel));
                            },
                            responseTransformer
                    );

                    // GET http://localhost:8080/user/cars/sorted/model/:engineType
                    // Route to get sorted cars with specified engine type
                    get(
                            "/model/:engineType",
                            (request, response) -> {
                                response.header("Content-Type", "application/json;charset=utf-8");
                                EngineType engineType = EngineType.valueOf(request.params("engineType"));
                                return new ResponseDto<>(carService.sortedCarsWithSpecifiedTypeOfEngine(CarComparator.byModel, engineType));
                            },
                            responseTransformer
                    );

                    // GET http://localhost:8080/user/cars/sorted/model/components/:components
                    // Route to get cars with specified components
                    get(
                            "/model/components/:components",
                            (request, response) -> {
                                response.header("Content-Type", "application/json;charset=utf-8");
                                List<String> components = Arrays.asList(request.queryParamsValues("components"));
                                return new ResponseDto<>(carService.carsWithSpecifiedComponents(CarComparator.byModel, components));
                            },
                            responseTransformer
                    );
                });

                // GET http://localhost:8080/user/cars/body/:bodyType/price/:min/:max
                // Route to get cars with specified body type and price range
                get(
                        "/body/:bodyType/price/:min/:max",
                        (request, response) -> {
                            response.header("Content-Type", "application/json;charset=utf-8");
                            CarBodyType bodyType = CarBodyType.valueOf(request.params("bodyType"));
                            BigDecimal priceMin = new BigDecimal(request.params("min"));
                            BigDecimal priceMax = new BigDecimal(request.params("max"));
                            return new ResponseDto<>(carService.carsWithSpecifiedBodyAndPriceBetween(bodyType, priceMin, priceMax));
                        },
                        responseTransformer
                );

                //TODO response serialization/deserialization

                // GET http://localhost:8080/user/cars/mileages
                // Route to get cars with mileage
                get(
                        "/mileages",
                        (request, response) -> {
                            response.header("Content-Type", "application/json;charset=utf-8");
                            return new ResponseDto<>(carService.carsWithMileage());
                        },
                        responseTransformer
                );

                // GET http://localhost:8080/user/cars/cars-wheels-types
                // Route to get cars grouped by wheel type
                get(
                        "/cars-wheels-types",
                        (request, response) -> {
                            response.header("Content-Type", "application/json;charset=utf-8");
                            return new ResponseDto<>(carService.wheelTypeWithCars());
                        },
                        responseTransformer
                );
            });
        });
    }
}


