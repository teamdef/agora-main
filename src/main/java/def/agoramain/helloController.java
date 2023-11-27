package def.agoramain;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/hello")
public class helloController {
    @GetMapping()
    public String hello() {
        return "hello";
    }
}
