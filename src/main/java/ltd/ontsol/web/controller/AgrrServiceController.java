package ltd.ontsol.web.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import ltd.ontsol.core.constants.AgrrConstants;
import ltd.ontsol.core.constants.AttachmentConstants;
import ltd.ontsol.core.constants.PageConstants;
import ltd.ontsol.core.constants.QuestionTypeConstants;
import ltd.ontsol.core.dto.AddressDTO;
import ltd.ontsol.core.dto.AgrrNodeDTO;
import ltd.ontsol.core.dto.AttachmentDTO;
import ltd.ontsol.core.service.AddressService;
import ltd.ontsol.core.service.AgrrNodeService;
import ltd.ontsol.core.service.AttachmentService;
import ltd.ontsol.core.service.QuestionService;
import ltd.ontsol.core.util.IpUtil;

/**
 * Created by cn40580 at 2018-06-17 11:21 AM.
 */
@Controller
@RequestMapping("/agrr")
public class AgrrServiceController {

    private static final Log LOG = LogFactory.getLog(AgrrServiceController.class);

    @Inject
    QuestionService questionService;

    @Inject
    AddressService addressService;

    @Inject
    AgrrNodeService agrrNodeService;

    @Inject
    AttachmentService service;

    @GetMapping("/")
    public String aboutIndex(Model model) {
        Map objs = new HashMap<>();
        objs.put("obj", questionService.findAllByType(QuestionTypeConstants.SERVICE));
        model.addAllAttributes(objs);
        return "/front/agrr/service";
    }

    @GetMapping("/providerAppli")
    public String providerAppliIndex(Model model) {
        Map<String, Object> objs = new HashMap<>();
        objs.put("obj", questionService.findAllByType(QuestionTypeConstants.PROVIDER));
        model.addAllAttributes(objs);
        return "/front/agrr/providerAppli";
    }

    @GetMapping("/serviceAppli")
    public String serviceAppliIndex(Model model) {
        Map<String, Object> objs = new HashMap<>();
        objs.put("obj", questionService.findAllByType(QuestionTypeConstants.PROVIDER));
        model.addAllAttributes(objs);
        return "/front/agrr/serviceAppli";
    }

    @GetMapping("/provider")
    public String providerIndex(Model model) {
        Map<String, Object> objs = new HashMap<>();
        objs.put("obj", questionService.findAllByType(QuestionTypeConstants.PROVIDER));
        model.addAllAttributes(objs);
        return "/front/agrr/provider";
    }

    @GetMapping("/service")
    public String photoIndex(Model model) {
        Map<String, Object> objs = new HashMap<>();
        objs.put("obj", questionService.findAllByType(QuestionTypeConstants.SERVICE));
        model.addAllAttributes(objs);
        return "/front/agrr/service";
    }

    @GetMapping("/goodProvider")
    public String goodProviderIndex(Model model, HttpServletRequest httpServletRequest) {
        Map<String, Object> objs = new HashMap<>();
        List<AgrrNodeDTO> dtos = agrrNodeService.findAllGoodProvider();
        dtos.forEach(o -> {
            o.setAddrs(addressService.findAllByAgrrNode(o));
        });
        List<AgrrNodeDTO> oversea = dtos.stream().filter(n -> AgrrConstants.OVERSEA.equals(n.getType())).limit(PageConstants.PAGE_SIZE).collect(Collectors.toList());
        List<AgrrNodeDTO> china = dtos.stream().filter(n -> AgrrConstants.CHINA.equals(n.getType())).limit(PageConstants.PAGE_SIZE).collect(Collectors.toList());
        objs.put("oversea", oversea);
        objs.put("china", china);
        model.addAllAttributes(objs);
        return "/front/agrr/goodProvider";
    }

    @GetMapping("/application")
    public String applicationIndex(Model model) {

        return "/front/agrr/application";
    }

    @PostMapping("/providerForm")
    public String providerForm(@ModelAttribute(value = "obj") AgrrNodeDTO obj, HttpServletRequest request) throws IOException {
        String ipaddr = IpUtil.getIpAddr(request);
        LOG.info("some one submit form ---> ip address : " + ipaddr);
        MultipartFile file = obj.getFile();

        SimpleDateFormat sdf = new SimpleDateFormat("yyMMDDmmss");

        String fileName = file.getOriginalFilename();

        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);

        String tempFileName = sdf.format(new Date()) + "." + suffix;

        Path filePath = Paths.get(service.getUploadFolder(), tempFileName);

        Files.copy(file.getInputStream(), filePath);

        //从request获取id
        AttachmentDTO dto = new AttachmentDTO();

        dto.setDisplayName(file.getOriginalFilename());

        dto.setFilePath(tempFileName);

        dto.setType(AttachmentConstants.IMAGE);


        obj.setShopCertAttachment(dto);


        agrrNodeService.save(obj);
        return "redirect:/agrr/provider";
    }

    @PostMapping("/serviceForm")
    public String serviceForm(@ModelAttribute(value = "obj") AgrrNodeDTO obj, HttpServletRequest request, BindingResult bindingResult) throws IOException {
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

        obj.setShopCertAttachment(dto);

        MultipartFile otherfile = obj.getotherFile();
        SimpleDateFormat othersdf = new SimpleDateFormat("yyMMDDmmss");
        System.out.println("----->>" + otherfile.getOriginalFilename());
        String otherfileName = otherfile.getOriginalFilename();
        System.out.println("----->>>" + otherfileName);
        String otherfilesuffix = otherfileName.substring(otherfileName.lastIndexOf(".") + 1);
        String tempotherFileName = othersdf.format(new Date()) + "_1." + otherfilesuffix;
        Path otherfilePath = Paths.get(service.getUploadFolder(), tempotherFileName);
        Files.copy(otherfile.getInputStream(), otherfilePath);
        AttachmentDTO dto1 = new AttachmentDTO();
        dto1.setDisplayName(otherfile.getOriginalFilename());
        dto1.setFilePath(tempotherFileName);
        dto1.setType(AttachmentConstants.OTHER);
        obj.setShopBannerAttachment(dto1);

        //address
        if (obj.getNodeAddress() != null && obj.getNodeAddress().size() == 0
                && obj.getCountry() != null && obj.getCountry().size() > 0) {
            for (int i = 0; i < obj.getCountry().size(); i++) {
                String country = obj.getCountry().size() - 1 < i ? "" : obj.getCountry().get(i);
                String province = obj.getProvince().size() - 1 < i ? "" : obj.getProvince().get(i);
                String city = obj.getCity().size() - 1 < i ? "" : obj.getCity().get(i);
                String street = obj.getStreet().size() - 1 < i ? "" : obj.getStreet().get(i);
                String postCode = obj.getPostCode().size() - 1 < i ? "" : obj.getPostCode().get(i);
                obj.getNodeAddress().add(country + " " + province + " " + city + " " + street + "   邮编： " + postCode);
            }
        }
        agrrNodeService.save(obj);

        if (obj.getNodeAddress().size() > 0) {
            for (int i = 0; i < obj.getNodeAddress().size(); i++) {
                AddressDTO add = new AddressDTO();
                add.setText(obj.getNodeAddress().get(i));
                add.setAgrrNode(obj);
                addressService.saveOrUpdate(add);
            }
        }
        return "redirect:/agrr/service";
    }
}
