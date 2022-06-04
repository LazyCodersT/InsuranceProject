package Model.Services;

import Model.Entities.Company;
import Model.Entities.CustomerType;
import Model.Entities.Insurance;
import Model.Entities.Service;

import java.sql.SQLException;
import java.util.List;

public interface IInsuranceService {
    void newInsurance(Insurance insurance) throws SQLException;
    Insurance getInsuranceById(int id) throws SQLException;
    List<Insurance> getInsurancesByUserId(int id) throws SQLException;
    List<Company> getCompanies() throws SQLException;
    List<Service> getServices() throws SQLException;
    List<CustomerType> getCustomerTypes() throws SQLException;
    void pay(int insurance_id, String code) throws SQLException;
    boolean hasPaid(int insurance_id) throws SQLException;
    void updateInsuranceById(int id, Insurance insurance) throws SQLException;
    void cancelInsuranceById(int id) throws SQLException;
}
