package ltd.ontsol.web.controller;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ltd.ontsol.core.constants.AttachmentConstants;
import ltd.ontsol.core.constants.GlobalConstants;
import ltd.ontsol.core.dto.*;
import ltd.ontsol.core.service.*;
import ltd.ontsol.core.service.impl.LongTextServiceImpl;
import ltd.ontsol.core.util.IpUtil;
import ltd.ontsol.core.util.TranslationUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.thymeleaf.util.StringUtils;

import ltd.ontsol.core.constants.ArticleConstants;
import ltd.ontsol.core.constants.PageConstants;

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
    @Inject
    GlobalService globalService;

    @GetMapping("/tech")
    public String techIndex(Model model) {
        Map objs = new HashMap<>();
        objs.put("objs", productService.findAll()
                .stream().limit(12).collect(Collectors.toList()));
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

    @PostMapping("/upload")
    @ResponseBody
    public void importFile(HttpServletRequest request) throws IOException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> uploadFile = multipartRequest.getFiles("files");
        String fileName = uploadFile.get(0).getOriginalFilename();
        String fileType = "";
        if (fileName.lastIndexOf(".") != -1
                && fileName.lastIndexOf(".") + 1 < fileName.length()) {
            fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        try {
            InputStream fis = uploadFile.get(0).getInputStream();
            Workbook workbook = null;
            if (fileType.toLowerCase().equals("xlsx")) {
                workbook = new XSSFWorkbook(fis);
            } else if (fileType.toLowerCase().equals("xls")) {
                workbook = new HSSFWorkbook(fis);
            }
// 获取每个sheet表
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                Sheet sheet = workbook.getSheetAt(i);
// 获取每行
                for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
                    XSSFRow row = (XSSFRow) sheet.getRow(j);
                    if (row != null) {
// 获取每个单元格
                       //配置set方法
                        String[] method = new String[]{"setShopname","setShopcontact", "setShoptel", "setVerNumber", "setZipCode", "setShopaddress","setShoplongitude","setShoplatitude", "setType", "setGlobalName", "setCityName", "setAreaName"};
                        //String[] filedArry = new String[]{};
                        ArrayList<String> filedArry = new ArrayList();
                        GlobalDTO globalDTO = new GlobalDTO();
                        Class<GlobalDTO> clas = GlobalDTO.class;
                        Field[] fields = clas.getDeclaredFields();

                        //获取实体类的字段属性
                        for (int k = 0;k< fields.length;k++) {
                            if(fields[k].getName().equals("shoptype")||fields[k].getName().equals("globalBannerAttachment")){
                                continue;
                            }else{
                                System.out.println(fields[k].getName());
                                filedArry.add(fields[k].getName());
                            }

                        }
                        for (int k = 0; k < sheet.getRow(0)
                                .getPhysicalNumberOfCells(); k++) {
                            Cell cell = row.getCell(k);
// 设置返回值类型
                            cell.setCellType(Cell.CELL_TYPE_STRING);

                            String value = cell.getStringCellValue().replaceAll("'", "''");
                            System.out.println("method=="+method[k]);
                            System.out.println(clas.getDeclaredField(filedArry.get(k)).getType());
                            Method m = clas.getMethod(method[k], clas.getDeclaredField(filedArry.get(k)).getType());
                            String type = m.getParameterTypes()[0].getName();
                            if(type.equals("ltd.ontsol.core.dto.LongText")){
                                LongText longText = new LongText();
                                LongTextTranslation longTextTranslation = new LongTextTranslation();
                                longTextTranslation.setText(value);
                                longTextTranslation.setLongText(null);
                                longTextTranslation.setLanguage(new Locale("zh","CN"));
                                Set<LongTextTranslation> translations = new HashSet<>();
                                translations.add(longTextTranslation);
                                longText.setTranslations(translations);
                                m.invoke(globalDTO, longText);
                               // globalDTO.setShopname(longText);

                            }else if(type.equals("java.lang.Double")){
                                m.invoke(globalDTO, Double.parseDouble(value));
                            }else if(type.equals("ltd.ontsol.core.dto.AttachmentDTO")){
                                continue;
                            }else if(type.equals("ltd.ontsol.core.constants.GlobalConstants")){
                                GlobalConstants globalConstants = GlobalConstants.valueOf(value);
                                m.invoke(globalDTO, globalConstants);
                            }else{
                                m.invoke(globalDTO, (String)value);
                            }


                        }
                        TranslationUtils.putTranslation(globalDTO.getCityName());
                        TranslationUtils.putTranslation(globalDTO.getAreaName());
                        TranslationUtils.putTranslation(globalDTO.getGlobalName());
                        TranslationUtils.putTranslation(globalDTO.getShopaddress());
                        TranslationUtils.putTranslation(globalDTO.getShopname());
                        TranslationUtils.putTranslation(globalDTO.getShopcontact());
                        globalService.saveOrUpdate(globalDTO);
                    }
                }
                fis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void importServiceNode(GlobalDTO globalDTO) {
        globalService.saveOrUpdate(globalDTO);
    }

}
