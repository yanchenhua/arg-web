package ltd.ontsol.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ltd.ontsol.core.dto.DicSuggestionDTO;
import ltd.ontsol.core.dto.DictionaryDTO;
import ltd.ontsol.core.service.DicSuggestionService;
import ltd.ontsol.core.service.DictionaryService;

/**
 * Created by cn40580 at 2018-06-17 11:21 AM.
 */
@Controller
@RequestMapping("/dictionary")
public class DictionaryController {

    private static final Log log = LogFactory.getLog(DictionaryController.class);

    @Inject
    DictionaryService service;

    @Inject
    DicSuggestionService suggestionService;


    @GetMapping("")
    public String dictionaryIndex(Model model) {
        Map objs = new HashMap<>();
        objs.put("obj", service.findRoot());
        model.addAllAttributes(objs);
        return "/front/dictionary";
    }

    @GetMapping("/test")
    @ResponseBody
    public List<DictionaryDTO> test(Model model) {
        return service.findRoot();
    }

    @Deprecated
    @PostMapping("/suggestionForm/{dicId}")
    public String suggestionForm(@ModelAttribute(value = "obj") DicSuggestionDTO obj,
                                 @PathVariable(name = "dicId") Long dicId) {
        DictionaryDTO dic = service.findById(dicId);
        obj.setDictionary(dic);
        obj.setCreatedDate(new Date());

        suggestionService.save(obj);

        return "redirect:/dictionary";
    }


}
