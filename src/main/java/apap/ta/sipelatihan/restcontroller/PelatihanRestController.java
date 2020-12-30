package apap.ta.sipelatihan.restcontroller;

import apap.ta.sipelatihan.model.JenisPelatihanModel;
import apap.ta.sipelatihan.model.PelatihanModel;
import apap.ta.sipelatihan.model.TrainerModel;
import apap.ta.sipelatihan.model.UserModel;
import apap.ta.sipelatihan.rest.BaseResponsePelatihan;
import apap.ta.sipelatihan.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PelatihanRestController {
    @Autowired
    private PelatihanRestService pelatihanRestService;

    @Autowired
    private PelatihanService pelatihanService;

    @Autowired
    private JenisPelatihanService jenisPelatihanService;

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private UserService userService;

    @PostMapping("/pelatihan")
    private BaseResponsePelatihan createPelatihan(
            @Valid @RequestBody PelatihanModel pelatihan,
            BindingResult bindingResult
    ) {
        BaseResponsePelatihan baseResponse = new BaseResponsePelatihan();
        if (bindingResult.hasFieldErrors()) {
            baseResponse.setStatus(400);
            baseResponse.setMessage("Bad Request");
            baseResponse.setResult(null);
            return baseResponse;
        }
        else {
            // set id
            List<PelatihanModel> pelatihanList = pelatihanService.getPelatihanList();
            PelatihanModel pelatihanTerakhir = pelatihanList.get(pelatihanList.size()-1);
            pelatihan.setId(pelatihanTerakhir.getId()+1);
            System.out.println(pelatihan.getId());

            // set jenis pelatihan & trainer
            JenisPelatihanModel jenisPelatihan = jenisPelatihanService.getJenisPelatihanById(1);
            TrainerModel trainer = trainerService.getTrainerById(1);
            pelatihan.setTrainer(trainer);
            pelatihan.setJenis_pelatihan(jenisPelatihan);

            // set user pengaju
            UserModel userPengaju = userService.findUser(SecurityContextHolder.getContext().getAuthentication().getName());
            pelatihan.setUserPengaju(userPengaju);

            pelatihanRestService.createPelatihan(pelatihan);
            baseResponse.setStatus(200);
            baseResponse.setMessage("success");
            baseResponse.setResult(pelatihan);
            return baseResponse;
        }
    }
}