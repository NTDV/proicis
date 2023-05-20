package ru.ntdv.proicis.graphql.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import ru.ntdv.proicis.crud.service.UserService;
import ru.ntdv.proicis.integrations.telegram.service.SendMessageService;

@Validated
@Controller
@Slf4j
public
class TelegramController {
@Autowired
private SendMessageService sendMessageService;
@Autowired
private UserService userService;

@Secured({ "ROLE_Administrator", "ROLE_Moderator" })
@QueryMapping
public
Boolean sendTelegramMessage(@Argument @Valid final Long userId, @Argument @Valid final String message) {
    return userService.findUserById(userId)
                      .filter(user -> sendMessageService.sendMessage(user.getTelegramChatId(), message))
                      .isPresent();
}
}