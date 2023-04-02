package ru.ntdv.proiics.crud.service;

import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.MutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.web.Link;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;
import ru.ntdv.proiics.crud.model.Credentials;
import ru.ntdv.proiics.crud.model.User;
import ru.ntdv.proiics.crud.model.UserRole;
import ru.ntdv.proiics.crud.repository.CredentialsRepository;
import ru.ntdv.proiics.crud.repository.UserRepository;
import ru.ntdv.proiics.graphql.input.CredentialsInput;
import ru.ntdv.proiics.graphql.input.UserInput;

import javax.management.InstanceAlreadyExistsException;
import java.util.*;
import java.util.stream.Stream;

@Service
public class UserService {
    @Autowired
    private CredentialsRepository credentialsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<User> findUserById(final Long userId) {
        return userRepository.findById(userId);
    }

    public User getUserById(Long userId) throws EntityNotFoundException {
        return userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Пользователь не найден."));
    }

    public Credentials register(final CredentialsInput credentialsInput, final UserInput userInput, final UserRole.Role role) throws InstanceAlreadyExistsException {
        if (credentialsRepository.findByLogin(credentialsInput.getLogin()) != null) throw new InstanceAlreadyExistsException("Пользователь с такими данными уже существует");

        credentialsInput.setPassword(passwordEncoder.encode(credentialsInput.getPassword()));

        final var user = userRepository.saveAndFlush(new User(userInput));
        return credentialsRepository.saveAndFlush(new Credentials(credentialsInput, user, Collections.singleton(role.getUserRole())));
    }

    public List<Credentials> registerAll(final Collection<Triple<CredentialsInput, UserInput, UserRole.Role>> data) {
        final var users = new LinkedList<User>();
        data.forEach(tuple -> users.add(new User(tuple.getMiddle())));
        final var usersIterator = userRepository.saveAllAndFlush(users).iterator();

        final var credentials = new LinkedList<Credentials>();
        data.forEach(tuple -> {
            tuple.getLeft().setPassword(passwordEncoder.encode(tuple.getLeft().getPassword()));
            credentials.add(new Credentials(tuple.getLeft(), usersIterator.next(), Collections.singleton(tuple.getRight().getUserRole())));
        });
        return credentialsRepository.saveAllAndFlush(credentials);
    }


    public List<User> getAllParticipants() {
        return credentialsRepository
                .findAllByRolesContains(UserRole.Role.Participant.getUserRole())
                .stream()
                .map(Credentials::getUser)
                .toList();
    }

    public List<User> getAllAdministrators() {
        return credentialsRepository
                .findAllByRolesContains(UserRole.Role.Administrator.getUserRole())
                .stream()
                .map(Credentials::getUser)
                .toList();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
