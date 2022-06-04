package Model.Repositories;

import Model.Entities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InsuranceRepo implements IInsuranceRepo{

    private Connection conn;

    public InsuranceRepo() {
        this.conn = Database.getInstance().getConnection();
    }
    @Override
    public Insurance findOne(Query query) throws SQLException {
        String q = "SELECT * FROM insurances " +
                "JOIN services on insurances.service_id = services.id JOIN companies on insurances.company_id = companies.id " +
                "JOIN customer_types on customer_types.id = insurances.customer_type_id" + query.parseQuery() + " limit 1;";
        Statement statement = conn.createStatement();
        ResultSet res = statement.executeQuery(q);
        Insurance insurance = new Insurance();
        if (res.next()) {
            insurance.setId(res.getInt("id")).setDocNumber(res.getInt("document_number"));
            insurance.setPaymentCode(res.getString("payment_code"));
            insurance.setJobVerificationCode(res.getString("job_verification_number")).setDate(res.getDate("date"));
            insurance.setCustomerType(new CustomerType(res.getInt("customer_types.id"), res.getString("customer_types.name")));
            insurance.setService(new Service(res.getInt("services.id"), res.getString("services.name"), res.getInt("services.price")));
            insurance.setCompany(new Company(res.getInt("companies.id"), res.getString("companies.name")));
            insurance.setUserId(res.getInt("user_id"));
        }
        statement.close();
        return insurance;
    }

    @Override
    public List<Insurance> findMany(Query query) throws SQLException {
        String q = "SELECT * FROM insurances " +
                "JOIN services on insurances.service_id = services.id JOIN companies on insurances.company_id = companies.id " +
                "JOIN customer_types on customer_types.id = insurances.customer_type_id" + query.parseQuery() + ";";
        Statement statement = conn.createStatement();
        ResultSet res = statement.executeQuery(q);
        List<Insurance> insurances = new ArrayList<>();
        while (res.next()) {
            Insurance insurance = new Insurance();
            insurance.setId(res.getInt("id")).setDocNumber(res.getInt("document_number"));
            insurance.setPaymentCode(res.getString("payment_code"));
            insurance.setJobVerificationCode(res.getString("job_verification_number")).setDate(res.getDate("date"));
            insurance.setCustomerType(new CustomerType(res.getInt("customer_types.id"), res.getString("customer_types.name")));
            insurance.setService(new Service(res.getInt("services.id"), res.getString("services.name"), res.getInt("services.price")));
            insurance.setCompany(new Company(res.getInt("companies.id"), res.getString("companies.name")));
            insurance.setUserId(res.getInt("user_id"));
            insurances.add(insurance);
        }
        statement.close();
        return insurances;
    }

    @Override
    public void insertOne(Insurance insurance) throws SQLException {
        String q = "INSERT INTO insurances (document_number, payment_code, job_verification_number, date, service_id, customer_type_id, company_id, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement statement = conn.prepareStatement(q);
        statement.setInt(1, insurance.getDocNumber());
        statement.setString(2, insurance.getPaymentCode());
        statement.setString(3, insurance.getJobVerificationCode());
        statement.setDate(4, insurance.getDate());
        statement.setInt(5, insurance.getService().getId());
        statement.setInt(6, insurance.getCustomerType().getId());
        statement.setInt(7, insurance.getCompany().getId());
        statement.setInt(8, insurance.getUserId());
        statement.execute();
        statement.close();
    }

    @Override
    public void updateOne(Query query, Insurance insurance) throws SQLException {
        String q = "UPDATE insurances SET document_number=?, payment_code=?, job_verification_number=?," +
                "date=?, service_id=?, company_id=?, customer_type_id=?, user_id=?" + query.parseQuery() + ";";
        PreparedStatement statement = conn.prepareStatement(q);
        statement.setInt(1, insurance.getDocNumber());
        statement.setString(2, insurance.getPaymentCode());
        statement.setString(3, insurance.getJobVerificationCode());
        statement.setDate(4, insurance.getDate());
        statement.setInt(5, insurance.getService().getId());
        statement.setInt(6, insurance.getCompany().getId());
        statement.setInt(7, insurance.getCustomerType().getId());
        statement.setInt(8, insurance.getUserId());
        statement.execute();
        statement.close();
    }

    @Override
    public void deleteOne(Query query) throws SQLException {
        String q = "DELETE FROM insurances" + query.parseQuery() + " limit 1;";
        Statement statement = conn.createStatement();
        statement.execute(q);
        statement.close();
    }

    public void commit() throws SQLException {
        this.conn.commit();
    }

    public void rollback() throws SQLException {
        this.conn.rollback();
    }
}
