package ltd.ontsol.web.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import ltd.ontsol.core.service.LoginService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import ltd.ontsol.core.dto.DicSuggestionDTO;
import ltd.ontsol.core.dto.DictionaryDTO;
import ltd.ontsol.core.dto.LongText;
import ltd.ontsol.core.dto.LongTextTranslation;
import ltd.ontsol.core.service.DicSuggestionService;
import ltd.ontsol.core.service.DictionaryService;
import ltd.ontsol.core.service.StaffService;
import ltd.ontsol.core.util.TranslationUtils;
import ltd.ontsol.web.viewer.PaginationViewer;

/**
 * Created by cn40580 at 2016-10-10 10:02 AM.
 */
@RestController
@RequestMapping("/app/rest/dictionary/")
public class DictionaryWebService {

    private static final Log log = LogFactory.getLog(DictionaryWebService.class);
    @Inject
    StaffService staffService;
    @Inject
    DicSuggestionService suggestionService;
    @Inject
    private DictionaryService service;
    @Inject
    private LoginService Loginservice;

    @RequestMapping(value = "pagination/{pageCount}/{pageSize}/",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<PaginationViewer> pagination(@RequestBody DictionaryDTO dto, @PathVariable("pageCount") Integer pageCount,
                                                       @PathVariable("pageSize") Integer pageSize) {
        List<DictionaryDTO> uds = service.findAll(dto);
        if (uds.size() == 0)
            return new ResponseEntity<>(new PaginationViewer(new ArrayList<>(), pageCount, pageSize), HttpStatus.OK);

        //用于存储查询过滤后的结果
        List<DictionaryDTO> newDictionaryDTOList = new ArrayList<>();
        //如果前端没有传字典过来
        LongText titleLongText = dto.getTitle();
        if (titleLongText == null) {
            newDictionaryDTOList = uds;
        } else {
            for (DictionaryDTO queryDTO : uds) {
                LongText queryLongText = queryDTO.getTitle();
                Set<LongTextTranslation> queryLongTextTranslation = queryLongText.getTranslations();
                for (LongTextTranslation longTextTranslation : queryLongTextTranslation) {
                    //数据库查询出来的字典
                    String text = longTextTranslation.getText();

                    if (null != titleLongText) {
                        Set<LongTextTranslation> longTextTranslations = titleLongText.getTranslations();
                        for (LongTextTranslation _longTextTranslation : longTextTranslations) {
                            //前端带过来的字典
                            String _text = _longTextTranslation.getText();
                            if (text.contains(_text)) {
                                newDictionaryDTOList.add(queryDTO);
                            }
                        }
                    }
                }

            }
        }


        PaginationViewer page = new PaginationViewer(newDictionaryDTOList, pageCount, pageSize);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @RequestMapping(value = "all",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<PaginationViewer> findAll() {
        List<DictionaryDTO> uds = service.findAllByOrderById();

        if (uds.size() == 0)
            return new ResponseEntity<>(new PaginationViewer(new ArrayList<>(), 1, 1000), HttpStatus.OK);

        PaginationViewer page = new PaginationViewer(uds, 1, 1000);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }


    @RequestMapping(
            value = "saveOrUpdate",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<DictionaryDTO> saveOrUpdate(@RequestBody DictionaryDTO dto) throws Exception {

        DictionaryDTO parentDictionaryDTO = dto.getParentDic();
        Set<DicSuggestionDTO> suggestions = dto.getSuggestions();
        DictionaryDTO dto1 = new DictionaryDTO();
        dto1.setId(dto.getId());
        for(DicSuggestionDTO suggestion : suggestions){
            suggestion.setDictionary(dto1);
        }
        //如果父类的id为0 或为空
        if (null != parentDictionaryDTO) {
            Long parentId = dto.getParentDic().getId();
            if (null == parentId || parentId == 0) {
                dto.setParentDic(null);
            }
        }
        //dto.getSuggestions();
        TranslationUtils.putTranslation(dto.getTitle());
        TranslationUtils.putTranslation(dto.getDesc());
        if (dto.getId() != null && dto.getId() > 0) {
            DictionaryDTO oldDictionaryDTO = service.findById(dto.getId());
//            service.findRoot()
            BeanUtils.copyProperties(dto, oldDictionaryDTO);
        }
        dto = service.saveOrUpdate(dto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping(value = "delete/{id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (null == id || id < 1) {
            return new ResponseEntity<>("error", HttpStatus.OK);
        }
        int cnt = service.findDictionaryChildren(id);
        if (cnt > 0) {
            return new ResponseEntity<>("请先删除子节点，子节点数量" + cnt + "个。", HttpStatus.OK);
        }
        service.delete(id);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/test/{data}")
    public ResponseEntity<Map> testForm(@PathParam("data") String success) {
        Map<String, Object> result = new HashMap<>();

        result.put("message", "");
        result.put("status", success);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping(value = "ajax/suggestionForm",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE}
    )
    public ResponseEntity<Map> validateForm(HttpServletRequest servletRequest) {
        Map<String, Object> result = new HashMap<>();
        String staffCode = servletRequest.getParameter("staffCode");
        String text = servletRequest.getParameter("text");
        String staffName = servletRequest.getParameter("staffName");
        String dicId = servletRequest.getParameter("dicId");
        Integer count = Loginservice.countByStaffName(staffCode, staffName);
        if (count > 0) {
            DictionaryDTO dic = service.findById(Long.parseLong(dicId));
            DicSuggestionDTO dto = new DicSuggestionDTO();
            dto.setDictionary(dic);
            dto.setCreatedDate(new Date());
            dto.setStaffCode(staffCode);
            dto.setStaffName(staffName);
            dto.setCheckFlag(true);
            dto.setText(text);
            suggestionService.save(dto);
            result.put("message", "");
            result.put("status", "success");
            result.put("suggId", dto.getId());
        } else {
            result.put("message", "用户验证不通过");
            result.put("status", "failed");
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @PostMapping(value = "ajax/Staff",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE}
    )
    public ResponseEntity<Map> validateStaff(HttpServletRequest servletRequest) {
        Map<String, Object> result = new HashMap<>();
        String staffCode = servletRequest.getParameter("staffCode");
        String staffName = servletRequest.getParameter("staffName");
        Integer count = Loginservice.countByStaffName(staffCode, staffName);
        if (count > 0) {
            result.put("message", "");
            result.put("status", "success");
        } else {
            result.put("message", "用户验证不通过");
            result.put("status", "failed");
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
