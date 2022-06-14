package View;

import Model.Entities.User;
import Model.Entities.UserType;
import Model.Services.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;


public class RegisterForm extends JDialog {
    private JPanel RegisterPanel;
    private JTextField txtName;
    private JTextField txtFamilyName;
    private JTextField txtFatherName;
    private JTextField txtNationalcode;
    private JTextField txtNationality;
    private JTextField txtCity;
    private JTextField txtcallNumber;
    private JPasswordField pfPassword;
    private JButton btnRegister;
    private JButton btnCancel;
    private JPasswordField pfConfirmPassword;
    private JTextField txtUsername;
    private JTextField txtIdNumber;
    private JTextField txtHomeNumber;
    private JTextField txtYear;
    private JTextField txtMonth;
    private JTextField txtDay;
    private JTextField txtBirth;
    private String id;


    public RegisterForm(JFrame parent) {
        super(parent);
        setTitle("Enter your information");
        setContentPane(RegisterPanel);
        setSize(550, 600);
        setResizable(false);
        setModal(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RgisterUser();
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    private void RgisterUser() {
        String name = txtName.getText();
        String lastname = txtFamilyName.getText();
        String fatherName = txtFatherName.getText();
        String nationalCode = txtNationalcode.getText();
        String idNumber = txtIdNumber.getText();
        String nationality = txtNationality.getText();
        String city = txtCity.getText();
        String phone = txtcallNumber.getText();
        String homeNumber = txtHomeNumber.getText();
        String username = txtUsername.getText();
        String password = String.valueOf(pfPassword.getPassword());
        String confirmPassword = String.valueOf(pfConfirmPassword.getPassword());

        if (name.isEmpty() || lastname.isEmpty() || fatherName.isEmpty() || nationalCode.isEmpty() || nationality.isEmpty() || city.isEmpty()
                || phone.isEmpty() || homeNumber.isEmpty() || idNumber.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Please fill all fields.",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)){
            JOptionPane.showMessageDialog(this,
                    "Confirm password does not match!",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (UserService service = new UserService()) {
            User user = new User().setFirstname(name).setLastname(lastname).setFatherName(fatherName).setIdNumber(idNumber).setHomeNumber(homeNumber)
                    .setNationalCode(nationalCode).setNationality(nationality).setCity(city).setPhoneNumber(phone).setUsername(username).setPassword(password)
                    .setType(UserType.USER).setBirthdate(new Date(Integer.parseInt(txtYear.getText()), Integer.parseInt(txtMonth.getText()), Integer.parseInt(txtDay.getText())));
            service.signUp(user);
            JOptionPane.showMessageDialog(this,
                    "Successfully Registered the User.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            clearTxt();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Field to register new user!",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearTxt() {
        txtName.setText(null);
        txtFamilyName.setText(null);
        txtFatherName.setText(null);
        txtYear.setText(null);
        txtMonth.setText(null);
        txtDay.setText(null);
        txtNationalcode.setText(null);
        txtIdNumber.setText(null);
        txtHomeNumber.setText(null);
        txtNationality.setText(null);
        txtCity.setText(null);
        txtcallNumber.setText(null);
        txtUsername.setText(null);
        pfPassword.setText(null);
        pfConfirmPassword.setText(null);
    }
}
