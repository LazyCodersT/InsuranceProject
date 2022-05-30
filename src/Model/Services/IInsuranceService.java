package Model.Services;

import Model.Entities.Insurance;

import java.sql.SQLException;
import java.util.List;

public interface IInsuranceService {
    void newInsurance(Insurance insurance) throws SQLException;
    Insurance getInsuranceById(int id) throws SQLException;
    List<Insurance> getInsurancesByUserId(int id) throws SQLException;
    void setPaymentCode(int insurance_id, String code) throws SQLException;
    void updateInsuranceById(int id, Insurance insurance) throws SQLException;
    void cancelInsuranceById(int id) throws SQLException;
}