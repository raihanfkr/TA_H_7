package apap.ta.sipelatihan.controller;

import apap.ta.sipelatihan.model.JenisPelatihanModel;
import apap.ta.sipelatihan.rest.LaporanDetail;
import apap.ta.sipelatihan.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @Autowired
    TrainerService trainerService;

    @Autowired
    PesertaService pesertaService;

    @Autowired
    PelatihanService pelatihanService;

    @Autowired
    JenisPelatihanService jenisPelatihanService;

    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("trainer", trainerService.getTrainerList().size());
        model.addAttribute("peserta", pesertaService.getListPeserta().size());
        model.addAttribute("pelatihan", pelatihanService.getPelatihanList().size());
        model.addAttribute("jenisPelatihan", jenisPelatihanService.getJenisPelatihanList().size());

        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}

