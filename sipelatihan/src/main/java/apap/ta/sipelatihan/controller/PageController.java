package apap.ta.sipelatihan.controller;

import apap.ta.sipelatihan.model.UserModel;
import apap.ta.sipelatihan.rest.BaseResponse;
import apap.ta.sipelatihan.rest.PegawaiDTO;
import apap.ta.sipelatihan.service.RoleService;
import apap.ta.sipelatihan.service.UserRestService;
import apap.ta.sipelatihan.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    UserRestService userRestService;

    @RequestMapping("/")
    public String home(Model model){
        UserModel user = userService.findUser(SecurityContextHolder.getContext().getAuthentication().getName());
        BaseResponse baseResponse = userRestService.getPegawai(user.getUsername());
        PegawaiDTO pegawai = baseResponse.getResult();
        model.addAttribute("pegawai", pegawai);
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}

