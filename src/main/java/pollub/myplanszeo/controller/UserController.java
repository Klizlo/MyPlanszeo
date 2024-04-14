package pollub.myplanszeo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pollub.myplanszeo.model.User;
import pollub.myplanszeo.service.user.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "auth/signup_form";
    }

    @PostMapping("/register")
    public String processRegister(User user) {
        userService.addUser(user);
        return "auth/signup_success";
    }


}
