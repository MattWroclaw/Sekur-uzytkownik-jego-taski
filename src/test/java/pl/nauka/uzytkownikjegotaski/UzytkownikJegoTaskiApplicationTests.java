package pl.nauka.uzytkownikjegotaski;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class UzytkownikJegoTaskiApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void jakDzilaPasswordEncoder(){
        String pass = "a";
        PasswordEncoder delegatingPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        System.out.printf("to jest zakodowane haslo a: ", delegatingPasswordEncoder.encode(pass));
    }


}
