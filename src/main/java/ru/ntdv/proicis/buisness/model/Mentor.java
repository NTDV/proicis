package ru.ntdv.proicis.buisness.model;

import lombok.Getter;
import ru.ntdv.proicis.crud.model.User;
import ru.ntdv.proicis.integrations.telegram.TelegramUrl;
import ru.ntdv.proicis.integrations.vkontakte.VkontakteUrl;

@Getter
public class Mentor {
    private final Long id;

    private final Name name;
    private final VkontakteUrl vkontakteUrl;
    private final TelegramUrl telegramUrl;
    private final Organization organization;

    public Mentor(final User user) throws IllegalArgumentException {
        id = user.getId();
        name = new Name(user);
        vkontakteUrl = new VkontakteUrl(user);
        telegramUrl = new TelegramUrl(user);
        organization = new Organization(user);
    }
}
