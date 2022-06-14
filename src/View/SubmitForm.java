package View;

import Model.Entities.*;
import Model.Services.InsuranceService;
import Model.Services.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;


public class SubmitForm extends JDialog {
    private JPanel RegisterPanel;
    private JButton btnRegister;
    private JButton btnCancel;
    private JComboBox<String> comboService;
    private JComboBox<String> comboCompany;
    private JComboBox<String> comboCustomer;
    private JTextField txtDoc;
    private JTextField txtJob;
    private JTextField txtPayment;
    private String id;

    private User user;
    private List<Service> services;
    private List<Company> companies;
    private List<CustomerType> types;

    public SubmitForm(JFrame parent, User user) {
        super(parent);
        this.user = user;
        setTitle("Submit a new Insurance");
        setContentPane(RegisterPanel);
        setSize(550, 600);
        setResizable(false);
        setModal(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);

        try (InsuranceService service = new InsuranceService()) {
            services = service.getServices();
            companies = service.getCompanies();
            types = service.getCustomerTypes();
            for (Service serv: services) {
                comboService.addItem(serv.getName());
            }
            for (Company company: companies) {
                comboCompany.addItem(company.getName());
            }
            for (CustomerType type: types) {
                comboCustomer.addItem(type.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitInsurance();
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

    private void submitInsurance() {
        Service service = services.get(comboService.getSelectedIndex());
        Company company = companies.get(comboCompany.getSelectedIndex());
        CustomerType type = types.get(comboCustomer.getSelectedIndex());
        String docNumber = txtDoc.getText();
        String jobCode = txtJob.getText();
        String paymentCode = txtPayment.getText();

        if (docNumber.isEmpty() || jobCode.isEmpty() || paymentCode.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Please fill all fields.",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (InsuranceService insService = new InsuranceService()) {
            Insurance newIns = new Insurance().setService(service).setCompany(company).setCustomerType(type).setUserId(user.getId())
                            .setDocNumber(Integer.parseInt(docNumber)).setPaymentCode(paymentCode).setJobVerificationCode(jobCode);
            insService.newInsurance(newIns);
            JOptionPane.showMessageDialog(this,
                    "Successfully submitted the Insurance.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            clearTxt();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Field to submit!",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearTxt() {
        txtDoc.setText(null);
        txtJob.setText(null);
        txtPayment.setText(null);
    }
}
