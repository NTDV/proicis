package ru.ntdv.proiics.graphql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import ru.ntdv.proiics.crud.service.UserService;
import ru.ntdv.proiics.graphql.model.User;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Secured({"ROLE_Administrator", "ROLE_Moderator"})
    @QueryMapping
    public List<User> getAllParticipants() {
        return userService.getAllParticipants().stream().map(User::new).toList();
    }

    @Secured({"ROLE_Administrator", "ROLE_Moderator"})
    @QueryMapping
    public List<User> getAllAdministrators() {
        return userService.getAllAdministrators().stream().map(User::new).toList();
    }
}
