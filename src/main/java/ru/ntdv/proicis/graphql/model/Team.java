package ru.ntdv.proicis.graphql.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.ntdv.proicis.constant.TeamState;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public
class Team {
private Long id;

private String title;
private Theme chosenTheme;
private User chosenMentor;

private Set<User> participants;
private List<Theme> preferThemes;
private TeamState state;

public
Team(final ru.ntdv.proicis.crud.model.Team dbTeam) {
    id = dbTeam.getId();
    title = dbTeam.getTitle();
    chosenTheme = dbTeam.getChosenTheme() == null ? null : new Theme(dbTeam.getChosenTheme());
    chosenMentor = dbTeam.getChosenMentor() == null ? null : new User(dbTeam.getChosenMentor());
    participants = dbTeam.getParticipants().stream().map(User::new).collect(Collectors.toSet());
    preferThemes = dbTeam.getPreferThemes().stream().map(Theme::new).collect(Collectors.toList());
    state = dbTeam.getState();
}
}
