package ru.ntdv.proiics.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ntdv.proiics.crud.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
