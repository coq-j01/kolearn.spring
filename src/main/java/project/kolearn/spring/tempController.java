package project.kolearn.spring;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.MalformedURLException;

@Controller
//@RequiredArgsConstructor
public class tempController {

    @GetMapping("camera")
    public String camera() {
        return "camera";
    }

    @GetMapping("trans")
    public String trans() {
        return "trans";
    }

    @GetMapping("dict")
    public String dict() {
        return "dict";
    }

    @GetMapping("quiz")
    public String quiz() {
        return "quiz";
    }
}
