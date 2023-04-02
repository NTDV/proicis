package ru.ntdv.proiics.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ntdv.proiics.crud.model.Credentials;
import ru.ntdv.proiics.crud.model.User;
import ru.ntdv.proiics.crud.model.UserRole;

import java.util.List;

@Repository
public interface CredentialsRepository extends JpaRepository<Credentials, Long> {
    Credentials findByLogin(final String login);
    Credentials findByUser(final User user);
    List<Credentials> findAllByRolesContains(final UserRole role);
    Credentials findFirstByLoginStartsWithOrderByIdDesc(final String login);
}
