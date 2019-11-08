package pl.nauka.uzytkownikjegotaski.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.nauka.uzytkownikjegotaski.dto.KlientDto;
import pl.nauka.uzytkownikjegotaski.entity.Klient;
import pl.nauka.uzytkownikjegotaski.entity.KlientRepository;

import java.util.List;
import java.util.NoSuchElementException;

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

    public Klient findKlientByEmain(String email){
       return klientRepository.findByEmail(email)
               .orElseThrow( ()->new NoSuchElementException("nie ma takiego klienta: "+email));
    }
//  metody do cruda Klienta

    public List<Klient> showAll(){
        return klientRepository.findAll();
    }

    public void deleteKlient(long id){
        klientRepository.deleteById(id);
    }

    public Klient findKlientById(long id){
        Klient szukanyKlient = klientRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Nie ma klienta o id: "+ id));
        return szukanyKlient;
    }

    public Klient zapiszKlienta(KlientDto klientDto, Long id){
        Klient encja = klientRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Nie ma klienta o id: "+id));
        encja.setName(klientDto.getName());
        encja.setEmail(klientDto.getEmail());
        return encja;
    }

    public Klient zapiszKlientaEncja(Klient klientEdytowany){
        Klient encja = klientRepository.findById(klientEdytowany.getId())
                .orElseThrow(()-> new NoSuchElementException("Nie ma klienta o id: "+klientEdytowany.getId()
                +klientEdytowany.getPassword()));
        encja.setEmail(klientEdytowany.getEmail());
        encja.setName(klientEdytowany.getName());
        encja.setPassword(klientEdytowany.getPassword());
        klientRepository.save(encja);
        return encja;
    }

    public List<Klient> findAll(){
        return klientRepository.findAll();
    }
}
