package apap.ta.sipelatihan.controller;

import apap.ta.sipelatihan.model.JenisPelatihanModel;
import apap.ta.sipelatihan.model.PelatihanModel;
import apap.ta.sipelatihan.model.PesertaModel;
import apap.ta.sipelatihan.model.PesertaPelatihanModel;
import apap.ta.sipelatihan.model.RoleModel;
import apap.ta.sipelatihan.model.TrainerModel;
import apap.ta.sipelatihan.model.UserModel;
import apap.ta.sipelatihan.service.TrainerService;
import org.springframework.ui.Model;
import apap.ta.sipelatihan.service.PelatihanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PelatihanController {
    @Autowired
    private PelatihanService pelatihanService;

    @Autowired
    private TrainerService trainerService;

    @GetMapping("/pelatihan/viewall")
    public String listPelatihan(Model model) {
        List<PelatihanModel> listPelatihan = pelatihanService.getPelatihanList();
        model.addAttribute("listPelatihan", listPelatihan);

        return "viewall-pelatihan";
    }
}
