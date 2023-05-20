package ru.ntdv.proicis.integrations.telegram;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.ntdv.proicis.crud.service.SecretCodeService;
import ru.ntdv.proicis.crud.service.UserService;
import ru.ntdv.proicis.integrations.telegram.command.CommandContainer;
import ru.ntdv.proicis.integrations.telegram.service.SendMessageService;

import static ru.ntdv.proicis.integrations.telegram.command.CommandName.NO;

@Component
public
class ProicisTelegramBot extends TelegramLongPollingBot {
public static final String COMMAND_PREFIX = "/";
private final CommandContainer commandContainer;
private final SendMessageService sendMessageService;
@Value("${bot.name}")
private String botUsername;

@Autowired
ProicisTelegramBot(final UserService userService, final SecretCodeService secretCodeService,
                   @Value("${bot.token}") final String botToken) {
    super(botToken);
    this.sendMessageService = new SendMessageService(this);
    this.commandContainer = new CommandContainer(sendMessageService, userService, secretCodeService);
}

public
void sendMessages(final Long chatId, final Iterable<String> messages) {
    sendMessageService.sendMessages(chatId, messages);
}

public
boolean sendMessage(final Long chatId, final String message) {
    return sendMessageService.sendMessage(chatId, message);
}

@Override
public
void onUpdateReceived(final Update update) {
    if (update.hasMessage() && update.getMessage().hasText()) {
        val message = update.getMessage().getText().trim();
        if (message.startsWith(COMMAND_PREFIX)) {
            val commandIdentifier = message.split(" ")[0].toLowerCase();
            commandContainer.retrieveCommand(commandIdentifier).execute(update);
        } else {
            commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
        }
    }
}

@Override
public
String getBotUsername() {
    return botUsername;
}
}
