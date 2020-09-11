package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.Properties;


public class Main {
    public static void main(String[] args) throws SQLException {

        User user = new User("name","lastname", (byte) 6);

        UserService userService = new UserServiceImpl();



        userService.createUsersTable();

        userService.saveUser(user.getName(),user.getLastName(),user.getAge());
        userService.saveUser(user.getName(),user.getLastName(),user.getAge());
        userService.saveUser(user.getName(),user.getLastName(),user.getAge());
        userService.saveUser(user.getName(),user.getLastName(),user.getAge());
        userService.removeUserById(2);

        System.out.println(userService.getAllUsers().toString());

        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
