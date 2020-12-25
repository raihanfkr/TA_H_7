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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
public class TrainerController {
    //    @Qualifier("trainerServiceImpl")
    @Autowired
    private TrainerService trainerService;

    @GetMapping("/trainer/viewall")
    public String listPelatihan(Model model) {
        List<TrainerModel> listTrainer = trainerService.getTrainerList();
        model.addAttribute("listTrainer", listTrainer);

        return "viewall-trainer";
    }

    @RequestMapping(value = "/trainer/add", method = RequestMethod.GET)
    public String addTrainerFormPage(Model model) {
        model.addAttribute("trainer", "Trainer");

        return "form-add-trainer";
    }

    @RequestMapping(value = "/trainer/add", method = RequestMethod.POST)
    public String addTrainerSubmit(@RequestParam String noKtp,
                                   @ModelAttribute TrainerModel trainer,
                                   Model model
    ) {
        try{
            String exist_ktp = trainerService.checkTrainer(noKtp).getNoKtp();
            if (noKtp.equals(exist_ktp)){
                model.addAttribute("msg", "Penambahan Trainer gagal, karena Trainer dengan no ktp tersebut sudah terdaftar di Database!");
                return "form-add-trainer";
            }
        } catch (NoSuchElementException e){
            trainerService.addTrainer(trainer);
        }
        model.addAttribute("msg", "Trainer berhasil ditambahkan ke database!");
        model.addAttribute("listTrainer", trainerService.getTrainerList());
        return "viewall-trainer";
    }

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
            String noKtp,
            Model model
    ){
        try{
            String exist_ktp = trainerService.checkTrainer(noKtp).getNoKtp();
            if (noKtp.equals(exist_ktp)){
                model.addAttribute("msg", "Trainer gagal diubah, karena Trainer dengan no ktp tersebut sudah terdaftar di Database!");
                model.addAttribute("trainer", trainer);
                return "form-update-trainer";
            }
        } catch (NoSuchElementException e){
            trainerService.updateTrainer(trainer);
        }
        model.addAttribute("msg", "Ubah Trainer berhasil!");
        model.addAttribute("listTrainer", trainerService.getTrainerList());
        return "viewall-trainer";
    }
}