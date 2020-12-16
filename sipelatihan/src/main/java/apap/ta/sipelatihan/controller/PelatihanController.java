package apap.ta.sipelatihan.controller;

import apap.ta.sipelatihan.model.PelatihanModel;
import apap.ta.sipelatihan.model.PesertaPelatihanModel;
import apap.ta.sipelatihan.model.UserModel;
import apap.ta.sipelatihan.service.JenisPelatihanService;
import apap.ta.sipelatihan.service.TrainerService;
import apap.ta.sipelatihan.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import apap.ta.sipelatihan.service.PelatihanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/pelatihan")
public class PelatihanController {
    @Autowired
    private PelatihanService pelatihanService;

    @Autowired
    private JenisPelatihanService jenisPelatihanService;

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private UserService userService;
//
//    @Autowired
//    private PesertaService pesertaService;

    @GetMapping("/viewall")
    public String listPelatihan(Model model) {
        List<PelatihanModel> listPelatihan = pelatihanService.getPelatihanList();
        System.out.println(pelatihanService.getPelatihanList());
        model.addAttribute("listPelatihan", listPelatihan);

        return "viewall-pelatihan";
    }

    @GetMapping("/view/{id}")
    public String viewDetailPelatihan(
            @PathVariable Integer id, Model model) {

        PelatihanModel pelatihan = pelatihanService.getPelatihanById(id);
        List<PesertaPelatihanModel> listPeserta = pelatihan.getListPesertaPelatihan();
        if (!(listPeserta.isEmpty())){
            model.addAttribute("title", "Daftar Peserta");
            model.addAttribute("status", true);


        }else{
            model.addAttribute("status", false);

        }
        model.addAttribute("pelatihan", pelatihan);
        model.addAttribute("listPeserta", listPeserta);


        return "/pelatihan/view-pelatihan";


    }

    @GetMapping("/add")
    public String addPelatihan(Model model) {
        model.addAttribute("pelatihan", new PelatihanModel());
        model.addAttribute("listJenisPelatihan", jenisPelatihanService.getJenisPelatihanList());
        model.addAttribute("listTrainer", trainerService.getTrainerList());

        return "form-add-pelatihan";
    }

    @PostMapping("/add")
    public String addPelatihanSubmit(@ModelAttribute("pelatihan") PelatihanModel pelatihan,
                                     Authentication auth,
                                     Model model
    ) {
        if (pelatihan.getTanggal_mulai().after(pelatihan.getTanggal_selesai())) {
            return "add-trainer";
        }
        else {
            if(pelatihan.getWaktu_mulai().after(pelatihan.getWaktu_selesai()) || pelatihan.getWaktu_mulai()
                    .equals(pelatihan.getWaktu_selesai())) {
                return "add-trainer";
            }
            else {
                UserModel pengaju = userService.findUser(auth.getName());
                pelatihan.setUserPengaju(pengaju);
                pelatihanService.addPelatihan(pelatihan);
            }
        }
        return "viewall-pelatihan";
    }
}

