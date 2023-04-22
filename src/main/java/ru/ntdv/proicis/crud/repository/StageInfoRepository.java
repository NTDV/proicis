package ru.ntdv.proicis.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ntdv.proicis.crud.model.StageInfo;

@Repository
public
interface StageInfoRepository extends JpaRepository<StageInfo, Long> {
}
