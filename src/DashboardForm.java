import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DashboardForm extends JFrame{
    private JPanel DashsboardPanel;
    private JLabel lbName;
    private JLabel lbFamilyname;
    private JLabel lbNationalcode;
    private JLabel lbNationality;
    private JLabel lbAddress;
    private JLabel lbphone;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton registerButton;


    public DashboardForm(){
        setTitle("Your information");
        setContentPane(DashsboardPanel);
        setSize(500,550);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        boolean hasRegisteredUser = ConnectToDB();
        //-------------------------------------------------SHOW LOGIN FORM.
      if(hasRegisteredUser){
          LoginForm loginForm = new LoginForm(this);
          User user = loginForm.user;

          if (user!=null){
              lbName.setText("" + user.Name);
              lbFamilyname.setText("" + user.familyname);
              lbNationalcode.setText("" + user.Nationalcode);
              lbNationality.setText("" + user.Nationality);
              lbAddress.setText("" + user.address);
              lbphone.setText("" + user.phone);
              lbUsername.setText("" + user.username);
              lbPassword.setText("" + user.password);
              setLocationRelativeTo(null);
              setVisible(true);
          }else{
              dispose();
          }
      }else{
          //-----------------------------------------------------SHOW REGISTRATION form.
          RegisterForm registerForm = new RegisterForm(this);
          User user = registerForm.user;

          if (user!=null){
              lbName.setText("" + user.Name);
              lbFamilyname.setText("" + user.familyname);
              lbNationalcode.setText("" + user.Nationalcode);
              lbNationality.setText("" + user.Nationality);
              lbAddress.setText("" + user.address);
              lbphone.setText("" + user.phone);
              lbUsername.setText("" + user.username);
              lbPassword.setText("" + user.password);
              setLocationRelativeTo(null);
              setVisible(true);
          }else{
              dispose();
          }
       }
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterForm registerForm = new RegisterForm(DashboardForm.this);
                User user = registerForm.user;

                if (user!=null){
                    JOptionPane.showMessageDialog(DashboardForm.this,
                            "New user for " + user.username,
                            "Successful registration",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }



    private boolean ConnectToDB(){
        boolean hasRegisteredUser = false;

        final String ORACLE_SERVER = "jdbc:oracle:thin:@localhost";
        final String DB_URL = "JDBC:oracle:thin:@localhost:1521:ex";
        final String USERNAME = "insurance";
        final String PASSWORD = "myjava123";

        try{
            //--------------------------------------------------------------------FIRST CONNECTION TO DB TO CREATE TABLE .
            Connection conn = DriverManager.getConnection(ORACLE_SERVER,USERNAME,PASSWORD);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS insurance");
            stmt.close();
            conn.close();

            //-------------------------------------------------------------------SECOND CONNECTION TO DB FOR CREATING A NEW USER.
            conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            stmt = conn.createStatement();
            String sql = "CREATE DATABASE IF NOT EXISTS insurance("
                    +"id varchar2(20),"
                    +"name varchar2(40),"
                    +"familyname varchar2(60),"
                    +"fathername varchar2(40),"
                    +"nationalcode varchar2(30),"
                    +"nationality varchar2(30),"
                    +"address varchar2(100),"
                    +"phone varchar2(20),"
                    +"username varchar2(60),"
                    +"password varchar2(60),"
                    +")";
            stmt.executeUpdate(sql);

            //---------------------------------------------------------------------CHECK THAT WE HAVE USER IN DATABASE.
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT COUNT (*) FROM insurance");

            if (resultSet.next()){
                int numUser = resultSet.getInt(1);
                if (numUser >0){
                    hasRegisteredUser = true;
                }
            }

            stmt.close();
            conn.close();

        }catch(Exception e){
            System.out.println("There is some problem to connect to the database.");
            e.printStackTrace();
        }
        return hasRegisteredUser;
    }

    public static void main(String[] args) {
        DashboardForm dashboardForm = new DashboardForm();
    }
}
