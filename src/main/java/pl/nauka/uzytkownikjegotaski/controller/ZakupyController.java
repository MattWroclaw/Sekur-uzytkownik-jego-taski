package pl.nauka.uzytkownikjegotaski.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.nauka.uzytkownikjegotaski.dto.ZakupDto;
import pl.nauka.uzytkownikjegotaski.entity.Klient;
import pl.nauka.uzytkownikjegotaski.entity.Zakup;
import pl.nauka.uzytkownikjegotaski.service.ZakupService;

import javax.jws.WebParam;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ZakupyController {
    private final ZakupService zakupService;

    @RequestMapping(value = "/zakupy" , method = RequestMethod.GET)
    public String goZakupy(Model model, Principal principal){
        List<ZakupDto> zakupLista = zakupService.findZakupByKlientEmail(principal.getName());
        model.addAttribute("listaZakupow", zakupLista);
        model.addAttribute("nowyZakup", new ZakupDto());
        return "buy";
    }

    @RequestMapping(value = "/formularzZakupowy", method = RequestMethod.POST)
    public String processFormular(@ModelAttribute ZakupDto zakupForm, Principal principal){
        zakupService.createZakup(zakupForm, principal.getName());
        return "redirect:/zakupy";
    }

    @GetMapping("/delete/{id}")
    public String deleteZakup(@PathVariable long id){
        zakupService.deleteZakup(id);
        return "redirect:/zakupy";
    }

    @GetMapping("/edit/{id}")
    public String editZakup(Model model, @PathVariable long id, Principal principal){
        Zakup edytowanyZakup = zakupService.zakupByIdAndmail(principal.getName(), id);
        model.addAttribute("edytowany" , edytowanyZakup);
        return "edit";
    }

    @PostMapping("/update/{id}")
    public String processEditZakup(@ModelAttribute Zakup edytowanyForm,
                                   Principal principal,
                                   Model model){

        zakupService.zapiszZakup(edytowanyForm);
        return "redirect:/zakupy";
    }
}
