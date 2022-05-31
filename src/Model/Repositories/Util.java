package Model.Repositories;

import Model.Entities.Company;
import Model.Entities.Service;
import Model.Entities.User;
import Model.Entities.UserType;

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
}
