package ltd.ontsol.web.rest;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import ltd.ontsol.core.dto.UserDTO;
import ltd.ontsol.core.service.UserService;
import ltd.ontsol.core.validator.UserValidator;
import ltd.ontsol.web.viewer.PaginationViewer;

/**
 * Created by cn40580 at 2016-10-10 10:02 AM.
 */
@RestController
@RequestMapping("/app/rest/user/")
public class UserWebService {

    private static final Log log = LogFactory.getLog(UserWebService.class);

    @Inject
    private UserService service;

    @Autowired
    private UserValidator validator;


    @RequestMapping(
            value = "saveOrUpdate",
            method = RequestMethod.POST,
            produces = {MediaType.ALL_VALUE})
    @Timed
    public ResponseEntity<UserDTO> saveOrUpdate(@RequestBody UserDTO dto, BindingResult bindingResult) throws Exception {
        validator.validate(dto, bindingResult);
        /*if (bindingResult.hasErrors()) {
            dto.setErrors(bindingResult.getFieldErrors());
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }*/
        service.saveOrUpdate(dto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping(value = "all",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> us = service.findAll();
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
    public ResponseEntity<PaginationViewer> pagination(@RequestBody UserDTO dto,
                                                       @PathVariable("pageCount") Integer pageCount,
                                                       @PathVariable("pageSize") Integer pageSize) {
        List<UserDTO> uds = service.findAll(dto);

        if (uds.size() == 0)
            return new ResponseEntity<>(new PaginationViewer(uds, pageCount, pageSize), HttpStatus.OK);

        List<UserDTO> rs = uds.stream()
                .map(u -> {
                    u.setPassword("");
                    return u;
                }).collect(Collectors.toList());
        PaginationViewer page = new PaginationViewer(rs, pageCount, pageSize);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }
}
