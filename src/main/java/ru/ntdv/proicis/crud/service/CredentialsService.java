package ru.ntdv.proicis.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ntdv.proicis.crud.repository.CredentialsRepository;
import ru.ntdv.proicis.crud.model.Credentials;
import ru.ntdv.proicis.crud.model.UserRole;

import java.util.List;

@Service
public class CredentialsService implements UserDetailsService {
    @Autowired
    private CredentialsRepository credentialsRepository;

    @Override
    public Credentials loadUserByUsername(final String login) throws UsernameNotFoundException {
        final var credentials = credentialsRepository.findByLogin(login);
        if (credentials == null) throw new UsernameNotFoundException("Данные пользователя не найдены.");
        return credentials;
    }

    public List<Credentials> findAllByRolesContains(final UserRole.Role role) {
        return credentialsRepository.findAllByRolesContains(role.getUserRole());
    }

    public String getLatestPostfix(final String login) {
        final var latestCredentials = credentialsRepository.findFirstByLoginStartsWithOrderByIdDesc(login);
        if (latestCredentials == null) return "";
        else if (latestCredentials.getLogin().length() == login.length()) return "1";
        else return String.valueOf(Long.parseLong(latestCredentials.getLogin().substring(login.length())) + 1);
    }
}
