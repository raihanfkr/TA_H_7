package apap.ta.sipelatihan.controller;

import apap.ta.sipelatihan.model.PesertaModel;
import apap.ta.sipelatihan.model.UserModel;
import apap.ta.sipelatihan.rest.BaseResponse;
import apap.ta.sipelatihan.rest.PegawaiDTO;
import apap.ta.sipelatihan.service.PesertaService;
import apap.ta.sipelatihan.service.UserRestService;
import apap.ta.sipelatihan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private UserService userService;

    @Autowired
    private UserRestService userRestService;


    @GetMapping("/add")
    private String addPesertaForm(Model model){
        model.addAttribute("peserta", new PesertaModel());
        UserModel user = userService.findUser(SecurityContextHolder.getContext().getAuthentication().getName());
        BaseResponse baseResponse = userRestService.getPegawai(user.getUsername());
        PegawaiDTO pegawai = baseResponse.getResult();
        model.addAttribute("pegawai", pegawai);
        return "form-add-peserta";
    }

    @PostMapping("/add")
    private String addPesertaSubmit(
            @ModelAttribute PesertaModel peserta,
            Model model){
        pesertaService.addPeserta(peserta);
        model.addAttribute("message", "Peserta berhasil ditambahkan!");
        UserModel user = userService.findUser(SecurityContextHolder.getContext().getAuthentication().getName());
        BaseResponse baseResponse = userRestService.getPegawai(user.getUsername());
        PegawaiDTO pegawai = baseResponse.getResult();
        model.addAttribute("pegawai", pegawai);
        return "form-add-peserta";
    }

}