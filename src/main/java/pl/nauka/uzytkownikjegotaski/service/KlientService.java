package pl.nauka.uzytkownikjegotaski.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.nauka.uzytkownikjegotaski.dto.KlientDto;
import pl.nauka.uzytkownikjegotaski.entity.Klient;
import pl.nauka.uzytkownikjegotaski.entity.KlientRepository;

@Service
@RequiredArgsConstructor
public class KlientService {
    private final KlientRepository klientRepository;
    private final PasswordEncoder passwordEncoder;

    public void createKlient(KlientDto klientDto){
        Klient entity = new Klient();
        entity.setEmail(klientDto.getEmail());
//        entity.setName(klientDto.getName());

        String encode = passwordEncoder.encode(klientDto.getPassword());
        entity.setPassword(encode);

        klientRepository.save(entity);
    }

}
