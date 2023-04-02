package ru.ntdv.proicis.graphql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import ru.ntdv.proicis.crud.model.UserRole;
import ru.ntdv.proicis.crud.service.UserService;
import ru.ntdv.proicis.graphql.input.CredentialsInput;
import ru.ntdv.proicis.graphql.input.UserInput;

import javax.management.InstanceAlreadyExistsException;

@Controller
public class CredentialsController {
    @Autowired
    private UserService userService;

    @Secured({"ROLE_Administrator", "ROLE_Moderator"})
    @MutationMapping
    public boolean register(@Argument final CredentialsInput credentialsInput, @Argument final UserInput userInput, @Argument final UserRole.Role role) {
        try {
            userService.register(credentialsInput, userInput, role);
            return true;
        } catch (final InstanceAlreadyExistsException ignored) {
            return false;
        }
    }
}
