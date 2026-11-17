package kz.iitu.springlab.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {

    private final AppUserRepository repo;

    public DatabaseUserDetailsService(AppUserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        var authorities = appUser.getRoles().stream()
                .map(r -> r.startsWith("ROLE_") ? r : "ROLE_" + r)
                .map(SimpleGrantedAuthority::new)
                .toList();

        return User.withUsername(appUser.getUsername())
                .password(appUser.getPasswordHash())
                .authorities(authorities)
                .disabled(!appUser.isEnabled())
                .build();
    }
}