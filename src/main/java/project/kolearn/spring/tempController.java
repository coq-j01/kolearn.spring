package project.kolearn.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequiredArgsConstructor
public class tempController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

}
