package ru.ntdv.proicis.buisness.model;

import lombok.Getter;
import ru.ntdv.proicis.crud.model.User;
import ru.ntdv.proicis.integrations.mephi.StudentGroup;
import ru.ntdv.proicis.integrations.telegram.TelegramUrl;
import ru.ntdv.proicis.integrations.vkontakte.VkontakteUrl;

@Getter
public class Participant {
    private final Long id;

    private final Name name;
    private final VkontakteUrl vkontakteUrl;
    private final TelegramUrl telegramUrl;
    private final StudentGroup group;

    public Participant(final User user) throws IllegalArgumentException {
        id = user.getId();
        name = new Name(user);
        vkontakteUrl = new VkontakteUrl(user);
        telegramUrl = new TelegramUrl(user);
        group = new StudentGroup(user);
    }
}
