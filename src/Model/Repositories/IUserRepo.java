package Model.Repositories;

import Model.Entities.Query;
import Model.Entities.User;

public interface IUserRepo {
    User selectUser(Query query);
    void createUser(User user);
    void updateUser(Query query, User user);
    void deleteUser(Query query);

}
