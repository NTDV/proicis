package ru.ntdv.proicis.graphql.input;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public class TeamInput {
    private String title;
    private Set<Long> participants;
    private List<Long> preferThemes;
}
