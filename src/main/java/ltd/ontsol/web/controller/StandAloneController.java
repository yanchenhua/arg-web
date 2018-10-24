package ltd.ontsol.web.controller;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ltd.ontsol.core.constants.AttachmentConstants;
import ltd.ontsol.core.dto.AttachmentDTO;
import ltd.ontsol.core.service.*;
import ltd.ontsol.core.util.IpUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import ltd.ontsol.core.constants.ArticleConstants;
import ltd.ontsol.core.constants.PageConstants;
import ltd.ontsol.core.dto.ArticleDTO;
import ltd.ontsol.core.dto.BusinessDTO;
import ltd.ontsol.core.dto.ProductDTO;

/**
 * Created by cn40580 at 2018-06-17 11:21 AM.
 */
@Controller
public class StandAloneController {

    private static final Log LOG = LogFactory.getLog(StandAloneController.class);

    @Inject
    HomeService homeService;

    @Inject
    ArticleService articleService;

    @Inject
    ProductService productService;

    @Inject
    BusinessService businessService;
    @Inject
    AttachmentService service;

    @GetMapping("/tech")
    public String techIndex(Model model) {
        Map objs = new HashMap<>();
        objs.put("objs", productService.findAll()
                .stream().limit(PageConstants.PAGE_SIZE).collect(Collectors.toList()));
        model.addAllAttributes(objs);
        return "/front/tech";
    }

    @GetMapping("/legal")
    public String legalIndex(Model model) {
        Map objs = new HashMap<>();
        objs.put("obj", homeService.findOne());
        model.addAllAttributes(objs);
        return "/front/legal";
    }

    @GetMapping("/service")
    public String serviceIndex(Model model) {
        Map objs = new HashMap<>();
        objs.put("objs", articleService.findAllByType(ArticleConstants.TECH)
                .stream().limit(PageConstants.PAGE_SIZE).collect(Collectors.toList()));
        model.addAllAttributes(objs);
        return "/front/service";
    }

    @GetMapping("/business")
    public String businessIndex(Model model) {
        Map objs = new HashMap<>();
        return "/front/business";
    }
    @GetMapping("/employee")
    public String customerIndex(Model model) {
        Map objs = new HashMap<>();
        return "/front/employee";
    }
    @GetMapping("/customer")
    public String customer2Index(Model model) {
        Map objs = new HashMap<>();
        return "/front/customer";
    }
    @GetMapping("/e_login")
    public String loginIndex(Model model) {
        Map objs = new HashMap<>();
        return "/front/e_login";
    }
    @PostMapping("/submitBusiness")
    public String submitBusiness(@ModelAttribute(value = "obj") BusinessDTO obj, HttpServletRequest request, BindingResult bindingResult) throws IOException {
        String ipaddr = IpUtil.getIpAddr(request);
        LOG.info("some one submit form ---> ip address : " + ipaddr);
        MultipartFile file = obj.getFile();
        LOG.info(String.format("[文件类型] - [{%s}]", file.getContentType()));
        LOG.info(String.format("[文件名称] - [{%s}]", file.getOriginalFilename()));
        LOG.info(String.format("[文件大小] - [{%s}]", file.getSize()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMDDmmss");
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String tempFileName = sdf.format(new Date()) + "." + suffix;
        Path filePath = Paths.get(service.getUploadFolder(), tempFileName);
        LOG.info(String.format("[文件上传路径] - [{%s}]", filePath.toString()));
        Files.copy(file.getInputStream(), filePath);
        //从request获取id
        AttachmentDTO dto = new AttachmentDTO();
        dto.setDisplayName(file.getOriginalFilename());
        dto.setFilePath(tempFileName);
        dto.setType(AttachmentConstants.IMAGE);
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        obj.setSubDate(dateString);

        obj.setShopCertAttachment(dto);
        if (StringUtils.isEmpty(obj.getAddress())
                && (obj.getCountry() != null && obj.getProvince() != null && obj.getCity() != null && obj.getStreet() != null)) {
            obj.setAddress(obj.getCountry() + " " + obj.getProvince() + " " + obj.getCity() + " " + obj.getStreet());
        }
        businessService.saveOrUpdate(obj);
        return "redirect:/business";
    }

    @GetMapping("/contact")
    public String contactIndex(Model model) {
        Map objs = new HashMap<>();
        return "/front/contact";
    }

    @GetMapping("/fragment")
    public String fragmentlIndex(Model model) {
        Map objs = new HashMap<>();
        objs.put("objs", homeService.findOne());
        model.addAllAttributes(objs);
        return "/front/fragment";
    }

    @GetMapping("/article/{articleId}")
    public String articleIndex(Model model, @PathVariable Long articleId) {
        ArticleDTO obj = null;
        if (articleId != null && articleId > 0) {
            obj = articleService.findById(articleId);
        }
        Map objs = new HashMap<>();
        objs.put("obj", obj);
        model.addAllAttributes(objs);
        return "/front/article";
    }

    @GetMapping("/detail/{productId}")
    public String detailIndex(Model model, @PathVariable Long productId) {
        ProductDTO obj = null;
        if (productId != null && productId > 0) {
            obj = productService.findById(productId);
        }
        Map objs = new HashMap<>();
        objs.put("obj", obj);
        model.addAllAttributes(objs);
        return "/front/detail";
    }
    /**
     * robots txt
     *
     * @return
     */
    @GetMapping("/robots.txt")
    public void robotsTxt(HttpServletResponse response) throws IOException {
        Writer writer = response.getWriter();
        String lineSeparator = System.getProperty("line.separator", "\n");
        writer.append("User-agent: *").append(lineSeparator);
        writer.append("Allow:").append(" /").append(lineSeparator);
//        writer.append("Disallow:").append("/user/*").append(lineSeparator);
//        writer.append("Disallow:").append("/answer/*/edit").append(lineSeparator);
//        writer.append("Disallow:").append("/question/*/edit").append(lineSeparator);
    }

}
