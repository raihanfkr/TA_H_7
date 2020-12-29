package apap.ta.sipelatihan.controller;

import apap.ta.sipelatihan.model.PesertaModel;
import apap.ta.sipelatihan.rest.LaporanDetail;
import apap.ta.sipelatihan.service.LaporanRestService;
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

    @Autowired
    private LaporanRestService laporanRestService;

    @GetMapping("/add")
    private String addPesertaForm(Model model){
        model.addAttribute("peserta", new PesertaModel());
        return "form-add-peserta";
    }

    @PostMapping("/add")
    private String addPesertaSubmit(
            @ModelAttribute PesertaModel peserta,
            Model model){
        pesertaService.addPeserta(peserta);
        model.addAttribute("message", "Peserta berhasil ditambahkan!");
        return "form-add-peserta";
    }

    @RequestMapping(value = "/laporan", method = RequestMethod.GET)
    public String addLaporanFormPage(Model model){
        model.addAttribute("laporan", new LaporanDetail());
        return "form-add-laporan";
    }

    @RequestMapping(value = "/laporan", method = RequestMethod.POST)
    public String addLaporanSubmit(@ModelAttribute LaporanDetail laporan, Model model){
        laporanRestService.postLaporan(laporan);
        model.addAttribute("username", laporan.getUsername());
        model.addAttribute("jumlahTraining", laporan.getJumlahTraining());
        return "berhasil";
    }



}