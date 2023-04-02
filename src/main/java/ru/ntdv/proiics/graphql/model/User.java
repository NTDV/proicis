package ru.ntdv.proiics.graphql.model;

import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import ru.ntdv.proiics.crud.model.UserRole;
import ru.ntdv.proiics.crud.service.UserRoleService;

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

    public User(final ru.ntdv.proiics.crud.model.User dbUser) {
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
