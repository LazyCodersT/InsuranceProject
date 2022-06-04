package Model.Repositories;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database implements AutoCloseable {
    private static Database instance;
    private Connection conn;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/sys?characterEncoding=utf8";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "root";

    private Database() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            this.conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("There was a problem connecting to Database!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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

    public void rollback() {
        try {
            this.conn.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        this.conn.close();
    }
}
