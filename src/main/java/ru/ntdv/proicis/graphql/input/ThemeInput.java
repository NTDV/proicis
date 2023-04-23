package ru.ntdv.proicis.graphql.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import ru.ntdv.proicis.buisness.model.Hardness;
import ru.ntdv.proicis.buisness.model.Skill;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public
class ThemeInput {
private String title;
private String description;
private MultipartFile presentationSlide;
private Hardness hardness;
private Set<Skill> skills;
private List<Long> seasons;
}
