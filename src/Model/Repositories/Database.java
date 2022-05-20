package Model.Repositories;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database implements AutoCloseable {
    private static Database instance;
    private Connection conn;

    private static final String DB_URL = "";
    private static final String DB_USER = "system";
    private static final String DB_PASS = "oracle";

    private Database() {
        try {
            this.conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            this.conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("There was a problem connecting to Database!");
            e.printStackTrace();
        }
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() {
        return this.conn;
    }

    public void commit() {
        try {
            this.conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        this.conn.close();
    }
}
