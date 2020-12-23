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

import java.util.ArrayList;
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
            model.addAttribute("failed", "Tanggal yang dimasukkan salah");
            model.addAttribute("listJenisPelatihan", jenisPelatihanService.getJenisPelatihanList());
            model.addAttribute("listTrainer", trainerService.getTrainerList());
            return "form-add-pelatihan";
        }
        else {
            if(pelatihan.getWaktu_mulai().after(pelatihan.getWaktu_selesai()) || pelatihan.getWaktu_mulai()
                    .equals(pelatihan.getWaktu_selesai())) {
                model.addAttribute("failed", "Waktu yang dimasukkan salah");
                model.addAttribute("listJenisPelatihan", jenisPelatihanService.getJenisPelatihanList());
                model.addAttribute("listTrainer", trainerService.getTrainerList());
                return "form-add-pelatihan";
            }
            else {
                UserModel pengaju = userService.findUser(auth.getName());
                pelatihan.setUserPengaju(pengaju);
                pelatihanService.addPelatihan(pelatihan);
            }
        }
        return "redirect:/pelatihan/viewall";
    }

    @GetMapping("/ubah/{id}")
    public String updatePelatihan(
            @PathVariable Integer id,
            Model model) {
        PelatihanModel pelatihan = pelatihanService.getPelatihanById(id);
        model.addAttribute("pelatihan", pelatihan);
        model.addAttribute("listJenisPelatihan", jenisPelatihanService.getJenisPelatihanList());
        model.addAttribute("listTrainer", trainerService.getTrainerList());

        return "form-update-pelatihan";
    }

    @PostMapping("/ubah")
    public String updatePelatihanSubmit(
          @ModelAttribute("pelatihan") PelatihanModel pelatihan,
          Authentication auth,
          Model model
    ) {
        if (pelatihan.getTanggal_mulai().after(pelatihan.getTanggal_selesai())) {
            model.addAttribute("failed", "Tanggal yang dimasukkan salah");
            model.addAttribute("listJenisPelatihan", jenisPelatihanService.getJenisPelatihanList());
            model.addAttribute("listTrainer", trainerService.getTrainerList());
            return "form-update-pelatihan";
        }
        else {
            if(pelatihan.getWaktu_mulai().after(pelatihan.getWaktu_selesai()) || pelatihan.getWaktu_mulai()
                    .equals(pelatihan.getWaktu_selesai())) {
                model.addAttribute("failed", "Waktu yang dimasukkan salah");
                model.addAttribute("listJenisPelatihan", jenisPelatihanService.getJenisPelatihanList());
                model.addAttribute("listTrainer", trainerService.getTrainerList());
                return "form-update-pelatihan";
            }
            else {
                UserModel pengaju = userService.findUser(auth.getName());
                pelatihan.setUserPengaju(pengaju);
                pelatihanService.updatePelatihan(pelatihan);
            }
        }
        return "redirect:/pelatihan/viewall";
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

            List<PesertaPelatihanModel> listPesertaPelatihan = pelatihan.getListPesertaPelatihan();

            model.addAttribute("listPesertaPelatihan", listPesertaPelatihan);
            model.addAttribute("peserta_pelatihan", new PesertaPelatihanModel());
            model.addAttribute("pelatihan", pelatihan);
            model.addAttribute("listPeserta", listPeserta);

//            if (listPesertaPelatihan.size() != 0){
//                model.addAttribute("status",true);
//            }else{
//                model.addAttribute("status", false);
//            }
            return "view-pelatihan";
        }
    }

    @PostMapping("/{id}/tambah-peserta")
    private String addPesertaPelatihanFormSubmit(
            @PathVariable(value="id") Integer id,
            @ModelAttribute PesertaPelatihanModel peserta_pelatihan,
            Integer pesertaId,
            Model model){

        PelatihanModel p = pelatihanService.getPelatihanById(id);
        model.addAttribute("pelatihan", p);
        model.addAttribute("peserta_pelatihan", peserta_pelatihan);

        List<PesertaModel> listPeserta = pesertaService.getListPeserta();
        model.addAttribute("listPeserta", listPeserta);

        List<PesertaPelatihanModel> listPesertaPelatihan = p.getListPesertaPelatihan();
        ArrayList<String> nama = new ArrayList<>();
        for (PesertaPelatihanModel pp : listPesertaPelatihan){
            nama.add(pp.getPeserta().getNama());
        }
        model.addAttribute("listPesertaPelatihan", listPesertaPelatihan);

        if (pesertaService.getPesertaByID(pesertaId) != null){
            PesertaModel pesertaNama = pesertaService.getPesertaByID(pesertaId);
            if (nama.contains(pesertaNama.getNama())){
                model.addAttribute("gagal", "Peserta sudah terdaftar");
                model.addAttribute("listPesertaPelatihan", listPesertaPelatihan);
                return "view-pelatihan";
            }
        }

        String no_peserta = String.valueOf(pesertaId);
        peserta_pelatihan.setNo_peserta(no_peserta);
        peserta_pelatihan.setPeserta(pesertaService.getPesertaByID(pesertaId));
        peserta_pelatihan.setPelatihan(pelatihanService.getPelatihanById(id));
        pesertaPelatihanService.addPesertaPelatihan(peserta_pelatihan);
        model.addAttribute("listPesertaPelatihan", listPesertaPelatihan);
        return "redirect:/pelatihan/detail/{id}";
    }
}

