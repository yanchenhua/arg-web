package ltd.ontsol.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ltd.ontsol.core.constants.ArticleConstants;
import ltd.ontsol.core.constants.MediaConstants;
import ltd.ontsol.core.constants.PageConstants;
import ltd.ontsol.core.dto.MediaDTO;
import ltd.ontsol.core.service.ArticleService;
import ltd.ontsol.core.service.AttachmentService;
import ltd.ontsol.core.service.MediaService;

/**
 * Created by cn40580 at 2018-06-17 11:21 AM.
 */
@Controller
@RequestMapping("/news")
public class NewsController {

    private static final Log log = LogFactory.getLog(NewsController.class);

    @Inject
    private ArticleService service;

    @Inject
    private AttachmentService attachmentService;

    @Inject
    private MediaService mediaservice;

    @GetMapping("")
    public String aboutIndex(Model model) {
        Map objs = new HashMap<>();
        objs.put("obj", service.findAllByType(ArticleConstants.NEWS).stream()
                .limit(PageConstants.PAGE_SIZE).collect(Collectors.toList()));
        model.addAllAttributes(objs);
        return "/front/news/news";
    }

    @GetMapping("/industry")
    public String industryIndex(Model model) {
        Map<String, Object> objs = new HashMap<>();
        objs.put("obj", service.findAllByType(ArticleConstants.INDUSTRY).stream()
                .limit(PageConstants.PAGE_SIZE).collect(Collectors.toList()));
        model.addAllAttributes(objs);
        return "/front/news/industry";
    }

    @GetMapping("/knowledge")
    public String knowledgeIndex(Model model) {
        Map<String, Object> objs = new HashMap<>();
        objs.put("obj", service.findAllByType(ArticleConstants.SCIENCE).stream()
                .limit(PageConstants.PAGE_SIZE).collect(Collectors.toList()));
        model.addAllAttributes(objs);
        return "/front/news/knowledge";
    }

    @GetMapping("/video-and-photo")
    public String photoAndVideoIndex(Model model) {
        Map<String, Object> objs = new HashMap<>();
        List<MediaDTO> videos = mediaservice.findAllByType(MediaConstants.VIDEO)
                .stream().limit(PageConstants.PAGE_SIZE).collect(Collectors.toList());
        List<MediaDTO> pdfs = mediaservice.findAllByType(MediaConstants.PDF)
                .stream().limit(PageConstants.PAGE_SIZE).collect(Collectors.toList());
        objs.put("videos", videos);
        objs.put("pdfs", pdfs);
        model.addAllAttributes(objs);
        return "/front/news/video-and-photo";
    }

    @GetMapping("/photo")
    public String photoIndex(Model model) {
        Map<String, Object> objs = new HashMap<>();
        objs.put("pdfs", mediaservice.findAllByType(MediaConstants.PDF)
                .stream().limit(PageConstants.PAGE_SIZE).collect(Collectors.toList()));
        model.addAllAttributes(objs);
        return "/front/news/photo";
    }

    @GetMapping("/video")
    public String videoIndex(Model model) {
        Map<String, Object> objs = new HashMap<>();
        objs.put("videos", mediaservice.findAllByType(MediaConstants.VIDEO)
                .stream().limit(PageConstants.PAGE_SIZE).collect(Collectors.toList()));
        model.addAllAttributes(objs);
        return "/front/news/video";
    }

    @GetMapping("/detail/{id}")
    public String detailIndex(Model model, @PathVariable Long id) {
        Map<String, Object> objs = new HashMap<>();

        objs.put("obj", service.findById(id));
        model.addAllAttributes(objs);
        return "/front/news/detail";
    }

}
