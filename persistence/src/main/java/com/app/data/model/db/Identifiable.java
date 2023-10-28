package com.app.data.model.db;

public sealed interface Identifiable permits CarDb, CarBodyDb, WheelDb, EngineDb {
    int getId();
}
