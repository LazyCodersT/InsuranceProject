import Model.Entities.*;
import Model.Services.IInsuranceService;
import Model.Services.IUserService;
import Model.Services.InsuranceService;
import Model.Services.UserService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String []args) throws SQLException {
        IUserService userService = new UserService();
        /* -----------Sign up------------
        User newUser = new User();
        newUser.setUsername("emad").setPassword("1234").setFirstname("emad").setLastname("safari")
                .setBirthdate(new Date(27, 8, 2003)).setFatherName("mohammad").setNationalCode("12345678")
                .setIdNumber("09182974793847").setNationality("Persian").setCity("Tehran").setPhoneNumber("09105912146")
                .setHomeNumber("44614352").setType(UserType.USER);
        try {
            userService.signUp(newUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        // sign in
        System.out.println(userService.authenticate("emad", "123"));

        // get user
        /*try {
            User user = userService.getUserById(6);
            System.out.println(user.getFirstname());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("user not found!");
        }*/

        IInsuranceService insuranceService = new InsuranceService();

        // new Insurance
        Insurance insurance = new Insurance();
        insurance.setDocNumber(123456);
        insurance.setPaymentCode("laksdjf1234");
        insurance.setJobVerificationCode("0987");
        insurance.setCustomerType(insuranceService.getCustomerTypes().get(0));
        insurance.setService(insuranceService.getServices().get(1));
        insurance.setCompany(insuranceService.getCompanies().get(1));
        insurance.setUserId(6);
        insuranceService.newInsurance(insurance);

        // get insurance
        List<Insurance> ins = insuranceService.getInsurancesByUserId(6);
        System.out.println(ins.get(0).getPaymentCode());

        // has paid
        //System.out.println(insuranceService.hasPaid(1));

        // pay
        //insuranceService.pay(1, "1231kj3");

        // cancel insurance
        //insuranceService.cancelInsuranceById(1);

        System.out.println("Hello World");
    }
}
