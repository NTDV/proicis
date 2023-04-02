package ru.ntdv.proiics.graphql.input;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInput {
    private String firstName;
    private String secondName;
    private String thirdName;

    private String urlVkontakte;
    private String urlTelegram;

    private String group;
    private String organization;
}
