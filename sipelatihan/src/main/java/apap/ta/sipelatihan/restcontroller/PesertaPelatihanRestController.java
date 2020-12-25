package apap.ta.sipelatihan.restcontroller;

import apap.ta.sipelatihan.model.PelatihanModel;
import apap.ta.sipelatihan.model.PesertaPelatihanModel;
import apap.ta.sipelatihan.service.PelatihanService;
import apap.ta.sipelatihan.service.PesertaPelatihanRestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class PesertaPelatihanRestController {
    @Autowired
    private PesertaPelatihanRestService pesertaPelatihanRestService;

    @Autowired
    private PelatihanService pelatihanService;

    @GetMapping(value = "/peserta-pelatihan/{nama_peserta}")
    private List<Map<String,Object>> retrievePesertaPelatihan(
            @PathVariable(value = "nama_peserta") String nama_peserta){
        return pesertaPelatihanRestService.retrieveListPesertaPelatihan(nama_peserta);
    }
//        List<PesertaPelatihanModel> p = ;
//        List<PelatihanModel> pelatihan = pelatihanService.getPelatihanList();
//        List<PelatihanModel> p = pelatihanService.getPelatihanList();
//        PelatihanModel pelatihan = pelatihanService.getPelatihanById(pelatihan);
//        return pelatihanM
//        return pesertaPelatihanRestService.retrieveListPesertaPelatihan();
}