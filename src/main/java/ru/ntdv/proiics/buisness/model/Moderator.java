package ru.ntdv.proiics.buisness.model;

import lombok.Getter;
import ru.ntdv.proiics.crud.model.User;
import ru.ntdv.proiics.integrations.telegram.TelegramUrl;
import ru.ntdv.proiics.integrations.vkontakte.VkontakteUrl;
import ru.ntdv.proiics.crud.model.UserRole;

@Getter
public class Moderator {
    private final Long id;

    private final Name name;
    private final VkontakteUrl vkontakteUrl;
    private final TelegramUrl telegramUrl;

    public Moderator(final User user) throws IllegalArgumentException {
        id = user.getId();
        name = new Name(user);
        vkontakteUrl = new VkontakteUrl(user);
        telegramUrl = new TelegramUrl(user);
    }
}
