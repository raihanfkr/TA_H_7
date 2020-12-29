package apap.ta.sipelatihan.controller;

import apap.ta.sipelatihan.model.RoleModel;
import apap.ta.sipelatihan.model.UserModel;
import apap.ta.sipelatihan.rest.BaseResponse;
import apap.ta.sipelatihan.rest.PegawaiDTO;
import apap.ta.sipelatihan.service.UserRestService;
import apap.ta.sipelatihan.service.RoleService;
import apap.ta.sipelatihan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRestService userRestService;

    @RequestMapping("/add")
    public String addUserForm(Model model) {
        model.addAttribute("listRole", roleService.findAll());
        return "form-add-user";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUserSubmit(@ModelAttribute PegawaiDTO pegawai,
                                @RequestParam("password") String password,
                                Model model){
        UserModel user = new UserModel();
        RoleModel role = roleService.findRoleById(pegawai.getRoleId());
        user.setUsername(pegawai.getUsername());
        user.setPassword(password);
        user.setRole(role);
        userService.addUser(user);
        userRestService.addPegawai(pegawai);
        model.addAttribute("message","User berhasil ditambahkan!");
        return "berhasil";
    }

    @RequestMapping(value = "/profil")
    private String profilUser(
            Model model
    ) throws WebClientException {
        UserModel user = userService.findUser(SecurityContextHolder.getContext().getAuthentication().getName());
        RoleModel role = roleService.findRoleById(user.getRole().getId_role());
        try {
            BaseResponse baseResponse = userRestService.getPegawai(user.getUsername());
            PegawaiDTO pegawai = baseResponse.getResult();
            model.addAttribute("isPegawai", true);
            model.addAttribute("pegawai", pegawai);
        } catch (WebClientException webClientException){ }
        model.addAttribute("username", user.getUsername());
        model.addAttribute("role", role.getNama_role());
//        model.addAttribute("dateTime", LocalDateTime.now());

        return "profil-user";
    }
}
