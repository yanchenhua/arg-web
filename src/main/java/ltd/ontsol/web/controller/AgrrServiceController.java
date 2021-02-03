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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.codahale.metrics.annotation.Timed;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

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
    @GetMapping("/login")
    public String agrrLoginIndex(Model model) {
        Map<String, Object> objs = new HashMap<>();
        objs.put("obj", questionService.findAllByType(QuestionTypeConstants.PROVIDER));
        model.addAllAttributes(objs);
        return "/front/agrr/login";
    }
//    @GetMapping("/InfoPage")
//    public String InfoPageIndex(Model model) {
//        Map<String, Object> objs = new HashMap<>();
//        objs.put("obj", questionService.findAllByType(QuestionTypeConstants.PROVIDER));
//        model.addAllAttributes(objs);
//        return "/front/agrr/InfoPage";
//    }
    @GetMapping("/providerAppli")
    public String providerAppliIndex(Model model) {
        Map<String, Object> objs = new HashMap<>();
        objs.put("obj", questionService.findAllByType(QuestionTypeConstants.PROVIDER));
        model.addAllAttributes(objs);
        return "/front/agrr/providerAppli";
    }

    @GetMapping("/serviceAppli")
    public String serviceAppliIndex(HttpServletRequest request,Model model) {
        Map<String, Object> objs = new HashMap<>();
        objs.put("obj", questionService.findAllByType(QuestionTypeConstants.PROVIDER));
        model.addAllAttributes(objs);
        Cookie[] cookies =  request.getCookies();


        return "/front/agrr/serviceAppli";
    }
    @GetMapping("/InfoPage")
    public  String InfoPageIndex(HttpServletRequest request,Model model){
        Map<String, Object> objs = new HashMap<>();
        model.addAllAttributes(objs);
        Cookie[] cookies =  request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("username")){
                    return "/front/agrr/InfoPage";
                }
            }
        }
        return "redirect:/agrr/login";
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
    @PostMapping(value = "userLogin",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE}
    )
    public String userLogin(HttpServletRequest servletRequest,HttpServletResponse response) {
        Map<String, Object> result = new HashMap<>();
        String username = servletRequest.getParameter("username");
        String password = servletRequest.getParameter("password");

        Integer count = agrrNodeService.countByLoginInfo(username, password);
        if (count > 0) {
            result.put("message", "");
            result.put("status", "success");
            Cookie cookie0 = new Cookie("username",username);
            Cookie cookie1 = new Cookie("password",password);
            response.addCookie(cookie0);
            response.addCookie(cookie1);

        } else {
            result.put("message", "用户验证不通过");
            result.put("status", "failed");
        }
        return "redirect:/agrr/InfoPage";
        //return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @PostMapping(value = "validateUserid",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    public ResponseEntity<Map> validateUserid(HttpServletRequest servletRequest) {
        Map<String, Object> result = new HashMap<>();
        String username = servletRequest.getParameter("username");
        Integer count = agrrNodeService.countByUsername(username);
        if (count > 0) {
            result.put("message", "用户名已存在");
            result.put("message_en", "The user name already exists");
            result.put("status", "fail");
        }else{
            result.put("status", "success");
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @PostMapping(value = "returnInfo",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE}
    )
    public ResponseEntity<AgrrNodeDTO> returnInfo(HttpServletRequest servletRequest,HttpServletResponse response) {
        Map<String, Object> result = new HashMap<>();
        String username = servletRequest.getParameter("username");
        String password = servletRequest.getParameter("password");

        AgrrNodeDTO dto = agrrNodeService.findByLoginInfo(username,password).get(0);
        System.out.println(dto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
        //return new ResponseEntity<>(result, HttpStatus.OK);
    }
//    @PostMapping(value = "returnInfo",
//            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
//    public String returnInfo(HttpServletRequest servletRequest){
//        String username = servletRequest.getParameter("username");
//        String password = servletRequest.getParameter("password");
//        AgrrNodeDTO dto = agrrNodeService.findByLoginInfo(username,password).get(0);
//        return "";
//    }
    @PostMapping("/serviceForm")
    public String serviceForm(@ModelAttribute(value = "obj") AgrrNodeDTO obj, HttpServletRequest request, BindingResult bindingResult) throws IOException {
       String username = "";
       String password = "";
        Cookie[] cookies =  request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("username")){
                    username = cookie.getValue();
                }
                if(cookie.getName().equals("password")){
                    password = cookie.getValue();
                }
            }
        }
        if(username!=""){
            AgrrNodeDTO obj1 = agrrNodeService.findByLoginInfo(username,password).get(0);
            obj.setId(obj1.getId());
        }
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

        MultipartFile contactfile = obj.getContactfile();
        SimpleDateFormat contactsdf = new SimpleDateFormat("yyMMDDmmss");
        System.out.println("----->>" + contactfile.getOriginalFilename());
        String contactfileName = contactfile.getOriginalFilename();
        System.out.println("----->>>" + contactfileName);
        String contactfilesuffix = contactfileName.substring(contactfileName.lastIndexOf(".") + 1);
        String contactFileName = contactsdf.format(new Date()) + "_2." + contactfilesuffix;
        Path contactfilePath = Paths.get(service.getUploadFolder(), contactFileName);
        Files.copy(contactfile.getInputStream(), contactfilePath);
        AttachmentDTO dto2 = new AttachmentDTO();
        dto2.setDisplayName(contactfile.getOriginalFilename());
        dto2.setFilePath(contactFileName);
        dto2.setType(AttachmentConstants.OTHER);
        obj.setShopContactAttachment(dto2);

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
        if(username==""){
            agrrNodeService.save(obj);
        }else{
            agrrNodeService.saveOrUpdate(obj);
            AgrrNodeDTO obj1 = agrrNodeService.findByLoginInfo(username,password).get(0);
            Long id = obj1.getId();
            addressService.deleteByAgrrId(id);
        }
        //agrrNodeService.save(obj);

        if (obj.getNodeAddress().size() > 0) {
            for (int i = 0; i < obj.getNodeAddress().size(); i++) {
                AddressDTO add = new AddressDTO();
                add.setText(obj.getNodeAddress().get(i));
                String[] arry = obj.getNodeAddress().get(i).split(" ");
                add.setAgrrNode(obj);
                add.setCountry(arry[0]);
                add.setProvince(arry[1]);
                add.setCity(arry[2]);
                add.setStreet(arry[3]);
                addressService.saveOrUpdate(add);
            }
        }
        return "redirect:/agrr/login";
    }
    @PostMapping(value = "/uploadContact",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})

    public String uploadContact(HttpServletRequest servletRequest) throws IOException {

         MultipartHttpServletRequest multipartRequest =
                 WebUtils.getNativeRequest(servletRequest, MultipartHttpServletRequest.class);
        Long id =  Long.parseLong(servletRequest.getParameter("contactid"));
        System.out.println("---->>>>"+id);
         MultipartFile contactFile = multipartRequest.getFile("contactfile01");
//        MultipartFile contactFile = obj.getContactfile();
         SimpleDateFormat contactsdf = new SimpleDateFormat("yyMMDDmmss");
         String contactfileName = contactFile.getOriginalFilename();
         String contactfilesuffix = contactfileName.substring(contactfileName.lastIndexOf(".") + 1);
         String tempcontactFileName = contactsdf.format(new Date()) + "_1." + contactfilesuffix;
         Path contactfilePath = Paths.get(service.getUploadFolder(), tempcontactFileName);
         Files.copy(contactFile.getInputStream(), contactfilePath);
         AttachmentDTO dto = service.findById(id);
         dto.setDisplayName(contactFile.getOriginalFilename());
         dto.setFilePath(tempcontactFileName);
         dto.setType(AttachmentConstants.OTHER);
         service.saveOrUpdate(dto);
         //return new ResponseEntity<>("success", HttpStatus.OK);
        return "redirect:/manage/service/add.html?id="+servletRequest.getParameter("serviceid");
        //return "redirect:/manage/service";
    }
//    @PostMapping(value = "/setVerStatus",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
//    public String setVerStatus(HttpServletRequest servletRequest){
//        Long id =  Long.parseLong(servletRequest.getParameter("id"));
//        String status = servletRequest.getParameter("verstatus");
//        AgrrNodeDTO dto = agrrNodeService.findById(id);
//        dto.setVerstatus(status);
//        agrrNodeService.saveOrUpdate(dto);
//        return "";
//    }
//    @PostMapping(value = "page",
//            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE}
//    )
//    public String setVerStatus(HttpServletRequest servletRequest,HttpServletResponse response) {
////        Long id =  Long.parseLong(servletRequest.getParameter("id"));
////            String status = servletRequest.getParameter("verstatus");
////        AgrrNodeDTO dto = agrrNodeService.findById(id);
////        dto.setVerstatus(status);
////        agrrNodeService.saveOrUpdate(dto);
//        return "success";
//    }
    @PostMapping(value = "setVerStatus",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
public String setVerStatus(HttpServletRequest servletRequest) {
        Long id =  Long.parseLong(servletRequest.getParameter("id"));
        String status = servletRequest.getParameter("verstatus");
        AgrrNodeDTO dto = agrrNodeService.findById(id);
        dto.setVerstatus(status);
        agrrNodeService.saveOrUpdate(dto);
    return "/manage/agrr/service";
}
    @RequestMapping(value = "checkStaff",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<Map> validateStaffLogin(HttpServletRequest servletRequest) {
        Map<String, Object> result = new HashMap<>();
        String username = servletRequest.getParameter("username");

        Integer count = agrrNodeService.countByUsername(username);
        if (count > 0) {
            result.put("message", "用户名已存在");
            result.put("message_en", "No duplicate registration");
            result.put("status", "fail");
        }
        else{
            result.put("status", "success");
            result.put("message", "success");
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
