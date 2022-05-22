package Model.Repositories;

import Model.Entities.Insurance;
import Model.Entities.Query;

import java.sql.SQLException;
import java.util.List;

public interface IInsuranceRepo {
    Insurance findOne(Query query) throws SQLException;
    List<Insurance> findMany(Query query) throws SQLException;
    void insertOne(Insurance insurance) throws SQLException;
    void updateOne(Query query, Insurance insurance) throws SQLException;
    void deleteOne(Query query) throws SQLException;
}
