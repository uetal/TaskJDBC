package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();

        User user = new User("name","lastname", (byte) 4);

        userService.createUsersTable();

        userService.saveUser(user.getName(),user.getLastName(),user.getAge());
        userService.saveUser(user.getName(),user.getLastName(),user.getAge());
        userService.saveUser(user.getName(),user.getLastName(),user.getAge());
        userService.saveUser(user.getName(),user.getLastName(),user.getAge());

        System.out.println(userService.getAllUsers().toString());

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
