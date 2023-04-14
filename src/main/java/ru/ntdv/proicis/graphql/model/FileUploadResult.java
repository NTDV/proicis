package ru.ntdv.proicis.graphql.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.ntdv.proicis.crud.model.File;

import java.util.UUID;

@Data
@AllArgsConstructor
public
class FileUploadResult {
private UUID id;

public
FileUploadResult(File file) {
    this.id = file.getId();
}
}
