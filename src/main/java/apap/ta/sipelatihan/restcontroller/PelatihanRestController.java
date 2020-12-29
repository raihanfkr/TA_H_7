package apap.ta.sipelatihan.restcontroller;

import apap.ta.sipelatihan.model.PelatihanModel;
import apap.ta.sipelatihan.model.UserModel;
import apap.ta.sipelatihan.rest.BaseResponse;
import apap.ta.sipelatihan.service.PelatihanRestService;
import apap.ta.sipelatihan.service.UserRestService;
import apap.ta.sipelatihan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class PelatihanRestController {
    @Autowired
    private PelatihanRestService pelatihanRestService;

    @Autowired
    private UserService userService;

    @PostMapping("/pelatihan")
    private BaseResponse createPelatihan(
            @Valid @RequestBody PelatihanModel pelatihan,
            Authentication auth,
            BindingResult bindingResult
    ) {
        BaseResponse baseResponse = new BaseResponse();
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        }
        else {
            UserModel userPengaju = userService.findUser(SecurityContextHolder.getContext().getAuthentication().getName());
            pelatihan.setUserPengaju(userPengaju);

            pelatihanRestService.createPelatihan(pelatihan);
            baseResponse.setStatus(200);
            baseResponse.setMessage("success");
            baseResponse.setResultPelatihan(pelatihan);
            return baseResponse;
        }
    }
}