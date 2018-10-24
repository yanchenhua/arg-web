package ltd.ontsol.web.controller;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ltd.ontsol.core.constants.ContactCardConstants;
import ltd.ontsol.core.dto.ContactCardDTO;
import ltd.ontsol.core.service.ContactCardService;

/**
 * Created by cn40580 at 2018-06-17 11:21 AM.
 */
@Controller
@RequestMapping("/contact")
public class ContactController {

    private static final Log log = LogFactory.getLog(ContactController.class);

    @Inject
    ContactCardService service;

    @GetMapping("/{type}")
    public String globalIndex(Model model, @PathVariable ContactCardConstants type) {
        model.addAttribute("type", type.toString());
        List<ContactCardDTO> objs = service.findAllByType(type);
        model.addAttribute("objs", objs);
        return "/front/contact";
    }

}
