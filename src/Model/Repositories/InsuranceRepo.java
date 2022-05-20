package Model.Repositories;

import Model.Entities.Insurance;
import Model.Entities.Query;
import Model.Entities.User;
import Model.Entities.UserType;

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
    public Insurance findOne(Query query) throws SQLException {
        return null;
    }

    @Override
    public List<Insurance> findMany(Query query) {
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

    private UserType getType(bool type) {
        if (type)
    }
}
