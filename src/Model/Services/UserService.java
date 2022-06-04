package Model.Services;

import Model.Entities.Op;
import Model.Entities.Query;
import Model.Entities.User;
import Model.Repositories.UserRepo;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.SQLException;

public class UserService implements IUserService {

    private UserRepo repo;

    public UserService() {
        this.repo = new UserRepo();
    }

    @Override
    public void signUp(User user) throws SQLException {
        user.setPassword(hashPass(user.getPassword()));
        repo.insertOne(user);
        repo.commit();
    }

    @Override
    public boolean authenticate(String username, String password) {
        try {
            User user = repo.findOne(new Query().setField("username").setOp(Op.EQUAL).setValue(username));
            if (user.getUsername().equals(username) && hashPass(password).equals(user.getPassword())) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public User getUserById(int id) throws SQLException {
        // Bad practice
        return repo.findOne(new Query().setField("users.id").setOp(Op.EQUAL).setValue(id));
    }

    @Override
    public User getUserByUsername(String username) throws SQLException {
        return repo.findOne(new Query().setField("username").setOp(Op.EQUAL).setValue(username));
    }

    private static String hashPass(String pass) {
        try {
            MessageDigest msg = MessageDigest.getInstance("SHA-256");
            byte[] hash = msg.digest(pass.getBytes(StandardCharsets.UTF_8));
            StringBuilder s = new StringBuilder();
            for (byte b : hash) {
                s.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            return s.toString();
        } catch (Exception e){
            return null;
        }
    }

}
