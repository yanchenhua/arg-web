package ltd.ontsol.web.rest;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import ltd.ontsol.core.dto.AttachmentDTO;
import ltd.ontsol.core.dto.LongText;
import ltd.ontsol.core.service.AttachmentService;
import ltd.ontsol.core.util.TranslationUtils;

/**
 * Created by cn40580 at 2016-10-10 10:02 AM.
 */
@RestController
@RequestMapping("/app/rest/attachment/")
public class AttachmentWebService {

    private static final Log log = LogFactory.getLog(AttachmentWebService.class);

    @Inject
    private AttachmentService service;

    @RequestMapping(
            value = "saveOrUpdate",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<AttachmentDTO> saveOrUpdate(@RequestBody AttachmentDTO dto) throws Exception {
        LongText desc = dto.getDesc();
        if (null != desc) {
            TranslationUtils.putTranslation(dto.getDesc());
        }
        LongText desc2 = dto.getDesc2();
        if (null != desc2)
            TranslationUtils.putTranslation(desc2);
        dto = service.saveOrUpdate(dto);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


}
