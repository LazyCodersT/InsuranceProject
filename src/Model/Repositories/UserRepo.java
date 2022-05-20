package Model.Repositories;

import Model.Entities.Query;
import Model.Entities.User;
import Model.Entities.UserType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepo implements IUserRepo{

    private Connection conn;

    public UserRepo() {
        this.conn = Database.getInstance().getConnection();
    }

    @Override
    public User findOne(Query query) throws SQLException {
        String q = "SELECT * FROM Users JOIN Privileges ON Users.privilege_id = Privileges.id" + query.parseQuery() + " limit 1;";
        Statement statement = conn.createStatement();
        ResultSet res = statement.executeQuery(q);
        User user = new User();
        if (res.next()) {
            user.setId(res.getInt("id")).setUsername(res.getString("username")).setPassword(res.getString("password"));
            user.setFirstname(res.getString("firstname")).setLastname(res.getString("lastname")).setBirthdate(res.getDate("birthdate"));
            user.setFatherName(res.getString("fathername")).setNationalCode(res.getString("nationaCode")).setIdNumber(res.getString("idNumber"));
            user.setNationality(res.getString("nationality")).setCity(res.getString("city")).setPhoneNumber(res.getString("phoneNumber"));
            user.setHomeNumber(res.getString("homeNumber")).setType(res.getBoolean("isAdmin") ? UserType.ADMIN: UserType.USER);
        }
        return user;
    }

    @Override
    public List<User> findMany(Query query) throws SQLException {
        String q = "SELECT * FROM Users JOIN Privileges ON Users.privilege_id = Privileges.id" + query.parseQuery() + ";";
        Statement statement = conn.createStatement();
        ResultSet res = statement.executeQuery(q);
        List users = new ArrayList();
        while (res.next()) {
            User user = new User();
            user.setId(res.getInt("id")).setUsername(res.getString("username")).setPassword(res.getString("password"));
            user.setFirstname(res.getString("firstname")).setLastname(res.getString("lastname")).setBirthdate(res.getDate("birthdate"));
            user.setFatherName(res.getString("fathername")).setNationalCode(res.getString("nationaCode")).setIdNumber(res.getString("idNumber"));
            user.setNationality(res.getString("nationality")).setCity(res.getString("city")).setPhoneNumber(res.getString("phoneNumber"));
            user.setHomeNumber(res.getString("homeNumber")).setType(res.getBoolean("isAdmin") ? UserType.ADMIN: UserType.USER);
            users.add(user);
        }
        return users;
    }

    @Override
    public void insertOne(User user) {
        String query = "INSERT INTO Users VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    }

    @Override
    public void updateOne(Query query, User user) {

    }

    @Override
    public void deleteOne(Query query) {

    }
}
