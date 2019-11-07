package pl.nauka.uzytkownikjegotaski.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.nauka.uzytkownikjegotaski.dto.KlientDto;
import pl.nauka.uzytkownikjegotaski.entity.KlientRepository;
import pl.nauka.uzytkownikjegotaski.service.KlientService;

import java.security.Principal;

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

    @RequestMapping(value = {"/", "/sklep"}, method = RequestMethod.GET)
    public String goSklep(){
        return "shop";
    }

}
