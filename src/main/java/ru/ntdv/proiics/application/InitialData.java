package ru.ntdv.proiics.application;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.ntdv.proiics.crud.model.UserRole;
import ru.ntdv.proiics.crud.service.UserRoleService;
import ru.ntdv.proiics.crud.service.UserService;
import ru.ntdv.proiics.graphql.input.CredentialsInput;
import ru.ntdv.proiics.graphql.input.UserInput;

import javax.management.InstanceAlreadyExistsException;

@Component
@AllArgsConstructor
public class InitialData implements ApplicationRunner {
    @Autowired
    private final UserService userService;
    @Autowired
    private final UserRoleService userRoleService;

    public void run(final ApplicationArguments args) {
        userRoleService.persistRoles();

        if (userService.findUserById((long) 1).isPresent()) return;
        final var user = new UserInput("Danila", "Valkovets", "Igorevich", "https://vk.com/danila_valkovets", "https://t.me/danichvolk", "Б22-534", "МИФИ");
        final var credentials = new CredentialsInput("ntdv", "qwerty123");
        try {
            userService.register(credentials, user, UserRole.Role.Administrator);
        } catch (final InstanceAlreadyExistsException e) {
            e.printStackTrace(System.err);
        }
    }
}
