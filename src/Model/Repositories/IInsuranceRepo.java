package Model.Repositories;

import Model.Entities.Insurance;
import Model.Entities.Query;

public interface IInsuranceRepo {
    Insurance selectInsurance();
    void createInsurance(Insurance insurance);
    void updateInsurance(Query query, Insurance insurance);
    void deleteInsurance(Query query);
}
