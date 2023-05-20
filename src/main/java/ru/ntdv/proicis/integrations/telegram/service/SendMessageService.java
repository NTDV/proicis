package ru.ntdv.proicis.integrations.telegram.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.ntdv.proicis.integrations.telegram.ProicisTelegramBot;

@Service
@Slf4j
public
class SendMessageService {
@Autowired
private ProicisTelegramBot proicisTelegramBot;

public
void sendMessagesIgnoreExceptions(final Long chatId, final Iterable<String> messages) {
    messages.forEach(message -> sendMessage(chatId, message));
}

public
boolean sendMessage(final Long chatId, final String message) {
    if (chatId == null || message == null || message.isBlank()) return false;
    final var sendMessage = new SendMessage(chatId.toString(), message);
    try {
        proicisTelegramBot.execute(sendMessage);
        return true;
    } catch (final TelegramApiException e) {
        log.error(e.getMessage());
        return false;
    }
}
}
