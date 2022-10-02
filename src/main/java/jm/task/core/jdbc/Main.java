package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private static final UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        userService.createUsersTable();
        userService.saveUser("Иван", "Иванов", (byte) 20);
        userService.saveUser("Алексей", "Алексеев", (byte) 21);
        userService.saveUser("Пётр", "Петров", (byte) 22);
        userService.saveUser("Роман", "Романов", (byte) 23);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
