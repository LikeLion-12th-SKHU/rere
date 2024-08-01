package net.skhu.likelion12thteam03be;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserCheckController {
    @GetMapping
    public String userHi (Model model) {
        return "user.html";
    }
}
