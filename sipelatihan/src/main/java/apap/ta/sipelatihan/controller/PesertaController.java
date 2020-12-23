package apap.ta.sipelatihan.controller;

import apap.ta.sipelatihan.model.PesertaModel;
import apap.ta.sipelatihan.service.PesertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/peserta")
public class PesertaController {
    @Qualifier("pesertaServiceImpl")

    @Autowired
    private PesertaService pesertaService;


    @GetMapping("/add")
    private String addPesertaForm(Model model){
        model.addAttribute("peserta", new PesertaModel());
        return "form-add-peserta";
    }

    @PostMapping("/add")
    private String addPesertaSubmit(
            @ModelAttribute PesertaModel peserta,
            RedirectAttributes attributes){
        pesertaService.addPeserta(peserta);
        attributes.addFlashAttribute("message", "Peserta berhasil ditambahkan!");
        return "form-add-peserta";
    }

//    @GetMapping("/laporan")
//    public String addLaporanFormPage(Model model){
//        model.addAttribute("laporan", new LaporanDetail());
//        return "form-add-laporan";
//    }
//
//    @PostMapping("/laporan")
//    public String addLaporanSubmit(
//            @ModelAttribute LaporanDetail laporan,
//            @RequestParam(value="username") String username,
//            @RequestParam(value="tanggal") Date tanggal,
//            Model model){
//        laporanRestService.postLaporan(laporan, username, tanggal);
//        model.addAttribute("username", laporan.getUsername());
//        return "add-laporan";
//    }
}