package com.app.data.repository.cars_data.impl;

import com.app.data.repository.generic.AbstractCrudRepository;
import com.app.data.model.db.EngineDb;
import org.jdbi.v3.core.Jdbi;


public class EngineRepositoryImpl extends AbstractCrudRepository<EngineDb,Long> {


    public EngineRepositoryImpl(Jdbi jdbi) {
        super(jdbi);
    }
}
