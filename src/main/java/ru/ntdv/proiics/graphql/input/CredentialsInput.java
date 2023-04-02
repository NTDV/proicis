package ru.ntdv.proiics.graphql.input;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CredentialsInput {
    private String login;
    private String password;
}
