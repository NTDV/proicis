package ru.ntdv.proicis.graphql.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.ntdv.proicis.constant.Stage;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
public
class StageInfoInput {
private String title;
private Stage stage;
private OffsetDateTime start;
private OffsetDateTime end;
}
