package ru.ntdv.proicis.graphql.input;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
public
class SeasonInput {
private String title;
private OffsetDateTime start;
private OffsetDateTime end;
}