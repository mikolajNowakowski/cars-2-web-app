INSERT INTO engines (type,power)
VALUES ('DIESEL',210.0),
       ('GASOLINE',170.0),
       ('LPG',141.0);

INSERT INTO wheels (type,model,size)
VALUES ('SUMMER','PIRELLI',18),
       ('WINTER','MICHELIN',19),
       ('WINTER','PIRELLI',17);

INSERT INTO car_bodies ( color, type, components)
VALUES ('BLACK','HATCHBACK','ABS,AIR CONDITIONING'),
       ('BLUE','SEDAN','ABS,AIR CONDITIONING,CAMERA'),
       ('WHITE','COMBI','ABS,AIR CONDITIONING,CAMERA,CAR AUDIO');

INSERT INTO cars ( model,price, mileage, engine_id, car_body_id, wheel_id)
VALUES ('AUDI',120.0,12000,1,1,1),
       ('BMW',170.0,14000,2,2,2),
       ('HONDA',29,270000,3,3,3);



