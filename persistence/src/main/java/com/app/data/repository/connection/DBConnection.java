package com.app.data.repository.connection;

import org.jdbi.v3.core.Jdbi;

public sealed interface DBConnection permits DBConnectionImpl {

     Jdbi createConnection(String filePath);

}
