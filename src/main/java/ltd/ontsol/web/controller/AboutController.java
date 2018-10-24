package ltd.ontsol.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ltd.ontsol.core.service.AboutService;
import ltd.ontsol.core.service.CertificateService;
import ltd.ontsol.core.service.DevelopmentService;
import ltd.ontsol.core.service.HonorService;
import ltd.ontsol.core.service.LeaderService;

/**
 * Created by cn40580 at 2018-06-17 11:21 AM.
 */
@Controller
@RequestMapping("/about")
public class AboutController {

    private static final Log log = LogFactory.getLog(AboutController.class);

    @Inject
    AboutService service;

    @Inject
    LeaderService leaderService;

    @Inject
    DevelopmentService developmentService;

    @Inject
    CertificateService certificateService;

    @Inject
    HonorService honorService;

    @GetMapping("/{pageName}")
    public String aboutHome(Model model, @PathVariable("pageName") String pageName) throws IOException {
        Map<String, Object> objs = new HashMap<>();
        objs.put("obj", service.findOne());
        model.addAllAttributes(objs);
        return "/front/about/" + pageName;
    }

    @GetMapping("")
    public String aboutIndex(Model model) {
        Map<String, Object> objs = new HashMap<>();
        objs.put("developments", developmentService.findAll());
        objs.put("certificates", certificateService.findAll());
        objs.put("honors", honorService.findAll());
        objs.put("obj", service.findOne());
        model.addAllAttributes(objs);
        return "/front/about/about";
    }

    @GetMapping("/team")
    public String teamIndex(Model model) {
        Map<String, Object> objs = new HashMap<>();
        objs.put("obj", leaderService.findAll());
        model.addAllAttributes(objs);
        return "/front/about/team";
    }

}
