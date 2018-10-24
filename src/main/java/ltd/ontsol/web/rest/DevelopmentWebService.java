package ltd.ontsol.web.rest;

import java.util.List;

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

import ltd.ontsol.core.constants.PageConstants;
import ltd.ontsol.core.dto.DevelopmentDTO;
import ltd.ontsol.core.service.DevelopmentService;
import ltd.ontsol.core.util.TranslationUtils;
import ltd.ontsol.web.viewer.PaginationViewer;

/**
 * Created by cn40580 at 2016-10-10 10:02 AM.
 */
@RestController
@RequestMapping("/app/rest/development/")
public class DevelopmentWebService {

    private static final Log log = LogFactory.getLog(DevelopmentWebService.class);

    @Inject
    private DevelopmentService service;


    @RequestMapping(
            value = "saveOrUpdate",
            method = RequestMethod.POST,
            produces = {MediaType.ALL_VALUE})
    @Timed
    public ResponseEntity<DevelopmentDTO> saveOrUpdate(@RequestBody DevelopmentDTO dto) throws Exception {
        TranslationUtils.putTranslation(dto.getDevelopText());

        service.saveOrUpdate(dto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping(value = "all",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<List<DevelopmentDTO>> findAll() {
        List<DevelopmentDTO> us = service.findAll();
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

    @RequestMapping(value = "pagination/{pageCount}/{pageSize}/",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<PaginationViewer> pagination(@RequestBody DevelopmentDTO dto,
                                                       @PathVariable("pageCount") Integer pageCount) {
        List<DevelopmentDTO> uds = service.findAll();

        if (uds.size() == 0)
            return new ResponseEntity<>(new PaginationViewer(null, pageCount, PageConstants.PAGE_SIZE), HttpStatus.OK);

        PaginationViewer page = new PaginationViewer(uds, pageCount, PageConstants.PAGE_SIZE);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }
}
