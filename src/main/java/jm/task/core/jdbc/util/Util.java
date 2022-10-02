package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
//     реализуйте настройку соеденения с БД
    public static Connection getConnection() {
        Properties properties = new Properties();
        try (FileInputStream in = new FileInputStream("src/main/resources/database.properties")){
            properties.load(in);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"), properties.getProperty("password"));
        } catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }

        public static SessionFactory getSessionFactory() {

            Configuration configuration = new Configuration().addAnnotatedClass(User.class);
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            return sessionFactory;
        }
}
