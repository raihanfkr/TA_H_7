package apap.ta.sipelatihan.controller;

import apap.ta.sipelatihan.model.TrainerModel;
import apap.ta.sipelatihan.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
public class TrainerController {
//    @Qualifier("trainerServiceImpl")
    @Autowired
    private TrainerService trainerService;


    @RequestMapping("/")
    public String home(Model model){
        return "home";
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
            String exist_ktp =trainerService.checkTrainer(noKtp).getNoKtp();
            if (noKtp.equals(exist_ktp)){
                model.addAttribute("msg", "Trainer sudah terdaftar di Database!");
            }
        } catch (NoSuchElementException e){
            trainerService.addTrainer(trainer);
            model.addAttribute("msg", "Trainer berhasil ditambahkan ke database !");
        }
        return "add-trainer";
    }
}

