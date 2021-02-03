package ltd.ontsol.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.codahale.metrics.annotation.Timed;

import ltd.ontsol.core.constants.AttachmentConstants;
import ltd.ontsol.core.dto.AttachmentDTO;
import ltd.ontsol.core.service.AttachmentService;

/**
 * Created by cn40580 at 2018-06-17 11:21 AM.
 */
@Controller
@RequestMapping("/uploads")
public class FileUploadController {

    private static final Log log = LogFactory.getLog(FileUploadController.class);

    private final ResourceLoader resourceLoader;

    @Inject
    AttachmentService service;

    @Autowired
    public FileUploadController(HttpServletResponse res, ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{filename:.+}",
            produces = {MediaType.IMAGE_PNG_VALUE})
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable String filename) {

        try {
            System.out.println("->>>>>>>>>>>>>>"+filename);
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(service.getUploadFolder(), filename).toString()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/stream/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> downloadFile(@PathVariable String filename) {
        try {
            Resource file = resourceLoader.getResource("file:" + Paths.get(service.getUploadFolder(), filename).toString());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/pdf/{filename:.+}",
            produces = {MediaType.APPLICATION_PDF_VALUE})
    @ResponseBody
    public ResponseEntity<?> readPdfOnline(@PathVariable String filename) {
        try {
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(service.getUploadFolder(), filename).toString()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/upload1")
    @ResponseBody
    public AttachmentDTO upload1(@RequestParam("file") MultipartFile file,
                                 HttpServletRequest request) throws IOException {
        log.info(String.format("[文件类型] - [{%s}]", file.getContentType()));
        log.info(String.format("[文件名称] - [{%s}]", file.getOriginalFilename()));
        log.info(String.format("[文件大小] - [{%s}]", file.getSize()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMDDmmss");
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String tempFileName = sdf.format(new Date()) + "." + suffix;
        Path filePath = Paths.get(service.getUploadFolder(), tempFileName);
        log.info(String.format("[文件上传路径] - [{%s}]", filePath.toString()));
        Files.copy(file.getInputStream(), filePath);
        //从request获取id
        String idStr = request.getParameter("id");
        String type = request.getParameter("type");
        AttachmentDTO dto = new AttachmentDTO();
        if ((null != idStr && idStr.length() > 0 && !idStr.equals("0")) || type.equals("HOME")) {
            if (!idStr.equals("0")) {
                dto = service.findById(Long.parseLong(idStr));
            }
            dto.setDisplayName(file.getOriginalFilename());
            dto.setFilePath(tempFileName);
            if (type.equals("HOME")) {
                dto.setType(AttachmentConstants.HOME);
            } else {
                dto.setType(AttachmentConstants.IMAGE);
            }

            dto = service.saveOrUpdate(dto);
        } else {
            dto.setDisplayName(file.getOriginalFilename());
            dto.setFilePath(tempFileName);
        }

        return dto;
    }

    @Deprecated
    @PostMapping("/upload2")
    @ResponseBody
    public List<Map<String, String>> upload2(@RequestParam("file") MultipartFile[] files) throws IOException {
        if (files == null || files.length == 0) {
            return null;
        }
        List<Map<String, String>> results = new ArrayList<>();
        for (MultipartFile file : files) {
            file.transferTo(new File("F:\\app\\chapter16\\" + file.getOriginalFilename()));
            Map<String, String> map = new HashMap<>(16);
            map.put("contentType", file.getContentType());
            map.put("fileName", file.getOriginalFilename());
            map.put("fileSize", file.getSize() + "");
            results.add(map);
        }
        return results;
    }

    @Deprecated
    @PostMapping("/upload3")
    @ResponseBody
    public void upload2(String base64) throws IOException {
        final File tempFile = new File("F:\\app\\chapter16\\test.jpg");
        String[] d = base64.split("base64,");
        final byte[] bytes = Base64Utils.decodeFromString(d.length > 1 ? d[1] : d[0]);
        FileCopyUtils.copy(bytes, tempFile);

    }

    @Deprecated
    @PostMapping("/upload4")
    @ResponseBody
    public void upload4(@RequestParam("upload") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info(String.format("[文件类型] - [{%s}]", file.getContentType()));
        log.info(String.format("[文件名称] - [{%s}]", file.getOriginalFilename()));
        log.info(String.format("[文件大小] - [{%s}]", file.getSize()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMDDmmss");
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String tempFileName = sdf.format(new Date()) + "." + suffix;
        Path filePath = Paths.get(service.getUploadFolder(), tempFileName);
        log.info(String.format("[文件上传路径] - [{%s}]", filePath.toString()));
        Files.copy(file.getInputStream(), filePath);
        System.out.println("mp4 ---->>>>>>"+suffix);
//        if(suffix.equals("mp4")){
//            System.out.println("mp4 ---->>>>>>");
//            response.setContentType("video/mp4;charset=UTF-8");
//        }else{
//            response.setContentType("text/html;charset=UTF-8");
//        }
        response.setContentType("text/html;charset=UTF-8");
        String callback = request.getParameter("CKEditorFuncNum");
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        String url = "/uploads/" + tempFileName;
        out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + url + "',''" + ")");
        out.println("</script>");
        out.flush();
        out.close();
    }

    @RequestMapping(value = "delete/{id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
