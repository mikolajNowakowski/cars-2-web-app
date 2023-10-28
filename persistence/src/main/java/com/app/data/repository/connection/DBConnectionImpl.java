package com.app.data.repository.connection;

import org.jdbi.v3.core.Jdbi;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class DBConnectionImpl implements DBConnection {
    @Override
    public Jdbi createConnection(String filePath) {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(filePath)) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return  Jdbi.create(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
    }
}
