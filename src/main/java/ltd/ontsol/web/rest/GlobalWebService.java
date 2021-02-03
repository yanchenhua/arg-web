package ltd.ontsol.web.rest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import ltd.ontsol.core.constants.GlobalConstants;
import ltd.ontsol.core.dto.GlobalDTO;
import ltd.ontsol.core.dto.LongText;
import ltd.ontsol.core.dto.LongTextTranslation;
import ltd.ontsol.core.service.GlobalService;
import ltd.ontsol.core.util.TranslationUtils;
import ltd.ontsol.web.resource.GlobalResource;
import ltd.ontsol.web.viewer.PaginationViewer;

/**
 * Created by cn40580 at 2016-10-10 10:02 AM.
 */
@RestController
@RequestMapping("/app/rest/global/")
public class GlobalWebService {

    private static final Log log = LogFactory.getLog(GlobalWebService.class);

    @Inject
    private GlobalService service;

    @RequestMapping(
            value = "saveOrUpdate",
            method = RequestMethod.POST,
            produces = {MediaType.ALL_VALUE})
    @Timed
    public ResponseEntity<GlobalDTO> saveOrUpdate(@RequestBody GlobalDTO dto) throws Exception {
        TranslationUtils.putTranslation(dto.getCityName());
        TranslationUtils.putTranslation(dto.getAreaName());
        TranslationUtils.putTranslation(dto.getGlobalName());
        TranslationUtils.putTranslation(dto.getShopaddress());
        TranslationUtils.putTranslation(dto.getShopname());
        TranslationUtils.putTranslation(dto.getShopcontact());
        System.out.println("global add save");
        service.saveOrUpdate(dto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping(value = "all",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<List<GlobalDTO>> findAll() {
        List<GlobalDTO> us = service.findAll();
        return new ResponseEntity<>(us, HttpStatus.OK);
    }


    @RequestMapping(value = "delete/{id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    /**
     * GET  /rest/users/:login -> get the "login" user.
     */
    @RequestMapping(value = "all/{type}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<List<GlobalResource>> findAll(@PathVariable GlobalConstants type,
                                                        HttpServletRequest request) {
        System.out.println(request.getSession().getAttribute("lang_session").getClass());
        Locale locale = (Locale) request.getSession().getAttribute("lang_session");
        System.out.println(locale);

        if(type.name()=="SERVICE_NODE"){
            List<GlobalResource> globals = service.findAllGlobalResourceByType(type, locale == null ? Locale.getDefault() : locale);
            return new ResponseEntity<>(globals, HttpStatus.OK);
        }else{
            List<GlobalResource> globals = service.findAllGlobalResourceByType2(type, locale == null ? Locale.getDefault() : locale);
            return new ResponseEntity<>(globals, HttpStatus.OK);
        }


    }

    @RequestMapping(value = "all1/{type}/{province}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<List<GlobalResource>> findAll1(@PathVariable GlobalConstants type,@PathVariable("province") String province,
                                                        HttpServletRequest request) throws UnsupportedEncodingException {
        Locale locale = (Locale) request.getSession().getAttribute("lang_session");
        String province1 = URLDecoder.decode(province, "UTF-8");
        List<GlobalResource> globals = service.findAllGlobalResourceByType(type, locale == null ? Locale.getDefault() : locale);
        return new ResponseEntity<>(globals, HttpStatus.OK);
    }

    @RequestMapping(value = "pagination/{pageCount}/{pageSize}/",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<PaginationViewer> pagination(@RequestBody GlobalDTO dto,
                                                       @PathVariable("pageCount") Integer pageCount,
                                                       @PathVariable("pageSize") Integer pageSize) {
        List<GlobalDTO> uds = service.findAll(dto);
        System.out.println("pagination");
        if (uds.size() == 0)
            return new ResponseEntity<>(new PaginationViewer(new ArrayList<>(), pageCount, pageSize), HttpStatus.OK);

        //用于存储查询过滤后的结果
        List<GlobalDTO> newDictionaryDTOList = new ArrayList<>();
        //如果前端没有传字典过来
        LongText titleLongText = dto.getShopname();
        if (titleLongText == null) {
            newDictionaryDTOList = uds;
        } else {
            for (GlobalDTO queryDTO : uds) {
                LongText queryLongText = queryDTO.getShopname();
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

}
