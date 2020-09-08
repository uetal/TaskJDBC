package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl extends Util implements UserService {
    Connection connection = null;

    public void createUsersTable() throws SQLException {
        PreparedStatement preparedStatement = null;
        connection =getConnection();

        String sql = "CREATE TABLE IF NOT EXISTS `dbusers`.`USERS` (\n" +
                "  `ID` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                "  `FIRSTNAME` VARCHAR(255) NOT NULL,\n" +
                "  `SECONDNAME` VARCHAR(255) NOT NULL,\n" +
                "  `AGE` INT NOT NULL,\n" +
                "  PRIMARY KEY (`ID`))\n" +
                "ENGINE = InnoDB\n" +
                "DEFAULT CHARACTER SET = utf8;\n";

        Statement statement = null;

        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (preparedStatement!=null){
                preparedStatement.close();
            }
            if (connection!=null){
                connection.close();
            }
        }
    }

    public void dropUsersTable() throws SQLException {
        PreparedStatement preparedStatement = null;
        connection = getConnection();

        String sql="DROP TABLE IF EXISTS `dbusers`.`USERS`;";

        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (preparedStatement!=null){
                preparedStatement.close();
            }
            if (connection!=null){
                connection.close();
            }
        }

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        connection = getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO USERS (FIRSTNAME, SECONDNAME, AGE) VALUES(?, ?, ?)";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setByte(3,age);

            preparedStatement.executeUpdate();
            System.out.println("User с именем – "+name+" добавлен в базу данных");
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (preparedStatement!=null){
                preparedStatement.close();
            }
            if (connection!=null){
                connection.close();
            }
        }
    }

    public void removeUserById(long id) throws SQLException {
        connection = getConnection();
        PreparedStatement preparedStatement = null;

        String sql ="DELETE FROM USERS WHERE ID=?";

        try{
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (preparedStatement!=null){
                preparedStatement.close();
            }
            if (connection!=null){
                connection.close();
            }
        }

    }

    public List<User> getAllUsers() throws SQLException {
        connection = getConnection();
        List<User>userList = new ArrayList<>();

        String sql = "SELECT ID, FIRSTNAME, SECONDNAME, AGE FROM USERS";

        Statement statement = null;

        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("FIRSTNAME"));
                user.setLastName(resultSet.getString("SECONDNAME"));
                user.setAge(resultSet.getByte("AGE"));

                userList.add(user);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if (statement != null){
                statement.close();
            }
            if (connection!=null){
                connection.close();
            }
        }
        return userList;
    }

    public void cleanUsersTable() throws SQLException {
        connection = getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "TRUNCATE TABLE USERS";

        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (preparedStatement!=null){
                preparedStatement.close();
            }
            if (connection!=null){
                connection.close();
            }
        }
    }
}
