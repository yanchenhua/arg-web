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

import ltd.ontsol.core.service.ArticleService;
import ltd.ontsol.core.service.AttachmentService;
import ltd.ontsol.core.service.HomeService;
import ltd.ontsol.core.service.HonorService;

/**
 * Created by cn40580 at 2018-06-17 11:21 AM.
 */
@Controller
public class HomeController {

    private static final Log log = LogFactory.getLog(HomeController.class);

    @Inject
    HomeService service;

    @Inject
    AttachmentService attachmentService;

    @Inject
    ArticleService articleService;

    @Inject
    HonorService honorService;

    @GetMapping("/")
    public String list(Model model) throws IOException {

        Map<String, Object> objs = new HashMap<>();
        objs.put("obj", service.findOne());
//        objs.put("banner", service.findOne());
        objs.put("articles", articleService.findHomeArticle());
        objs.put("titlesArticles", articleService.findTitleArticle());
        objs.put("honors", honorService.findAll());
        model.addAllAttributes(objs);
        return "/front/index";
    }


}
