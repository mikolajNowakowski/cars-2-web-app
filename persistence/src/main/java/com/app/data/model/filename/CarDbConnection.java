package com.app.data.model.filename;

import com.app.data.repository.connection.DBConnectionImpl;
import lombok.Data;
import org.jdbi.v3.core.Jdbi;

@Data
public class CarDbConnection implements DataSource {

    private Jdbi jdbi;

    public CarDbConnection(Jdbi jdbi){
        this.jdbi = jdbi;
    }

    public static CarDbConnection of( String jdbiConfigPath){
        return new CarDbConnection(new DBConnectionImpl().createConnection(jdbiConfigPath));
    }

}
