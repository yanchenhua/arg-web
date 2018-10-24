package ltd.ontsol.web.rest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;

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

import ltd.ontsol.core.dto.AboutDTO;
import ltd.ontsol.core.service.AboutService;
import ltd.ontsol.core.util.TranslationUtils;
import ltd.ontsol.web.viewer.PaginationViewer;

/**
 * Created by cn40580 at 2016-10-10 10:02 AM.
 */
@RestController
@RequestMapping("/app/rest/about/")
public class AboutWebService {

    private static final Log log = LogFactory.getLog(AboutWebService.class);

    @Inject
    private AboutService service;

    /**
     * GET  /rest/users/:login -> get the "login" user.
     */
    @RequestMapping(value = "all",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<PaginationViewer> findAll() {
        List<AboutDTO> abouts = Stream.of(service.findOne()).collect(Collectors.toList());
        PaginationViewer page = new PaginationViewer(abouts, 1, 1);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }


    @RequestMapping(
            value = "saveOrUpdate",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<AboutDTO> saveOrUpdate(@RequestBody AboutDTO dto) throws Exception {
        TranslationUtils.putTranslation(dto.getHeadDesc1());
        TranslationUtils.putTranslation(dto.getHeadDesc2());
        TranslationUtils.putTranslation(dto.getAboutText());
        TranslationUtils.putTranslation(dto.getCusText());
        TranslationUtils.putTranslation(dto.getParam1());
        TranslationUtils.putTranslation(dto.getUnit1());
        TranslationUtils.putTranslation(dto.getCount1());
        TranslationUtils.putTranslation(dto.getParam2());
        TranslationUtils.putTranslation(dto.getUnit2());
        TranslationUtils.putTranslation(dto.getCount2());
        TranslationUtils.putTranslation(dto.getParam3());
        TranslationUtils.putTranslation(dto.getUnit3());
        TranslationUtils.putTranslation(dto.getCount3());
        TranslationUtils.putTranslation(dto.getParam4());
        TranslationUtils.putTranslation(dto.getUnit4());
        TranslationUtils.putTranslation(dto.getCount4());
        dto = service.saveOrUpdate(dto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
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
