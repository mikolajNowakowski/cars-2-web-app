package com.app.data.repository.cars_data.impl;


import com.app.data.repository.generic.AbstractCrudRepository;
import com.app.data.model.db.CarBodyDb;
import org.jdbi.v3.core.Jdbi;

public class CarBodyRepositoryImpl extends AbstractCrudRepository<CarBodyDb,Long> {

    public CarBodyRepositoryImpl(Jdbi jdbi) {
        super(jdbi);
    }

}
