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
        String q = "SELECT * FROM Insurance" +
                "JOIN Service on Insurance.service_id = Service.id JOIN Company on Insurance.company_id = Company.id " +
                "JOIN Insurance.customertype_id = CustomerType.id" + query.parseQuery() + " limit 1;";
        Statement statement = conn.createStatement();
        ResultSet res = statement.executeQuery(q);
        Insurance insurance = new Insurance();
        if (res.next()) {
            insurance.setId(res.getInt("id")).setDocNumber(res.getInt("docNumber"));
            insurance.setPaymentCode(res.getString("paymentCode"));
            insurance.setJobVerificationCode(res.getString("jobVerificationCode")).setDate(res.getDate("date"));
            insurance.setCustomerType(new CustomerType(res.getInt("CustomerType.id"), res.getString("CustomerType.name")));
            insurance.setService(new Service(res.getInt("Service.id"), res.getString("Service.name"), res.getInt("Service.price")));
            insurance.setCompany(new Company(res.getInt("Company.id"), res.getString("Company.name")));
            insurance.setUserId(res.getInt("user_id"));
        }
        statement.close();
        return insurance;
    }

    @Override
    public List<Insurance> findMany(Query query) throws SQLException {
        String q = "SELECT * FROM Insurance" +
                "JOIN Service on Insurance.service_id = Service.id JOIN Company on Insurance.company_id = Company.id " +
                "JOIN Insurance.customertype_id = CustomerType.id" + query.parseQuery() + ";";
        Statement statement = conn.createStatement();
        ResultSet res = statement.executeQuery(q);
        List<Insurance> insurances = new ArrayList<>();
        while (res.next()) {
            Insurance insurance = new Insurance();
            insurance.setId(res.getInt("id")).setDocNumber(res.getInt("docNumber"));
            insurance.setPaymentCode(res.getString("paymentCode"));
            insurance.setJobVerificationCode(res.getString("jobVerificationCode")).setDate(res.getDate("date"));
            insurance.setCustomerType(new CustomerType(res.getInt("CustomerType.id"), res.getString("CustomerType.name")));
            insurance.setService(new Service(res.getInt("Service.id"), res.getString("Service.name"), res.getInt("Service.price")));
            insurance.setCompany(new Company(res.getInt("Company.id"), res.getString("Company.name")));
            insurance.setUserId(res.getInt("user_id"));
            insurances.add(insurance);
        }
        statement.close();
        return insurances;
    }

    @Override
    public void insertOne(Insurance insurance) throws SQLException {
        String q = "INSERT INTO Insurance VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement statement = conn.prepareStatement(q);
        statement.setInt(0, insurance.getDocNumber());
        statement.setString(1, insurance.getPaymentCode());
        statement.setString(2, insurance.getJobVerificationCode());
        statement.setDate(3, insurance.getDate());
        statement.setInt(4, insurance.getService().getId());
        statement.setInt(5, insurance.getCustomerType().getId());
        statement.setInt(6, insurance.getCompany().getId());
        statement.setInt(7, insurance.getUserId());
        statement.execute();
        statement.close();
    }

    @Override
    public void updateOne(Query query, Insurance insurance) throws SQLException {
        String q = "UPDATE Insurance SET document_number=?, payment_code=?, job_verification_number=?," +
                "date=?, service_id=?, company_id, customertype_id=?, user_id=?" + query.parseQuery() + ";";
        PreparedStatement statement = conn.prepareStatement(q);
        statement.setInt(0, insurance.getDocNumber());
        statement.setString(1, insurance.getPaymentCode());
        statement.setString(2, insurance.getJobVerificationCode());
        statement.setDate(3, insurance.getDate());
        statement.setInt(4, insurance.getService().getId());
        statement.setInt(5, insurance.getCompany().getId());
        statement.setInt(6, insurance.getCustomerType().getId());
        statement.setInt(7, insurance.getUserId());
        statement.execute();
        statement.close();
    }

    @Override
    public void deleteOne(Query query) throws SQLException {
        String q = "DELETE FROM Insurance" + query.parseQuery() + " limit 1;";
        Statement statement = conn.createStatement();
        statement.execute(q);
        statement.close();
    }
}
