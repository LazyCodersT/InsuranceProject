package Model.Repositories;

import Model.Entities.Query;
import Model.Entities.User;
import Model.Entities.UserType;

import java.sql.*;
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
        statement.close();
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
        statement.close();
        return users;
    }

    @Override
    public void insertOne(User user) throws SQLException {
        String query = "INSERT INTO Users VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(0, user.getUsername());
        statement.setString(1, user.getPassword());
        statement.setString(2, user.getFirstname());
        statement.setString(3, user.getLastname());
        statement.setDate(4, user.getBirthdate());
        statement.setString(5, user.getFatherName());
        statement.setString(6, user.getNationalCode());
        statement.setString(7, user.getIdNumber());
        statement.setString(8, user.getNationality());
        statement.setString(9, user.getCity());
        statement.setString(10, user.getPhoneNumber());
        statement.setString(11, user.getHomeNumber());
        statement.setInt(12, user.getType().getPrivilegeId());
        statement.execute();
        statement.close();
    }

    @Override
    public void updateOne(Query query, User user) throws SQLException {
        String q = "UPDATE Users SET username=?, password=?, firstname=?," +
                "lastname=?, birthdate=?, fatherName=?, nationalCode=?," +
                "idNumber=?, nationality=?, city=?, phoneNumber=?, homeNumber=?" +
                "privilege_id=?" + query.parseQuery() + ";";
        PreparedStatement statement = conn.prepareStatement(q);
        statement.setString(0, user.getUsername());
        statement.setString(1, user.getPassword());
        statement.setString(2, user.getFirstname());
        statement.setString(3, user.getLastname());
        statement.setDate(4, user.getBirthdate());
        statement.setString(5, user.getFatherName());
        statement.setString(6, user.getNationalCode());
        statement.setString(7, user.getIdNumber());
        statement.setString(8, user.getNationality());
        statement.setString(9, user.getCity());
        statement.setString(10, user.getPhoneNumber());
        statement.setString(11, user.getHomeNumber());
        statement.setInt(12, user.getType().getPrivilegeId());
        statement.execute();
        statement.close();
    }

    @Override
    public void deleteOne(Query query) throws SQLException {
        String q = "DELETE FROM Users" + query.parseQuery() + " limit 1;";
        Statement statement = conn.createStatement();
        statement.execute(q);
        statement.close();
    }
}
