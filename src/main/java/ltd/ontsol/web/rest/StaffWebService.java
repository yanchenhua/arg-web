package ltd.ontsol.web.rest;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import ltd.ontsol.core.service.StaffService;

/**
 * Created by cn40580 at 2016-10-10 10:02 AM.
 */
@RestController
@RequestMapping("/app/rest/ltd.ontsol.web.soap.obj.staff/")
public class StaffWebService {

    private static final Log log = LogFactory.getLog(StaffWebService.class);

    @Inject
    private StaffService service;

    /**
     * GET  /rest/users/:login -> get the "login" user.
     */
    @RequestMapping(value = "clearAll",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<String> clearAll() {
        service.deleteAll();
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
