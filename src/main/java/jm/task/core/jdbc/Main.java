package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan", "Petrov", (byte)18);
        userService.saveUser("Dima", "Alexiev", (byte)19);
        userService.saveUser("Nikita", "melkih", (byte)21);
        userService.saveUser("Danya", "lowtabov", (byte)22);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
