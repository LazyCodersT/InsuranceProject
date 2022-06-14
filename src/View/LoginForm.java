package View;

import Model.Entities.User;
import Model.Services.UserService;

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
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(parent);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtusername.getText();
                String password = String.valueOf(pfPassword.getPassword());

                try (UserService service = new UserService()) {
                    boolean is_user_valid = service.authenticate(username, password);

                    if (is_user_valid){
                        dispose();
                    }else{
                        JOptionPane.showMessageDialog(LoginForm.this,
                                "Invalid username or password",
                                "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
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
                System.exit(0);
            }
        });

        setVisible(true);
    }


    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm(null);
    }
}
