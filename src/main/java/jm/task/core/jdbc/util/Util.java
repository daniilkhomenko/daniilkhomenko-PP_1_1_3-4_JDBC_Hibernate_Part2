package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.*;
import java.util.Properties;

public class Util {

    private static Connection conn;

    public static void connect () {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kata_homework1", "root", "Agotis444");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void connectHibernate () {

    }

    public static Connection getConnection() {
        return conn;
    }

    //закрывает общий Connection в Main, чтобы переиспользовать его в DAO, а не открывать и закрывать каждый раз заново
    public static void closeConnection () {
        try {
           conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
