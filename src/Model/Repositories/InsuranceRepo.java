package Model.Repositories;

import Model.Entities.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class InsuranceRepo implements IInsuranceRepo{

    private Connection conn;

    public InsuranceRepo() {
        this.conn = Database.getInstance().getConnection();
    }
    @Override
    public InsuranceUser findOne(Query query) throws SQLException {
        String q = "SELECT * FROM Users JOIN Privileges ON Users.privilege_id = Privileges.id" + query.parseQuery() + " limit 1;";
        Statement statement = conn.createStatement();
        ResultSet res = statement.executeQuery(q);
        InsuranceUser inusr = new InsuranceUser();
        if (res.next()) {
            inusr.setUser(new UserRepo().findOne(new Query().setField("id").setOp(Op.EQUAL).setValue(res.getInt("userId"))));
            Insurance insurance = new Insurance();
            insurance.setId(res.getInt("id")).setDocNumber(res.getInt("docNumber"));
            insurance.setPaymentCode(res.getString("paymentCode"));
            insurance.setJobVerificationCode(res.getString("jobVerificationCode")).setDate(res.getDate("date"));
            insurance.setCustomerType(res.getString("CustomerType.name"));
            insurance.setService(new Service(res.getInt("Service.id"), res.getString()));
            insurance.setInsurance();
        }
        statement.close();
        return insurance;
    }

    @Override
    public List<InsuranceUser> findMany(Query query) {
        return null;
    }

    @Override
    public void insertOne(Insurance insurance) {

    }

    @Override
    public void updateOne(Query query, Insurance insurance) {

    }

    @Override
    public void deleteOne(Query query) {

    }
}
