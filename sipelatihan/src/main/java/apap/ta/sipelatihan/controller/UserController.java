package apap.ta.sipelatihan.controller;

import apap.ta.sipelatihan.model.UserModel;
import apap.ta.sipelatihan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    private String addUserSubmit(
            @ModelAttribute UserModel user,
            Model model) {
        if(userService.checkPassword(user.getPassword())){
            userService.addUser(user);
            model.addAttribute("user", user);
            model.addAttribute("berhasil", "user berhasil ditambahkan");
        }else{
            model.addAttribute("user", user);
            model.addAttribute("gagal", "password harus mengandung angka dan huruf serta memiliki minimal 8 karakter");
            return "index";
        }
        return "index";
    }

    @GetMapping(value = "/change-password")
    public String changePassword(Model model) {
        return "change-password";
    }

    @PostMapping(value = "/change-password")
    public String changePassword(
            @ModelAttribute UserModel userModel,
            @RequestParam("oldPassword") String oldPassword,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("confirmNewPassword")String confirmNewPassword, Model model) {
        UserModel user = userService.findUser(userModel.getUsername());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(oldPassword, user.getPassword())){
            if (newPassword.equals(confirmNewPassword)) {
                if (userService.checkPassword(newPassword) == true) {
                    user.setPassword(newPassword);
                    userService.addUser(user);
                    model.addAttribute("berhasil", "password berhasil diubah");
                }else{
                    model.addAttribute("msg", "password harus mengandung angka dan huruf serta memiliki minimal 8 karakter");
                }
            }else{
                model.addAttribute("msg", "password yang dimasukkan berbeda");
            }
        }else {
            model.addAttribute("msg", "password gagal diubah");
        }
        return "change-password";
    }
}