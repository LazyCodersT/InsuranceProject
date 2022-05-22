package Model.Services;

import Model.Entities.User;

public interface IUserService {
    void signUp(User user);
    boolean authenticate(String username, String password);
    User getUserById(int id);
    User getUserByUsername(String username);
}
