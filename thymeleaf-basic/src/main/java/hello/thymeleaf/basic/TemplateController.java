package hello.thymeleaf.basic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/templates")
public class TemplateController {

    // 템플릿 조각
    @GetMapping("/fragment")
    public String template() {
        return "template/fragment/fragmentMain";
    }

}
