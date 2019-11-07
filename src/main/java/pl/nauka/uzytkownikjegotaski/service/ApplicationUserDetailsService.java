package pl.nauka.uzytkownikjegotaski.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.nauka.uzytkownikjegotaski.entity.Klient;
import pl.nauka.uzytkownikjegotaski.entity.KlientRepository;

@Service
@RequiredArgsConstructor
public class ApplicationUserDetailsService implements UserDetailsService {

    private final KlientRepository klientRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Klient entity = klientRepository.findByEmail(email)
                .orElseThrow( ()-> new UsernameNotFoundException(email+" does not exist.."));
        String[] roles = {"USER", "ADMIN"};
        return org.springframework.security.core.userdetails.User
                .withUsername(entity.getEmail())
                .password(entity.getPassword())
                .roles(roles)
                .build();
    }
}
