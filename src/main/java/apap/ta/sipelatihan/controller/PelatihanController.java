package apap.ta.sipelatihan.controller;

import apap.ta.sipelatihan.model.PelatihanModel;
import apap.ta.sipelatihan.model.PesertaModel;
import apap.ta.sipelatihan.model.PesertaPelatihanModel;
import apap.ta.sipelatihan.model.UserModel;
import apap.ta.sipelatihan.repository.PesertaPelatihanDb;
import apap.ta.sipelatihan.rest.BaseResponseKaryawan;
import apap.ta.sipelatihan.rest.KaryawanDetail;
import apap.ta.sipelatihan.rest.ListPesertaDetail;
import apap.ta.sipelatihan.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private KaryawanRestService karyawanRestService;

    @Autowired
    private PesertaPelatihanDb pesertaPelatihanDb;

    @GetMapping("/")
    public String listPelatihan(
            Authentication auth,
            Model model) {
        UserModel user = userService.findUser(auth.getName());
        if (user.getRole().getId_role() == 1 || user.getRole().getId_role() == 2) {
            List<PelatihanModel> listPelatihan = pelatihanService.getPelatihanList();
            model.addAttribute("listPelatihan", listPelatihan);
        } else {
            List<PelatihanModel> listPelatihan = pelatihanService.getPelatihanListPengaju(user);
            model.addAttribute("listPelatihan", listPelatihan);
        }
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
        return "berhasil";
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

    @PostMapping("/ubah/{id}")
    public String updatePelatihanSubmit(
            @PathVariable("id") Integer id,
            @ModelAttribute("pelatihan") PelatihanModel pelatihan,
            Authentication auth,
            Model model
    ) {
        PelatihanModel p = pelatihanService.getPelatihanById(id);
        List<PesertaPelatihanModel> listPesertaPelatihan = p.getListPesertaPelatihan();
        System.out.println(listPesertaPelatihan.size());

        if (listPesertaPelatihan != null) {
            if (listPesertaPelatihan.size() > pelatihan.getKapasitas()) {
                model.addAttribute("failed", "Kapasitas tidak boleh kurang dari jumlah peserta terdaftar");
                model.addAttribute("listJenisPelatihan", jenisPelatihanService.getJenisPelatihanList());
                model.addAttribute("listTrainer", trainerService.getTrainerList());
                return "form-update-pelatihan";
            } else {
                UserModel pengaju = userService.findUser(auth.getName());
                pelatihan.setUserPengaju(pengaju);
                pelatihanService.updatePelatihan(pelatihan);
            }
        } else {
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
                    System.out.println("ah");
                    UserModel pengaju = userService.findUser(auth.getName());
                    pelatihan.setUserPengaju(pengaju);
                    pelatihanService.updatePelatihan(pelatihan);
                }
            }
        }
        return "berhasil";
    }

    @GetMapping("/detail/{id}")
    public String viewDetailPelatihan(
            @PathVariable(value = "id") Integer id,
            Model model) {

        PelatihanModel pelatihan = pelatihanService.getPelatihanById(id);

        List<PesertaModel> listPeserta = pesertaService.getListPeserta();

        List<PesertaPelatihanModel> listPesertaPelatihan = pelatihan.getListPesertaPelatihan();

        model.addAttribute("listPesertaPelatihan", listPesertaPelatihan);
        model.addAttribute("peserta_pelatihan", new PesertaPelatihanModel());
        model.addAttribute("pelatihan", pelatihan);
        model.addAttribute("listPeserta", listPeserta);

        return "view-pelatihan";
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

        return "berhasil";
    }

    @GetMapping("/hapus/{id}")
    public String deletePesawat(
            @PathVariable(value = "id") Integer id,
            Model model){
        PelatihanModel pelatihan = pelatihanService.getPelatihanById(id);

        if (pelatihan.getStatus_persetujuan() == 2){
            model.addAttribute("msg", "Pelatihan gagal dihapus!");
            return "gagal";
        }else{
            pelatihanService.deletePelatihan(id);
        }
        return "berhasil";
    }

    @GetMapping(value = "/tambah-peserta/{id}")
    private String TambahPesertaFormPage(
            @PathVariable("id") Integer id,
            Model model){

        PelatihanModel pelatihanModel = new PelatihanModel();
        PelatihanModel pelatihan = pelatihanService.getPelatihanById(id);

        List<PesertaModel> pesertaBaru = pesertaService.getListPesertaBaru(pelatihan);

        List<PesertaPelatihanModel> temp = new ArrayList<>();
        String listId = "";
        for(PesertaModel a : pesertaBaru){
            PesertaPelatihanModel dummy = new PesertaPelatihanModel();
            dummy.setPeserta(a);
            dummy.setNo_peserta("fikriansyah");
            dummy.setPelatihan(pelatihan);

            PesertaPelatihanModel p = pesertaPelatihanService.addPesertaPelatihan(dummy);

            temp.add(p);
            listId = listId + p.getId() + " ";
        }

        System.out.println(listId);
        pelatihan.getListPesertaPelatihan().clear();
        model.addAttribute("listId", listId);

        int jumlahPesertaTerdaftar = pesertaPelatihanService.getPesertaPelatihanByPelatihan(pelatihan).size();
        model.addAttribute("pelatihan", pelatihan);
        model.addAttribute("temp", temp);
        model.addAttribute("kapasitas",pelatihan.getKapasitas() - jumlahPesertaTerdaftar);
//        model.addAttribute("peserta", pesertaService.getListPesertaBaru(pelatihan));

        return "form-tambah-peserta-pelatihan";
    }

//    @PostMapping(value = "/tambah-peserta/{id}")
//    private String addPesertaPelatihanSubmit(@PathVariable("id") Integer id , final HttpServletRequest request, Model model){
//        String[] idPesertaList = request.getParameter("idPesertas").split(",");
//        PelatihanModel pelatihan = pelatihanService.getPelatihanById(id);
//        pesertaPelatihanService.assignPesertaPelatihan(idPesertaList, pelatihan);
//
//        model.addAttribute("countAssignedPeserta", idPesertaList.length);
//        model.addAttribute("pelatihan", pelatihan);
//        return "berhasil";
//    }

    @PostMapping(value = "/tambah-peserta/{id}")
    private String addPesertaPelatihanSubmit(@PathVariable("id") Integer id, @ModelAttribute PelatihanModel pelatihan, String listId, Model model){
//        String[] idPesertaList = request.getParameter("idPesertas").split(",");
        System.out.println(pelatihan.getListPesertaPelatihan());
        List<String> temp = new ArrayList<>();
        List<PesertaPelatihanModel> selected = pelatihan.getListPesertaPelatihan();

        PelatihanModel modelPelatihan = pelatihanService.getPelatihanById(id);
        int counter = modelPelatihan.getKapasitas();


        System.out.println(listId);
        String[] idSplit = listId.split(" ");
        for(int a=0; a<idSplit.length; a++){
            Long num = Long.valueOf(idSplit[a]);
            pesertaPelatihanService.deletePesertaPelatihan(pesertaPelatihanService.getPesertaPelatihanById(num));
        }

        for(PesertaPelatihanModel p : selected) {
            temp.add(String.valueOf(p.getPeserta().getId()));
            System.out.println(p.getId());
        }

        String[] idPesertaList = new String[temp.size()];
        for(int a = 0; a < temp.size(); a++){
            idPesertaList[a] = String.valueOf(temp.get(a));
        }
//        idPesertaList = select.toArray(idPesertaList);

        System.out.println(counter + " counter");
        System.out.println(idPesertaList.length + " idPesertaList");

        if(idPesertaList.length > counter){
            model.addAttribute("msg", "Jumlah peserta yang Anda masukkan melebihi kapasitas pelatihan!");
            return "form-tambah-peserta-pelatihan";
        }
        else{
            PelatihanModel pelatihanReal = pelatihanService.getPelatihanById(id);
            pesertaPelatihanService.assignPesertaPelatihan(idPesertaList, pelatihanReal);
        }

        model.addAttribute("countAssignedPeserta", idPesertaList.length);
        model.addAttribute("pelatihan", pelatihan);
        return "berhasil";
    }

    List<PesertaPelatihanModel> tempPesertaPelatihan = new ArrayList<>();

    @GetMapping(value = "/tambah-pegawai/{id}")
    private String addPesertaPelatihanFromPegawaiSubmit(@PathVariable("id") Integer id, @ModelAttribute PelatihanModel pelatihanTemp , Model model){

        PelatihanModel pelatihan = pelatihanService.getPelatihanById(id);
        int numberExistingPeserta = pesertaPelatihanService.getPesertaPelatihanByPelatihan(pelatihan).size();

        BaseResponseKaryawan responseKaryawan = karyawanRestService.getListPegawai().block();
        List<Map<String, Object>> listKaryawan = (List<Map<String,Object>>)responseKaryawan.getResult();
        System.out.println(listKaryawan);

        if(listKaryawan.size() + numberExistingPeserta > pelatihan.getKapasitas()){
            model.addAttribute("id", id);
            model.addAttribute("gagal", "Jumlah peserta yang diassign telah melebihi kapasitas pelatihan!");
            return "berhasil";
        }

        pesertaPelatihanService.assignPesertaPelatihanFromPegawai(listKaryawan, pelatihan);
        model.addAttribute("countAssignedPeserta", listKaryawan.size());
        model.addAttribute("pelatihan", pelatihan);
        return "redirect:/pelatihan/detail/{id}";
    }
}

