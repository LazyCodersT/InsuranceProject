import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RegisterForm extends JDialog {
    private JPanel RegisterPanel;
    private JTextField txtName;
    private JTextField txtFamilyName;
    private JTextField txtFatherName;
    private JTextField txtNationalcode;
    private JTextField txtNationality;
    private JTextField txtAddress;
    private JTextField txtcallNumber;
    private JPasswordField pfPassword;
    private JComboBox comboBox1;
    private JButton btnRegister;
    private JButton btnCancel;
    private JComboBox comboBox2;
    private JPasswordField pfConfirmPassword;
    private JTextField txtUsername;
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
        String familyname = txtFamilyName.getText();
        String fathername = txtFatherName.getText();
        String ntionalcode = txtNationalcode.getText();
        String nationality = txtNationality.getText();
        String address = txtAddress.getText();
        String phone = txtcallNumber.getText();
        String username = txtUsername.getText();
        String password = String.valueOf(pfPassword.getPassword());
        String confirmPassword = String.valueOf(pfConfirmPassword.getPassword());

        if (name.isEmpty() || familyname.isEmpty() || fathername.isEmpty() || ntionalcode.isEmpty() || nationality.isEmpty() || address.isEmpty()
                || phone.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
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

        user = addUserToDB(name,familyname,fathername,ntionalcode,nationality,address,phone,username,password);
        if (user!=null){
            dispose();
        }else{
            JOptionPane.showMessageDialog(this,
                    "Field to register new user!",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }


    public User user;
    private User addUserToDB(String name,String familyname,String fathername,String nationalcode,String nationality,String address,
                             String phone,String username,String password){
        User user = null;
        final String DB_URL = "jdbc:oracle:thin:@localhost:1521:ex";
        final String USERNAME = "insurance";
        final String PASSWORD = "myjava123";

        try{
            Connection conn = DriverManager.getConnection(DB_URL,PASSWORD,USERNAME);
            //-----------------------------------------------------------------------------CONNECT TO DATABASE!
            Statement stmt = conn.createStatement();
            String sql = "insert into person (id,name,familyname,fathername,nationalcode,natiolity,address,phone,username,password)" +
                    "values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,id);
            preparedStatement.setString(2,name);
            preparedStatement.setString(3,familyname);
            preparedStatement.setString(4,fathername);
            preparedStatement.setString(5,nationalcode);
            preparedStatement.setString(6,nationality);
            preparedStatement.setString(7,address);
            preparedStatement.setString(8,phone);
            preparedStatement.setString(9,username);
            preparedStatement.setString(10,password);

            int addrows = preparedStatement.executeUpdate();
            if (addrows>0){
                user = new User();
                user.Name = name;
                user.familyname = familyname;
                user.FatherName = fathername;
                user.Nationalcode = nationalcode;
                user.Nationality = nationality;
                user.address = address;
                user.phone = phone;
                user.username = username;
                user.password = password;
            }

            stmt.close();
            conn.close();
        }catch (Exception e){
            System.out.println("There is some problem to connect to database!!");
            e.printStackTrace();
        }
        return user;
    }

    public static void main(String[] args) {
        RegisterForm registerForm = new RegisterForm(null);
        User user = registerForm.user;
        if (user!=null){
            System.out.println("Registration was successful for " + user.username);
        }else {
            System.out.println("Registration canceled.");
        }
    }
}