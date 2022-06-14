package View;

import Model.Entities.Insurance;
import Model.Entities.User;
import Model.Services.InsuranceService;
import Model.Services.UserService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DashboardForm extends JFrame{
    private JPanel DashsboardPanel;
    private JLabel lbName;
    private JLabel lbFamilyname;
    private JLabel lbNationalcode;
    private JLabel lbNationality;
    private JLabel lbCity;
    private JLabel lbphone;
    private JLabel lbUsername;
    private JLabel lbBirthdate;
    private JButton btnRegister;
    private JTextField txtSearch;
    private JButton btnSearch;
    private JTable tblInsurances;
    private JButton btnSubmit;
    private JButton btnCancel;
    private JButton btnPay;

    private int insurance_id;
    private User foundUser;

    public DashboardForm() {
        insurance_id = -1;
        setTitle("Your information");
        setContentPane(DashsboardPanel);
        setSize(800,550);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setVisible(true);

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterForm registerForm = new RegisterForm(null);
                registerForm.setVisible(true);
            }
        });

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (foundUser != null) {
                    SubmitForm submitForm = new SubmitForm(null, foundUser);
                    submitForm.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(DashboardForm.this,
                            "Search for a user first!",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnPay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // User InsuranceService Pay method
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (UserService service = new UserService(); InsuranceService ins = new InsuranceService()) {
                    foundUser = service.getUserByUsername(txtSearch.getText());
                    lbName.setText("Name: " + foundUser.getFirstname());
                    lbFamilyname.setText("Lastname: " + foundUser.getLastname());
                    lbNationalcode.setText("National Code: " + foundUser.getNationalCode());
                    lbNationality.setText("Nationality: " + foundUser.getNationality());
                    lbCity.setText("City: " + foundUser.getCity());
                    lbphone.setText("Phone Number: " + foundUser.getPhoneNumber());
                    lbUsername.setText("Username: " + foundUser.getUsername());
                    lbBirthdate.setText("Birthdate: " + foundUser.getBirthdate().toString());

                    String cols[] = {"id", "Service", "Company", "Doc Number", "Has Paid", "Date"};
                    DefaultTableModel tableModel = new DefaultTableModel(cols, 0);
                    tblInsurances.setModel(tableModel);

                    List<Insurance> insurances = ins.getInsurancesByUserId(foundUser.getId());
                    for (Insurance insurance : insurances) {
                        Object[] row = {insurance.getId().toString(), insurance.getService().getName(), insurance.getCompany().getName(),insurance.getDocNumber(),
                                insurance.getPaymentCode() != null ? "Yes" : "No", insurance.getDate().toString()};
                        tableModel.addRow(row);
                    }

                    tblInsurances.setCellSelectionEnabled(true);
                    ListSelectionModel select = tblInsurances.getSelectionModel();
                    select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    select.addListSelectionListener(new ListSelectionListener() {
                        @Override
                        public void valueChanged(ListSelectionEvent e) {
                            if (tblInsurances.getSelectedRow() >= 0) {
                                insurance_id = Integer.parseInt(tblInsurances.getValueAt(tblInsurances.getSelectedRow(), 0).toString());
                            }
                        }
                    });
                } catch (Exception ex) {
                    ex.printStackTrace();
                    clearLabels();
                    JOptionPane.showMessageDialog(DashboardForm.this,
                            "Couldn't find the User!",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (insurance_id < 0) {
                    JOptionPane.showMessageDialog(DashboardForm.this,
                            "Please Select one the Insurances first!",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                }
                try (InsuranceService service = new InsuranceService()) {
                    service.cancelInsuranceById(insurance_id);
                    btnSearch.doClick();
                    JOptionPane.showMessageDialog(DashboardForm.this,
                            "Canceled the selected Insurance.",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(DashboardForm.this,
                            "There was some problem!",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void clearLabels() {
        lbName.setText("Name: ");
        lbFamilyname.setText("Lastname: ");
        lbNationalcode.setText("National Code: ");
        lbNationality.setText("Nationality: ");
        lbCity.setText("City: ");
        lbphone.setText("Phone Number: ");
        lbUsername.setText("Username: ");
        lbBirthdate.setText("Birthdate: ");
    }
}
