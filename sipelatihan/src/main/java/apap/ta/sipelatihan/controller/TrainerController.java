package apap.ta.sipelatihan.controller;

import apap.ta.sipelatihan.model.JenisPelatihanModel;
import apap.ta.sipelatihan.model.PelatihanModel;
import apap.ta.sipelatihan.model.PesertaModel;
import apap.ta.sipelatihan.model.PesertaPelatihanModel;
import apap.ta.sipelatihan.model.RoleModel;
import apap.ta.sipelatihan.model.TrainerModel;
import apap.ta.sipelatihan.model.UserModel;

import apap.ta.sipelatihan.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class TrainerController {

    @Qualifier("trainerServiceImpl")
    @Autowired
    TrainerService trainerService;

//    @Autowired
//    PelatihanService pelatihanService;
//
//    @Autowired
//    PesertaService pesertaService;

    @GetMapping("/trainer/ubah/{id}")
    private String changeTrainerFormPage(
            @PathVariable Integer id,
            Model model
    ){
        TrainerModel trainer = trainerService.getTrainerById(id);
        model.addAttribute("trainer", trainer);

        return "form-update-trainer";
    }

    @PostMapping("/trainer/ubah")
    private String changeTrainerFormSubmit(
            @ModelAttribute TrainerModel trainer,
            Model model
    ){
        TrainerModel updatedTrainer = trainerService.updateTrainer(trainer);
        System.out.println("trainer");
        model.addAttribute("trainer", updatedTrainer);

        return "update-trainer";
    }
}
