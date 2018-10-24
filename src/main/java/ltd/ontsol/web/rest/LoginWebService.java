package ltd.ontsol.web.rest;

import com.codahale.metrics.annotation.Timed;
import ltd.ontsol.core.dto.DicSuggestionDTO;
import ltd.ontsol.core.dto.GlobalDTO;
import ltd.ontsol.core.dto.LoginDTO;
import ltd.ontsol.core.dto.LongText;
import ltd.ontsol.core.service.LoginService;
import ltd.ontsol.web.viewer.PaginationViewer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/app/rest/login/")
public class LoginWebService {
    @Inject
    private LoginService service;

    @PostMapping(value = "ajax/loginForm",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE}
    )
    @Timed
    public ResponseEntity<Map> validateForm(HttpServletRequest servletRequest) {
        Map<String, Object> result = new HashMap<>();
        String staffTel = servletRequest.getParameter("staffTel");
        String staffEmail = servletRequest.getParameter("staffEmail");
        String userid = servletRequest.getParameter("userid");
        String staffName = servletRequest.getParameter("staffName");
        String staffCode = servletRequest.getParameter("staffCode");
        String staffOrg = servletRequest.getParameter("staffOrg");
        String apartment = servletRequest.getParameter("apartment");
        String password = servletRequest.getParameter("password");
        Integer status = Integer.valueOf(servletRequest.getParameter("status"));
        String format = "\\p{Alpha}\\w{2,15}[@][a-z0-9]{3,}[.]\\p{Lower}{2,}";

            System.out.println("---->>>>"+staffCode);
            LoginDTO dto = new LoginDTO();
            dto.setstaffEmail(staffEmail);
            dto.setstaffName(staffName);
            dto.setstaffCode(staffCode);
            dto.setstaffOrg(staffOrg);
            dto.setapartment(apartment);
            dto.setstaffTel(staffTel);
            dto.setPassword(password);
            dto.setUserid(userid);
            dto.setStatus(status);
            service.saveOrUpdate(dto);
            result.put("message", "信息已提交，请等待审批");
            result.put("message_en", "Your registration has been submited. Please wait for approval. ");
            result.put( "status","success");


        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @PostMapping(value = "pagination/{pageCount}/{pageSize}/",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<PaginationViewer> pagination(@RequestBody LoginDTO dto,
                                                       @PathVariable("pageCount") Integer pageCount,
                                                       @PathVariable("pageSize") Integer pageSize) {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
        List<LoginDTO> uds = service.findAll(dto);
        if (uds.size() == 0)
            return new ResponseEntity<>(new PaginationViewer(null, pageCount, pageSize), HttpStatus.OK);
        //用于存储查询过滤后的结果
        List<LoginDTO> newDictionaryDTOList = new ArrayList<>();
        String nameText = dto.getstaffName();
        if (nameText == null) {
            newDictionaryDTOList = uds;
        }else{

            for (LoginDTO queryDTO : uds) {
                String textname = queryDTO.getstaffName();
                System.out.println("nameText="+nameText);
                System.out.println("textname="+textname);
                if (textname.contains(nameText)) {
                    newDictionaryDTOList.add(queryDTO);
                }
            }
        }

        PaginationViewer page = new PaginationViewer(newDictionaryDTOList, pageCount, pageSize);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }
    @RequestMapping(value = "delete/{id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        System.out.println(id+"---------------------->>>>>>");
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    @RequestMapping(value = "pass/{id}/{status}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<String> pass(@PathVariable Long id,@PathVariable Long status) {
        service.pass(id,status);
        System.out.println(id+"---------------------->>>>>>");
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    @RequestMapping(value = "staff",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<Map> validateStaff(HttpServletRequest servletRequest) {
        Map<String, Object> result = new HashMap<>();
        String staffCode = servletRequest.getParameter("staffCode");
        String staffName = servletRequest.getParameter("staffName");
        Integer count = service.countByStaffName(staffCode, staffName);
        if (count > 0) {
            result.put("message", "");
            result.put("status", "success");
        } else {
            result.put("message", "用户验证不通过");
            result.put("status", "failed");
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "checkuserid",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<Map> validateUserid(HttpServletRequest servletRequest) {
        Map<String, Object> result = new HashMap<>();
        String userid = servletRequest.getParameter("userid");
        Integer count = service.countByUserid(userid);
        if (count > 0) {
            result.put("message", "用户名已存在");
            result.put("message_en", "The user name already exists");
            result.put("status", "fail");
        }else{
            result.put("status", "success");
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "checkStaff",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<Map> validateStaffLogin(HttpServletRequest servletRequest) {
        Map<String, Object> result = new HashMap<>();
        String staffCode = servletRequest.getParameter("staffCode");
        String staffName = servletRequest.getParameter("staffName");
        String staffOrg = servletRequest.getParameter("staffOrg");
        String apartment = servletRequest.getParameter("apartment");
        Integer count = service.countByAll(staffCode,staffName,staffOrg,apartment);
        if (count > 0) {
            result.put("message", "请勿重复注册");
            result.put("message_en", "No duplicate registration");
            result.put("status", "fail");
        }
        else{
            result.put("status", "success");
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

