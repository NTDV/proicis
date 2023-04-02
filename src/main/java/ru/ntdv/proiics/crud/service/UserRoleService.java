package ru.ntdv.proiics.crud.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ntdv.proiics.crud.model.Credentials;
import ru.ntdv.proiics.crud.model.User;
import ru.ntdv.proiics.crud.model.UserRole;
import ru.ntdv.proiics.crud.repository.CredentialsRepository;
import ru.ntdv.proiics.crud.repository.UserRepository;
import ru.ntdv.proiics.crud.repository.UserRoleRepository;
import ru.ntdv.proiics.graphql.input.CredentialsInput;
import ru.ntdv.proiics.graphql.input.UserInput;

import javax.management.InstanceAlreadyExistsException;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private CredentialsRepository credentialsRepository;

    public UserRole.Role getUserRole(final User user) throws NoSuchElementException {
        return UserRole.Role.getFrom(credentialsRepository.findByUser(user).getRoles().stream().findFirst().orElseThrow());
    }

    public UserRole findRoleById(final Long roleId) throws EntityNotFoundException {
        return userRoleRepository.findById(roleId).orElseThrow(() -> new EntityNotFoundException("Права не найдены."));
    }

    public void persistRoles() {
        for (final UserRole.Role role : UserRole.Role.values()) {
            if (role.getUserRole() == null) {
                final UserRole userRole;
                if ((userRole = userRoleRepository.findByName(role.getName())) != null) role.setUserRole(userRole);
                else role.setUserRole(userRoleRepository.saveAndFlush(new UserRole(role.getName())));
            }
        }
    }
}
