package pl.nauka.uzytkownikjegotaski.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.nauka.uzytkownikjegotaski.dto.KlientDto;
import pl.nauka.uzytkownikjegotaski.entity.Klient;
import pl.nauka.uzytkownikjegotaski.entity.KlientRepository;
import pl.nauka.uzytkownikjegotaski.service.KlientService;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class KlientController {

    private final KlientService klientService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showForm(Model model){
        model.addAttribute("formularz", new KlientDto());
        return "rejestracja";
    }

    @RequestMapping(value = "/adFormularz", method = RequestMethod.POST)
    public String processForm(@ModelAttribute KlientDto klientDto){
        klientService.createKlient(klientDto);
        return "redirect:login";
    }


// ***************metody do cruda Klienta*****************
//    1. Lista klientów
    @RequestMapping(value = "/klienci", method = RequestMethod.GET)
    public String goKlienci(Model model){
        List<Klient> klients = klientService.showAll();
        model.addAttribute("wszyscyKlienci", klients);
        return "klienci";
    }
//  2. kasowanie klienta
    @RequestMapping(value = "/deleteKlient/{id}", method = RequestMethod.GET)
    public String kasowanieKlienta(@PathVariable long id){
        klientService.deleteKlient(id);
        return "redirect:/klienci";
    }
//  3. edycja klienta
    @RequestMapping(value = "/editKlient/{id}", method = RequestMethod.GET)
    public String edycjaKlienta(@PathVariable("id")long id, Model model){
        Klient klientById = klientService.findKlientById(id);
        model.addAttribute("edytowany", klientById);
        return "edycjaKlienta";
    }

    @RequestMapping(value = "/updateKlient", method = RequestMethod.POST)
    public String formularzEdycji(@ModelAttribute Klient klientForm){
//        model.addAttribute("wyedytowany", klientForm);
//        List<Klient> klients = klientService.findAll();
//        model.addAttribute("wszyscyKlienci", klients);
        klientService.zapiszKlientaEncja(klientForm);

        return "redirect:/klienci";
    }


//  ***********  metoda do Home Page *************
    @RequestMapping(value = {"/", "/sklep"}, method = RequestMethod.GET)
    public String goSklep(){
        return "shop";
    }

}
