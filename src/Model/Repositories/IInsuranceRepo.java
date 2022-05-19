package Model.Repositories;

import Model.Entities.Insurance;
import Model.Entities.Query;

import java.util.List;

public interface IInsuranceRepo {
    Insurance findOne(Query query);
    List<Insurance> findMany(Query query);
    void insertOne(Insurance insurance);
    void updateOne(Query query, Insurance insurance);
    void deleteOne(Query query);
}
