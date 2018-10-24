package ltd.ontsol.web.rest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.codahale.metrics.annotation.Timed;

import ltd.ontsol.core.dto.DicSuggestionDTO;
import ltd.ontsol.core.service.DicSuggestionService;
import ltd.ontsol.web.viewer.PaginationViewer;

/**
 * Created by cn40580 at 2016-10-10 10:02 AM.
 */
@RestController
@RequestMapping("/app/rest/suggestion/")
public class SuggestionWebService {

    private static final Log log = LogFactory.getLog(SuggestionWebService.class);

    @Inject
    private DicSuggestionService service;

    /**
     * GET  /rest/users/:login -> get the "login" user.
     */
    @RequestMapping(value = "all",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<PaginationViewer> findAll() {
        System.out.println("-------->>>findAll");
        PaginationViewer page = new PaginationViewer(service.findAll(), 1, 5);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @RequestMapping(value = "new",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<PaginationViewer> findNewSug() {
        System.out.println("-------->>>findAll");
        PaginationViewer page = new PaginationViewer(service.findNewSug(), 1, 5);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }


    @RequestMapping(value = "pagination/{pageCount}/{pageSize}/",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<PaginationViewer> pagination(@RequestBody DicSuggestionDTO dto,
                                                       @PathVariable("pageCount") Integer pageCount,
                                                       @PathVariable("pageSize") Integer pageSize) {

        List<DicSuggestionDTO> uds = service.findAll(dto);
        if (uds.size() == 0)
            return new ResponseEntity<>(new PaginationViewer(null, pageCount, pageSize), HttpStatus.OK);

        PaginationViewer page = new PaginationViewer(uds, pageCount, pageSize);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @RequestMapping(
            value = "saveOrUpdate",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<DicSuggestionDTO> saveOrUpdate(@RequestBody DicSuggestionDTO dto) throws Exception {
       // dto.setCreatedDate(new Date());
        DicSuggestionDTO dtoNew = service.findById(dto.getId());
        dtoNew.setText(dto.getText());
        dto = service.save(dtoNew);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping(value = "delete/{id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<Map> delete(@PathVariable Long id) {
        Map<String, Object> result = new HashMap<>();
        service.delete(id);
        //return new ResponseEntity<>("success", HttpStatus.OK);
        result.put("message", "success");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "check/{id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<String> check(@PathVariable Long id) {
        DicSuggestionDTO dto = service.findById(id);
        dto.setCheckFlag(true);
        service.save(dto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @RequestMapping(value = "uncheck/{id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<String> uncheck(@PathVariable Long id) {
        DicSuggestionDTO dto = service.findById(id);
        dto.setCheckFlag(false);
        service.save(dto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PostMapping(value = "reply",
                    produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_ATOM_XML_VALUE})
    public ResponseEntity<Map>reply(HttpServletRequest servletRequest){
        Map<String, Object> result = new HashMap<>();
        Long id =  Long.parseLong(servletRequest.getParameter("id"));
        DicSuggestionDTO dto = service.findById(id);
        dto.setReply(servletRequest.getParameter("mes"));
        service.save(dto);
        return  new ResponseEntity<>(result,HttpStatus.OK);
    }

}
