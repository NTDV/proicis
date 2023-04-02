package ru.ntdv.proicis.graphql.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private Long id;

    private String firstName;
    private String secondName;
    private String thirdName;

    private String urlVkontakte;
    private String urlTelegram;

    private String group;
    private String organization;

    public User(final ru.ntdv.proicis.crud.model.User dbUser) {
        id = dbUser.getId();
        firstName = dbUser.getFirstName();
        secondName = dbUser.getSecondName();
        thirdName = dbUser.getThirdName();
        urlVkontakte = dbUser.getUrlVkontakte();
        urlTelegram = dbUser.getUrlTelegram();
        group = dbUser.getGroup();
        organization = dbUser.getOrganization();
    }
}
