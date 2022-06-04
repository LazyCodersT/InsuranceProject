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
        String q = "SELECT * FROM users JOIN privileges ON users.privilege_id = privileges.id" + query.parseQuery() + " limit 1;";
        Statement statement = conn.createStatement();
        ResultSet res = statement.executeQuery(q);
        User user = new User();
        if (res.next()) {
            user.setId(res.getInt("id")).setUsername(res.getString("username")).setPassword(res.getString("password"));
            user.setFirstname(res.getString("firstname")).setLastname(res.getString("lastname")).setBirthdate(res.getDate("birthdate"));
            user.setFatherName(res.getString("father_name")).setNationalCode(res.getString("national_code")).setIdNumber(res.getString("id_number"));
            user.setNationality(res.getString("nationality")).setCity(res.getString("city")).setPhoneNumber(res.getString("phone_number"));
            user.setHomeNumber(res.getString("home_number")).setType(res.getBoolean("privileges.is_admin") ? UserType.ADMIN: UserType.USER);
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
            user.setFatherName(res.getString("father_name")).setNationalCode(res.getString("national_code")).setIdNumber(res.getString("id_number"));
            user.setNationality(res.getString("nationality")).setCity(res.getString("city")).setPhoneNumber(res.getString("phone-number"));
            user.setHomeNumber(res.getString("home-number")).setType(res.getBoolean("privileges.is_admin") ? UserType.ADMIN: UserType.USER);
            users.add(user);
        }
        statement.close();
        return users;
    }

    @Override
    public void insertOne(User user) throws SQLException {
        String query = "INSERT INTO users (username, password, firstname, lastname, birthdate, father_name, national_code, id_number, nationality, city, phone_number, home_number, privilege_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getFirstname());
        statement.setString(4, user.getLastname());
        statement.setDate(5, user.getBirthdate());
        statement.setString(6, user.getFatherName());
        statement.setString(7, user.getNationalCode());
        statement.setString(8, user.getIdNumber());
        statement.setString(9, user.getNationality());
        statement.setString(10, user.getCity());
        statement.setString(11, user.getPhoneNumber());
        statement.setString(12, user.getHomeNumber());
        statement.setInt(13, user.getType().getPrivilegeId());
        statement.execute();
        statement.close();
    }

    @Override
    public void updateOne(Query query, User user) throws SQLException {
        String q = "UPDATE users SET username=?, password=?, firstname=?," +
                "lastname=?, birthdate=?, father_name=?, national_code=?," +
                "id_number=?, nationality=?, city=?, phone_number=?, home_number=?" +
                "privilege_id=?" + query.parseQuery() + ";";
        PreparedStatement statement = conn.prepareStatement(q);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getFirstname());
        statement.setString(4, user.getLastname());
        statement.setDate(5, user.getBirthdate());
        statement.setString(6, user.getFatherName());
        statement.setString(7, user.getNationalCode());
        statement.setString(8, user.getIdNumber());
        statement.setString(9, user.getNationality());
        statement.setString(10, user.getCity());
        statement.setString(11, user.getPhoneNumber());
        statement.setString(12, user.getHomeNumber());
        statement.setInt(13, user.getType().getPrivilegeId());
        statement.execute();
        statement.close();
    }

    @Override
    public void deleteOne(Query query) throws SQLException {
        String q = "DELETE FROM users" + query.parseQuery() + " limit 1;";
        Statement statement = conn.createStatement();
        statement.execute(q);
        statement.close();
    }

    public void commit() throws SQLException {
        this.conn.commit();
    }

    public void rollback() throws SQLException {
        this.conn.rollback();
    }
}
