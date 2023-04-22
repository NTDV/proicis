package ru.ntdv.proicis.crud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ntdv.proicis.constant.Stage;
import ru.ntdv.proicis.graphql.input.StageInfoInput;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stage_info")
public
class StageInfo {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String title;
private Stage stage;
private OffsetDateTime start;
@Column(name = "stage_end")
private OffsetDateTime end;

public
StageInfo(final StageInfoInput stageInfo) {
    this.title = stageInfo.getTitle();
    this.stage = stageInfo.getStage();
    this.start = stageInfo.getStart();
    this.end = stageInfo.getEnd();
}
}
