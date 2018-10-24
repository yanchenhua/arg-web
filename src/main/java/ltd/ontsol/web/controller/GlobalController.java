package ltd.ontsol.web.controller;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ltd.ontsol.core.service.ContactCardService;

/**
 * Created by cn40580 at 2018-06-17 11:21 AM.
 */
@Controller
@RequestMapping("/global")
public class GlobalController {

    private static final Log log = LogFactory.getLog(GlobalController.class);

    @Inject
    ContactCardService service;

    /*@GetMapping("/{type}")
    public String globalIndex(Model model, @PathVariable String type) {
        model.addAttribute("type", type);
        return "/front/global";
    }*/
    @GetMapping("/SERVICE_NODE")
    public String servicenodeIndex(Model model) {

        model.addAttribute("type", "SERVICE_NODE");
        return "/front/global/SERVICE_NODE";
    }
    @GetMapping("/CAR_GLASS")
    public String carglassIndex(Model model) {

        model.addAttribute("type", "CAR_GLASS");
        return "/front/global/CAR_GLASS";
    }
    @GetMapping("/FLOAT_GLASS")
    public String floatglassIndex(Model model) {

        model.addAttribute("type", "FLOAT_GLASS");
        return "/front/global/FLOAT_GLASS";
    }
    @GetMapping("/RESEARCH_HUB")
    public String researchhubIndex(Model model) {

        model.addAttribute("type", "RESEARCH_HUB");
        return "/front/global/RESEARCH_HUB";
    }
    @GetMapping("/SALES")
    public String saleIndex(Model model) {

        model.addAttribute("type", "SALES");
        return "/front/global/SALES";
    }
}
