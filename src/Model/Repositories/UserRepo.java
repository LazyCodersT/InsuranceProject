package Model.Repositories;

import Model.Entities.Query;
import Model.Entities.User;

import java.util.List;

public class UserRepo implements IUserRepo{

    @Override
    public User findOne(Query query) {
        return null;
    }

    @Override
    public List<User> findMany(Query query) {
        return null;
    }

    @Override
    public void insertOne(User user) {

    }

    @Override
    public void updateOne(Query query, User user) {

    }

    @Override
    public void deleteOne(Query query) {

    }
}
