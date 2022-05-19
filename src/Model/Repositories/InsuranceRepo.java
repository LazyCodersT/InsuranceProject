package Model.Repositories;

import Model.Entities.Insurance;
import Model.Entities.Query;

import java.util.List;

public class InsuranceRepo implements IInsuranceRepo{
    @Override
    public Insurance findOne(Query query) {
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
}
