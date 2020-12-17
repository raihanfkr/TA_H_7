package apap.ta.sipelatihan.controller;

import apap.ta.sipelatihan.model.PelatihanModel;
import apap.ta.sipelatihan.model.PesertaModel;
import apap.ta.sipelatihan.model.PesertaPelatihanModel;
import apap.ta.sipelatihan.model.UserModel;
import apap.ta.sipelatihan.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
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
    private PesertaPelatihanService pesertaPelatihanService;

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private UserService userService;

    @Autowired
    private PesertaService pesertaService;

    @GetMapping("/viewall")
    public String listPelatihan(Model model) {
        List<PelatihanModel> listPelatihan = pelatihanService.getPelatihanList();
        System.out.println(pelatihanService.getPelatihanList());
        System.out.println(pelatihanService.getPelatihanById(1).getTrainer().getNama_trainer());
        model.addAttribute("listPelatihan", listPelatihan);

        return "viewall-pelatihan";
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
            return "add-pelatihan";
        }
        else {
            if(pelatihan.getWaktu_mulai().after(pelatihan.getWaktu_selesai()) || pelatihan.getWaktu_mulai()
                    .equals(pelatihan.getWaktu_selesai())) {
                return "add-pelatihan";
            }
            else {
                UserModel pengaju = userService.findUser(auth.getName());
                pelatihan.setUserPengaju(pengaju);
                pelatihanService.addPelatihan(pelatihan);
            }
        }
        return "redirect:/viewall";
    }

    @GetMapping("/detail/{id}")
    public String viewDetailPelatihan(
            @PathVariable(value = "id") Integer id,
            Model model) {
        if (id == null) {
            return "error-page";
        }
        else{
            PelatihanModel pelatihan = pelatihanService.getPelatihanById(id);
            List<PesertaModel> listPeserta = pesertaService.getListPeserta();
            model.addAttribute("status", false);
            model.addAttribute("peserta_pelatihan", new PesertaPelatihanModel());
            model.addAttribute("pelatihan", pelatihan);
            model.addAttribute("listPeserta", listPeserta);
            model.addAttribute("listPesertaPelatihan", pelatihan.getListPesertaPelatihan());
            if (!(listPeserta.isEmpty())){
                model.addAttribute("title", "Daftar Peserta");
                model.addAttribute("status", true);
                return "view-pelatihan";
            }else{
                return "view-pelatihan";
            }
        }
    }

    @PostMapping("/{id}/tambah-peserta")
    private String addPesertaPelatihanFormSubmit(
            @PathVariable(value="id") Integer id,
            @ModelAttribute PesertaPelatihanModel peserta_pelatihan,
            Integer pesertaId,
            Model model){

        String no_peserta = String.valueOf(pesertaId);
        peserta_pelatihan.setNo_peserta(no_peserta);
        peserta_pelatihan.setPeserta(pesertaService.getPesertaByID(pesertaId));
        peserta_pelatihan.setPelatihan(pelatihanService.getPelatihanById(id));
        pesertaPelatihanService.addPesertaPelatihan(peserta_pelatihan);

        return "add-peserta-pelatihan";
    }
}

