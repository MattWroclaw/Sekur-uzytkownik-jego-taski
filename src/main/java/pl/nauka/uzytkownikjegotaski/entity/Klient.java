package pl.nauka.uzytkownikjegotaski.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "klienci")
public class Klient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String email;

    private String password;
}