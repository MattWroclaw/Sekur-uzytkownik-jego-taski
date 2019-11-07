package pl.nauka.uzytkownikjegotaski.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ZakupRepository extends JpaRepository<Zakup, Long> {
    List<Zakup> findAllByKlient_Email(String email);
    Optional<Zakup> findByOpis(String opis);
    Optional<Zakup> findByKlient_EmailAndAndId(String email, long id);
}
