package pl.nauka.uzytkownikjegotaski.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "zakup")
public class Zakup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String opis;


    @ManyToOne
    @JoinColumn(name = "klient_id")
    private Klient klient;
}
