package ru.ntdv.proicis.crud.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ntdv.proicis.crud.model.StageInfo;
import ru.ntdv.proicis.crud.repository.StageInfoRepository;
import ru.ntdv.proicis.graphql.input.StageInfoInput;

import java.util.List;

@Service
public
class StageInfoService {
@Autowired
private StageInfoRepository stageInfoRepository;

public
StageInfo getStageInfo(final Long stageInfoId) {
    return stageInfoRepository.findById(stageInfoId)
                              .orElseThrow(() -> new EntityNotFoundException("No such stage info found."));
}

public
List<StageInfo> getStageInfos() {
    return stageInfoRepository.findAll();
}

public
StageInfo createStageInfo(final StageInfoInput stageInfo) {
    return stageInfoRepository.save(new StageInfo(stageInfo));
}
}
