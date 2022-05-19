package Model.Repositories;

import Model.Entities.Query;
import Model.Entities.User;

import java.util.List;

public interface IUserRepo {
    User findOne(Query query);
    List<User> findMany(Query query);
    void insertOne(User user);
    void updateOne(Query query, User user);
    void deleteOne(Query query);

}
