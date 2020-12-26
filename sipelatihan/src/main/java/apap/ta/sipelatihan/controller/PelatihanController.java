package apap.ta.sipelatihan.controller;

import apap.ta.sipelatihan.model.PelatihanModel;
import apap.ta.sipelatihan.model.PesertaModel;
import apap.ta.sipelatihan.model.PesertaPelatihanModel;
import apap.ta.sipelatihan.model.UserModel;
import apap.ta.sipelatihan.rest.BaseResponse;
import apap.ta.sipelatihan.rest.PegawaiDTO;
import apap.ta.sipelatihan.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private UserRestService userRestService;

    @GetMapping("/viewall")
    public String listPelatihan(Model model) {
        List<PelatihanModel> listPelatihan = pelatihanService.getPelatihanList();
        model.addAttribute("listPelatihan", listPelatihan);
        UserModel user = userService.findUser(SecurityContextHolder.getContext().getAuthentication().getName());
        BaseResponse baseResponse = userRestService.getPegawai(user.getUsername());
        PegawaiDTO pegawai = baseResponse.getResult();
        model.addAttribute("pegawai", pegawai);

        return "viewall-pelatihan";
    }

    @GetMapping("/add")
    public String addPelatihan(Model model) {
        model.addAttribute("pelatihan", new PelatihanModel());
        model.addAttribute("listJenisPelatihan", jenisPelatihanService.getJenisPelatihanList());
        model.addAttribute("listTrainer", trainerService.getTrainerList());
        UserModel user = userService.findUser(SecurityContextHolder.getContext().getAuthentication().getName());
        BaseResponse baseResponse = userRestService.getPegawai(user.getUsername());
        PegawaiDTO pegawai = baseResponse.getResult();
        model.addAttribute("pegawai", pegawai);

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
        UserModel user = userService.findUser(SecurityContextHolder.getContext().getAuthentication().getName());
        BaseResponse baseResponse = userRestService.getPegawai(user.getUsername());
        PegawaiDTO pegawai = baseResponse.getResult();
        model.addAttribute("pegawai", pegawai);
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

        UserModel user = userService.findUser(SecurityContextHolder.getContext().getAuthentication().getName());
        BaseResponse baseResponse = userRestService.getPegawai(user.getUsername());
        PegawaiDTO pegawai = baseResponse.getResult();
        model.addAttribute("pegawai", pegawai);
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

        UserModel user = userService.findUser(SecurityContextHolder.getContext().getAuthentication().getName());
        BaseResponse baseResponse = userRestService.getPegawai(user.getUsername());
        PegawaiDTO pegawai = baseResponse.getResult();
        model.addAttribute("pegawai", pegawai);
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

            UserModel user = userService.findUser(SecurityContextHolder.getContext().getAuthentication().getName());
            BaseResponse baseResponse = userRestService.getPegawai(user.getUsername());
            PegawaiDTO pegawai = baseResponse.getResult();
            model.addAttribute("pegawai", pegawai);
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
        UserModel user = userService.findUser(SecurityContextHolder.getContext().getAuthentication().getName());
        BaseResponse baseResponse = userRestService.getPegawai(user.getUsername());
        PegawaiDTO pegawai = baseResponse.getResult();
        model.addAttribute("pegawai", pegawai);
        return "redirect:/pelatihan/detail/{id}";
    }

    @GetMapping("/update-status/{id}")
    public String updateStatusPelatihanForm(
            @PathVariable Integer id,
            Model model
    ){
        PelatihanModel pelatihan = pelatihanService.getPelatihanById(id);
        model.addAttribute("pelatihan", pelatihan);
        return "form-update-status-pelatihan";
    }

    @PostMapping("/update-status")
    public String updateStatusPelatihanSubmit(
            @ModelAttribute PelatihanModel pelatihan,
            Model model
    ){
        String username = SecurityContextHolder.getContext().getAuthentication().getName().toString();
        pelatihan.setUserPenyetuju(userService.findUser(username));
        PelatihanModel pelatihanUpdated = pelatihanService.updateStatusPelatihan(pelatihan);

        List<PesertaModel> listPeserta = pesertaService.getListPeserta();
        List<PesertaPelatihanModel> listPesertaPelatihan = pelatihanUpdated.getListPesertaPelatihan();

        model.addAttribute("pelatihan", pelatihanUpdated);
        model.addAttribute("msg", "Status pelatihan berhasil diubah");

        model.addAttribute("listPesertaPelatihan", listPesertaPelatihan);
        model.addAttribute("peserta_pelatihan", new PesertaPelatihanModel());
        model.addAttribute("listPeserta", listPeserta);

        UserModel user = userService.findUser(SecurityContextHolder.getContext().getAuthentication().getName());
        BaseResponse baseResponse = userRestService.getPegawai(user.getUsername());
        PegawaiDTO pegawai = baseResponse.getResult();
        model.addAttribute("pegawai", pegawai);

        return "view-pelatihan";
    }
}

