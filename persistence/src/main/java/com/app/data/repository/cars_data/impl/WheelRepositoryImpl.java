package com.app.data.repository.cars_data.impl;

import com.app.data.repository.generic.AbstractCrudRepository;
import com.app.data.model.db.WheelDb;
import org.jdbi.v3.core.Jdbi;

public class WheelRepositoryImpl extends AbstractCrudRepository<WheelDb,Long> {


    public WheelRepositoryImpl(Jdbi jdbi) {
        super(jdbi);
    }
}
