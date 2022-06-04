package Model.Repositories;

import Model.Entities.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Util {
    private Connection conn;

    public Util() {
        this.conn = Database.getInstance().getConnection();
    }

    public List<Service> findAllServices() throws SQLException {
        String query = "SELECT * FROM services;";
        Statement statement = conn.createStatement();

        List<Service> services = new ArrayList<>();
        ResultSet res = statement.executeQuery(query);
        while (res.next()) {
            Service service = new Service(res.getInt("id"), res.getString("name"), res.getInt("price"));
            services.add(service);
        }
        return services;
    }

    public List<Company> findAllCompanies() throws SQLException {
        String query = "SELECT * FROM companies;";
        Statement statement = conn.createStatement();

        List<Company> companies = new ArrayList<>();
        ResultSet res = statement.executeQuery(query);
        while (res.next()) {
            Company company = new Company(res.getInt("id"), res.getString("name"));
            companies.add(company);
        }
        return companies;
    }

    public List<CustomerType> findAllCustomerTypes() throws SQLException {
        String query = "SELECT * FROM customer_types;";
        Statement statement = conn.createStatement();

        List<CustomerType> types = new ArrayList<>();
        ResultSet res = statement.executeQuery(query);
        while (res.next()) {
            CustomerType type = new CustomerType(res.getInt("id"), res.getString("name"));
            types.add(type);
        }
        return types;
    }
}
