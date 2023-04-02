package ru.ntdv.proiics.crud.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.ntdv.proiics.constant.UserState;
import ru.ntdv.proiics.graphql.input.CredentialsInput;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "credentials")
public class Credentials implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private User user;
    private String login;
    private String secret;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserRole> roles;

    public Credentials(final CredentialsInput credentialsInput, final User user, final Set<UserRole> roles) {
        this.user = user;
        this.login = credentialsInput.getLogin();
        this.secret = credentialsInput.getPassword();
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return getSecret();
    }

    @Override
    public String getUsername() {
        return getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        final var state = user.getState();
        return state != UserState.Banned && state != UserState.Deleted;
    }
}
