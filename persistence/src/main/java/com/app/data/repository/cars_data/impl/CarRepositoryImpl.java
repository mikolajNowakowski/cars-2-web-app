package com.app.data.repository.cars_data.impl;

import com.app.data.repository.generic.AbstractCrudRepository;
import com.app.data.model.db.CarDb;
import org.jdbi.v3.core.Jdbi;

public class CarRepositoryImpl extends AbstractCrudRepository<CarDb,Long> {


    public CarRepositoryImpl(Jdbi jdbi) {
        super(jdbi);
    }
}
