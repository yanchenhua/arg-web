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

import ltd.ontsol.core.dto.HomeDTO;
import ltd.ontsol.core.service.HomeService;
import ltd.ontsol.core.util.TranslationUtils;
import ltd.ontsol.web.viewer.PaginationViewer;

/**
 * Created by cn40580 at 2016-10-10 10:02 AM.
 */
@RestController
@RequestMapping("/app/rest/home/")
public class HomeWebService {

    private static final Log log = LogFactory.getLog(HomeWebService.class);

    @Inject
    private HomeService service;

    /**
     * GET  /rest/users/:login -> get the "login" user.
     */
    @RequestMapping(value = "all",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<PaginationViewer> findAll() {
        List<HomeDTO> Homes = Stream.of(service.findOne()).collect(Collectors.toList());
        PaginationViewer page = new PaginationViewer(Homes, 1, 10);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }


    @RequestMapping(
            value = "saveOrUpdate",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<HomeDTO> saveOrUpdate(@RequestBody HomeDTO dto) throws Exception {

        TranslationUtils.putTranslation(dto.getWebTitle());
        TranslationUtils.putTranslation(dto.getWebDesc());
        TranslationUtils.putTranslation(dto.getKeywords());
        TranslationUtils.putTranslation(dto.getCopyRight());
        TranslationUtils.putTranslation(dto.getCaseNum());
        TranslationUtils.putTranslation(dto.getLegalContent());
        TranslationUtils.putTranslation(dto.getLegalTitle());
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
