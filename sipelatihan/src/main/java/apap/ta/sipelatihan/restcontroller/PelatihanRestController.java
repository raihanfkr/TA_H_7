package apap.ta.sipelatihan.restcontroller;

import apap.ta.sipelatihan.model.PelatihanModel;
import apap.ta.sipelatihan.rest.BaseResponse;
import apap.ta.sipelatihan.service.PelatihanRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
public class PelatihanRestController {
    @Autowired
    private PelatihanRestService pelatihanRestService;

    @PostMapping("/pelatihan")
    private BaseResponse<PelatihanModel> createPelatihan(
            @Valid @RequestBody PelatihanModel pelatihan,
            BindingResult bindingResult
    ) {
        BaseResponse<PelatihanModel> baseResponse = new BaseResponse<PelatihanModel>();
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        }
        else {
            pelatihanRestService.createPelatihan(pelatihan);
            baseResponse.setStatus(200);
            baseResponse.setMessage("success");
            baseResponse.setResult(pelatihan);
            return baseResponse;
        }
    }
}
