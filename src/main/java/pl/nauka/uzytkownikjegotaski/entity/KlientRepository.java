package pl.nauka.uzytkownikjegotaski.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KlientRepository extends JpaRepository <Klient, Long> {

    public Optional <Klient> findByEmail(String email);
}
