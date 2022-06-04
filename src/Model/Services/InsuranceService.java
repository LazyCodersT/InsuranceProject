package Model.Services;

import Model.Entities.*;
import Model.Repositories.InsuranceRepo;
import Model.Repositories.Util;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class InsuranceService implements IInsuranceService {

    private InsuranceRepo repo;
    public InsuranceService() {
        this.repo = new InsuranceRepo();
    }

    @Override
    public void newInsurance(Insurance insurance) throws SQLException {
        insurance.setDate(new Date(new java.util.Date().getDate()));
        repo.insertOne(insurance);
        repo.commit();
    }

    @Override
    public Insurance getInsuranceById(int id) throws SQLException {
        // bad practice
        return repo.findOne(new Query().setField("insurances.id").setOp(Op.EQUAL).setValue(id));
    }

    @Override
    public List<Insurance> getInsurancesByUserId(int id) throws SQLException {
        return repo.findMany(new Query().setField("user_id").setOp(Op.EQUAL).setValue(id));
    }

    @Override
    public List<Company> getCompanies() throws SQLException {
        return new Util().findAllCompanies();
    }

    @Override
    public List<Service> getServices() throws SQLException {
        return new Util().findAllServices();
    }

    public List<CustomerType> getCustomerTypes() throws SQLException {
        return new Util().findAllCustomerTypes();
    }

    @Override
    public void pay(int insurance_id, String code) throws SQLException {
        // again bad practice
        if (!hasPaid(insurance_id)){
            Query query = new Query().setField("insurances.id").setOp(Op.EQUAL).setValue(insurance_id);
            Insurance insurance = repo.findOne(query);
            insurance.setPaymentCode(code);
            repo.updateOne(query, insurance);
            repo.commit();
        }
    }
    
    public boolean hasPaid(int insurance_id) throws SQLException {
        // again bad practice
        Insurance insurance = repo.findOne(new Query().setField("insurances.id").setOp(Op.EQUAL).setValue(insurance_id));
        long dateDiff = Math.abs(insurance.getDate().getTime() - new Date(new java.util.Date().getDate()).getTime());
        dateDiff = (dateDiff / (1000 * 60 * 60 * 24));
        if (dateDiff >= 365) {
            insurance.setPaymentCode(null);
            repo.updateOne(new Query().setField("id").setOp(Op.EQUAL).setValue(insurance.getId()), insurance);
            repo.commit();
        }
        return insurance.getPaymentCode() != null;
    }

    @Override
    public void updateInsuranceById(int id, Insurance insurance) throws SQLException {
        repo.updateOne(new Query().setField("insurances.id").setOp(Op.EQUAL).setValue(id), insurance);
        repo.commit();
    }

    @Override
    public void cancelInsuranceById(int id) throws SQLException {
        repo.deleteOne(new Query().setField("insurances.id").setOp(Op.EQUAL).setValue(id));
        repo.commit();
    }
}
