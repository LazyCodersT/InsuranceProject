package Model.Services;

import Model.Entities.*;
import Model.Repositories.InsuranceRepo;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class InsuranceService implements IInsuranceService {

    private InsuranceRepo repo;

    public InsuranceService() {
        this.repo = new InsuranceRepo();
    }

    @Override
    public void newInsurance(Insurance insurance) throws SQLException {
        insurance.setDate(new Date(new java.util.Date().getDate()));
        repo.insertOne(insurance);
    }

    @Override
    public Insurance getInsuranceById(int id) throws SQLException {
        return repo.findOne(new Query().setField("id").setOp(Op.EQUAL).setValue(id));
    }

    @Override
    public List<Insurance> getInsurancesByUserId(int id) throws SQLException {
        return repo.findMany(new Query().setField("user_id").setOp(Op.EQUAL).setValue(id));
    }

    @Override
    public List<Company> getCompanies() throws SQLException {
        return null;
    }

    @Override
    public List<Service> getServices() throws SQLException {
        return null;
    }

    @Override
    public void setPaymentCode(int insurance_id, String code) throws SQLException {
        Query query = new Query().setField("id").setOp(Op.EQUAL).setValue(insurance_id);
        Insurance insurance = repo.findOne(query);
        insurance.setPaymentCode(code);
        repo.updateOne(query, insurance);
    }
    
    public boolean hasPaid(int insurance_id) throws SQLException {
        Insurance insurance = repo.findOne(new Query().setField("id").setOp(Op.EQUAL).setValue(insurance_id));
        return insurance.getPaymentCode() != null;
    }

    @Override
    public void updateInsuranceById(int id, Insurance insurance) throws SQLException {
        repo.updateOne(new Query().setField("id").setOp(Op.EQUAL).setValue(id), insurance);
    }

    @Override
    public void cancelInsuranceById(int id) throws SQLException {
        repo.deleteOne(new Query().setField("id").setOp(Op.EQUAL).setValue(id));
    }
}
