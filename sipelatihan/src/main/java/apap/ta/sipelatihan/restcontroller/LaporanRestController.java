package apap.ta.sipelatihan.restcontroller;

import apap.ta.sipelatihan.rest.LaporanDetail;
import apap.ta.sipelatihan.service.LaporanRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
public class LaporanRestController {
    @Autowired
    private LaporanRestService laporanRestService;

    @GetMapping("/peserta/laporan")
    public String addLaporanFormPage(Model model){
        model.addAttribute("Laporan", new LaporanDetail());
        return "form-add-laporan";
    }

    @PostMapping("/peserta/laporan")
    public String addLaporanSubmit(@ModelAttribute LaporanDetail laporan, Model model){
        laporanRestService.postLaporan(laporan);
        model.addAttribute("username", laporan.getUsername());
        return "add-laporan";
    }

}
