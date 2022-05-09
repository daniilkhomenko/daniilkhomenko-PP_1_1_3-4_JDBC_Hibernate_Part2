package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;


public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        userService.dropUsersTable();
        userService.createUsersTable();


        userService.saveUser("Anna", "TheGreat", (byte) 20);
        userService.saveUser("Boris", "Johnson", (byte) 25);
        userService.saveUser("Jason", "Statham", (byte) 31);
        userService.saveUser("Sasha", "Volkov", (byte) 38);
        userService.saveUser("Vladimir", "Pozner", (byte) 38);
        userService.saveUser("Anakin", "Skywalker", (byte) 20);

        User user = userService.getAllUsers().get(0);
        System.out.println(user);

        userService.removeUserById(1);
        userService.cleanUsersTable();


        Util.closeConnection();


    }
}
