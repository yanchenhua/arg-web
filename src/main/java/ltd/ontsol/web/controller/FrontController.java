package ltd.ontsol.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FrontController {
//
//    @RequestMapping(value = {"/{pageName}"}, method = RequestMethod.GET)
//    public String frontPage(@PathVariable("pageName") String pageName) {
//        return "/front/" + pageName;
//    }
//
//    @RequestMapping(value = {"/{pageName}/"}, method = RequestMethod.GET)
//    public String frontSlashPage(@PathVariable("pageName") String pageName) {
//        return "/front/" + pageName;
//    }

    @RequestMapping(value = {"/front_static/{pageName}/"}, method = RequestMethod.GET)
    public String frontStaticPage(@PathVariable("pageName") String pageName) {
        return "/front_static/" + pageName;
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String loginPage() {
        return "/front/login";
    }

}