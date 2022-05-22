import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginForm extends JDialog{
    private JPanel LoginPanel;
    private JTextField txtusername;
    private JPasswordField pfPassword;
    private JButton btnLogin;
    private JButton btnCancel;


    public LoginForm(JFrame parent){
        super(parent);
        setTitle("Login Form");
        setContentPane(LoginPanel);
        setSize(300,350);
        setResizable(false);
        setModal(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtusername.getText();
                String password = String.valueOf(pfPassword.getPassword());

                user = AuthenticatedUser(username,password);

                if (user!=null){
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(LoginForm.this,
                            "Invalid username or password",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                }
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
    public static User user;
    private User AuthenticatedUser(String username,String password){
        User user = null;
        final String DB_URL = "jdbc:oracle:thin:@localhost:1521:ex";
        final String USERNAME = "insurance";
        final String PASSWORD = "myjava123";

        try{
            Connection conn = DriverManager.getConnection(DB_URL,PASSWORD,USERNAME);
            //----------------------------------------------------------------CONNECT TO DATABASE
            Statement stmt = conn.createStatement();
            String sql = "select * from person where username=? and password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);

            ResultSet resultSet = preparedStatement.executeQuery();
           if (resultSet.next()){
               user = new User();
               user.id = resultSet.getString("id");
               user.Name = resultSet.getString("name");
               user.familyname = resultSet.getString("familyname");
               user.FatherName = resultSet.getString("fathername");
               user.Nationalcode = resultSet.getString("nationalcode");
               user.Nationality = resultSet.getString("nationality");
               user.address = resultSet.getString("address");
               user.phone = resultSet.getString("phone");
               user.username = resultSet.getString("username");
               user.password = resultSet.getString("password");
           }

            stmt.close();
            conn.close();

        }
        catch (Exception e){
            System.out.println("There is some problem to connect to DB");
            e.printStackTrace();
        }
        return user;
    }


    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm(null);
        User user = LoginForm.user;
        if (user!=null){
            System.out.println("Authentication was successful for " + user.username);
        }else {
            System.out.println("Authentication canceled.");
        }
    }
}
