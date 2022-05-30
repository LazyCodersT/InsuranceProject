package Model.Services;

import Model.Entities.User;

import java.sql.SQLException;

public interface IUserService {
    void signUp(User user) throws SQLException;
    boolean authenticate(String username, String password);
    User getUserById(int id) throws SQLException;
    User getUserByUsername(String username) throws SQLException;
}
