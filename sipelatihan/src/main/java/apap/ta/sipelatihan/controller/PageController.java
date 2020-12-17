package apap.ta.sipelatihan.controller;

import apap.ta.sipelatihan.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @Autowired
    RoleService roleService;

    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("listRole", roleService.findAll());
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

//    @RequestMapping("/addUser")
//    public String addUserPage(Model model){
//        model.addAttribute("listRole", roleService.findAll());
//        return "add-user";
//    }

}

