package Model.Repositories;

import Model.Entities.Query;
import Model.Entities.User;

public class UserRepo implements IUserRepo{
    @Override
    public User selectUser(Query query) {
        return null;
    }

    @Override
    public void createUser(User user) {

    }

    @Override
    public void updateUser(Query query, User user) {

    }

    @Override
    public void deleteUser(Query query) {

    }
}
