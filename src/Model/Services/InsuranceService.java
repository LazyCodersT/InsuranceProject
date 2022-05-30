package Model.Services;

import Model.Entities.Insurance;
import Model.Entities.Op;
import Model.Entities.Query;
import Model.Repositories.InsuranceRepo;

import java.sql.SQLException;
import java.util.List;

public class InsuranceService implements IInsuranceService {

    private InsuranceRepo repo;

    public InsuranceService() {
        this.repo = new InsuranceRepo();
    }

    @Override
    public void newInsurance(Insurance insurance) throws SQLException {
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
    public void setPaymentCode(int insurance_id, String code) throws SQLException {
        Query query = new Query().setField("id").setOp(Op.EQUAL).setValue(insurance_id);
        Insurance insurance = repo.findOne(query);
        insurance.setPaymentCode(code);
        repo.updateOne(query, insurance);
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
