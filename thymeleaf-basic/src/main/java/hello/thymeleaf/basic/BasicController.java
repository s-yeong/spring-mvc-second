package hello.thymeleaf.basic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @GetMapping("/text-basic")
    public String textBasic(Model model) {
        model.addAttribute("data", "Hello Spring!");
        return "basic/text-basic";
    }
    
    // 웹 브라우저는 <를 HTML 태그의 시작으로 인식 => 문자로 표현하는 방법 = HTML 엔티티
    // HTML에서 사용하는 특수 문자를 HTML 엔티티로 변경하는 것 => 이스케이프 (기본적으로 제공)
    @GetMapping("/text-unescaped")
    public String textUnescaped(Model model) {
        model.addAttribute("data", "<b>Hello Spring!</b>");
        return "basic/text-unescaped";
    }



}
