package Model.Services;

import Model.Entities.Insurance;

import java.util.List;

public interface IInsuranceService {
    void newInsurance(Insurance insurance);
    Insurance getInsuranceById(int id);
    List<Insurance> getInsurancesByUserId(int id);
    void setPaymentCode(int insurance_id, int code);
    void updateInsuranceById(int id, Insurance insurance);
    void cancelInsuranceById(int id);
}
