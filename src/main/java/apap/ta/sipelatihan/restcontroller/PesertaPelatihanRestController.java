package apap.ta.sipelatihan.restcontroller;

import apap.ta.sipelatihan.model.PelatihanModel;
import apap.ta.sipelatihan.model.PesertaModel;
import apap.ta.sipelatihan.model.PesertaPelatihanModel;
import apap.ta.sipelatihan.rest.BaseResponse;
import apap.ta.sipelatihan.rest.BaseResponsePesertaPelatihan;
import apap.ta.sipelatihan.service.PelatihanService;
import apap.ta.sipelatihan.service.PesertaPelatihanRestService;

import apap.ta.sipelatihan.service.PesertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class PesertaPelatihanRestController {
    @Autowired
    private PesertaPelatihanRestService pesertaPelatihanRestService;

    @Autowired
    private PesertaService pesertaService;

    @GetMapping(value = "/peserta-pelatihan/{nama_peserta}")
    private BaseResponsePesertaPelatihan retrievePesertaPelatihan(
            @PathVariable(value = "nama_peserta") String nama_peserta){

        BaseResponsePesertaPelatihan baseResponse = new BaseResponsePesertaPelatihan();
        List<PesertaModel> listPeserta = pesertaService.getListPeserta();
        PesertaModel peserta = pesertaService.getPesertaByNamaPeserta(nama_peserta);

        if (!(listPeserta.contains(peserta))) {
            baseResponse.setStatus(400);
            baseResponse.setMessage("Bad Request");
            baseResponse.setResult(new ArrayList<>());
            return baseResponse;
        }
        else {
            List<Map<String,Object>> listPesertaPelatihan = pesertaPelatihanRestService.retrieveListPesertaPelatihan(nama_peserta);
            baseResponse.setStatus(200);
            baseResponse.setMessage("success");
            baseResponse.setResult(listPesertaPelatihan);
            return baseResponse;
        }
    }
}
