package Model.Repositories;

import Model.Entities.Query;
import Model.Entities.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserRepo {
    User findOne(Query query) throws SQLException;
    List<User> findMany(Query query) throws SQLException;
    void insertOne(User user);
    void updateOne(Query query, User user);
    void deleteOne(Query query);

}
