package abl.frd;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpaForwardController {
    /*
    @GetMapping("/")
    public String forwardRoot() {
        return "forward:/index.html";
    }
        */

   @GetMapping("/{path:[^\\.]*}")
    public String forwardRootRoute() {
        return "forward:/index.html";
    }

    @GetMapping("/{path1:[^\\.]*}/{path2:[^\\.]*}")
    public String forwardNestedRoute() {
        return "forward:/index.html";
    }
}