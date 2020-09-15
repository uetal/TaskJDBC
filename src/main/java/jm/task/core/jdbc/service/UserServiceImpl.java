package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDaoHibernate = new UserDaoHibernateImpl();
    UserDao userDaoJDBC = new UserDaoJDBCImpl();

    public void createUsersTable() throws SQLException {
        //userDaoJDBC.createUsersTable();
        userDaoHibernate.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        //userDaoJDBC.dropUsersTable();
        userDaoHibernate.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        //userDaoJDBC.saveUser(name, lastName, age);
        userDaoHibernate.saveUser(name,lastName,age);
    }

    public void removeUserById(long id) throws SQLException {
        //userDaoJDBC.removeUserById(id);
        userDaoHibernate.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        //List<User>userList = userDaoJDBC.getAllUsers();
        List<User>userList = userDaoHibernate.getAllUsers();
        return userList;
    }

    public void cleanUsersTable() throws SQLException {
        //userDaoJDBC.cleanUsersTable();
        userDaoHibernate.cleanUsersTable();
    }
}
