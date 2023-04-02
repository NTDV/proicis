package ru.ntdv.proicis.crud.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ntdv.proicis.graphql.input.UserInput;
import ru.ntdv.proicis.constant.UserState;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UserState state;
    private String firstName;
    private String secondName;
    private String thirdName;

    private String urlVkontakte;
    private String urlTelegram;

    @Column(name = "user_group")
    private String group;
    private String organization;

    public User(final UserInput userInput) {
        //state = UserState.Unconfirmed;
        state = UserState.Confirmed;
        firstName = userInput.getFirstName();
        secondName = userInput.getSecondName();
        thirdName = userInput.getThirdName();
        urlVkontakte = userInput.getUrlVkontakte();
        urlTelegram = userInput.getUrlTelegram();
        group = userInput.getGroup();
        organization = userInput.getOrganization();
    }
}
