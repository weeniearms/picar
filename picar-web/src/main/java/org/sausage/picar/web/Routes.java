package org.sausage.picar.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by weenie on 07.02.15.
 */
@Controller
public class Routes {
    @RequestMapping({
            "/sequence",
            "/about" })
    public String index() {
        return "forward:index.html";
    }
}
