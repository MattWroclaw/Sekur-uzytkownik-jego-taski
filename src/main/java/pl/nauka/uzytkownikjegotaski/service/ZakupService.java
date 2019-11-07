package pl.nauka.uzytkownikjegotaski.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.nauka.uzytkownikjegotaski.dto.ZakupDto;
import pl.nauka.uzytkownikjegotaski.entity.Klient;
import pl.nauka.uzytkownikjegotaski.entity.KlientRepository;
import pl.nauka.uzytkownikjegotaski.entity.Zakup;
import pl.nauka.uzytkownikjegotaski.entity.ZakupRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ZakupService {
    private final ZakupRepository zakupRepository;
    private final KlientRepository klientRepository;

    public List<ZakupDto>  findZakupByKlientEmail(String klientEmail){
       return zakupRepository.findAllByKlient_Email(klientEmail)
                .stream()
                .map( zakupEncja->{
                    ZakupDto dto = new ZakupDto();
                    dto.setOpis(zakupEncja.getOpis());
                    return dto;
                }).collect(Collectors.toList());
    }

    public void createZakup(ZakupDto dto, String  userEmail){
       Klient klientByEmail = klientRepository.findByEmail(userEmail)
               .orElseThrow( ()-> new NoSuchElementException(userEmail + " nie widnieje w bazie"));
       Zakup encja = new Zakup();
       encja.setOpis(dto.getOpis());
       encja.setKlient(klientByEmail);

       zakupRepository.save(encja);
    }

    public void deleteZakup(long id){
        zakupRepository.deleteById(id);
    }

    public void editZakup(Zakup zakup){
        Zakup encja = zakupRepository.findById(zakup.getId())
                .orElseThrow( ()-> new NoSuchElementException( " nie widnieje w bazie"));
        encja.setOpis(zakup.getOpis());

    }

    public void edycjaZakupu(long id){
        Zakup encjaDoEdycji = zakupRepository.findById(id)
                .orElseThrow( ()-> new NoSuchElementException( " nie widnieje w bazie"));
//        encjaDoEdycji.setOpis();
//        TODO sprawdzić czy można przez id..
    }

    public void zapiszZakup(Zakup zakup){
        Zakup encja = new Zakup();
        encja.setId(zakup.getId());
        encja.setOpis(zakup.getOpis());
        encja.setKlient(zakup.getKlient());
        zakupRepository.save(encja);
    }

    public Zakup zakupByIdAndmail(String email, long id){
       return zakupRepository.findByKlient_EmailAndAndId(email, id)
               .orElseThrow( ()-> new NoSuchElementException("nie ma takiego elementu"));
    }
}
