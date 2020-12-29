package apap.ta.sipelatihan.restcontroller;

import apap.ta.sipelatihan.rest.BaseResponse;
import apap.ta.sipelatihan.rest.PegawaiDTO;
import apap.ta.sipelatihan.service.UserRestService;
import apap.ta.sipelatihan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class UserRestController {
    @Autowired
    UserRestService userRestService;

    @Autowired
    UserService userService;

    @PostMapping(value = "/pegawai")
    private BaseResponse addPegawai(@ModelAttribute PegawaiDTO pegawai){
        return userRestService.addPegawai(pegawai);
    }

    @GetMapping(value = "/pegawai/{username}")
    private BaseResponse getPegawai(@PathVariable("username") String username){
        return userRestService.getPegawai(username);
    }
}
