package ltd.ontsol.web.rest;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import ltd.ontsol.core.constants.ArticleConstants;
import ltd.ontsol.core.constants.PageConstants;
import ltd.ontsol.core.dto.ArticleDTO;
import ltd.ontsol.core.service.ArticleService;
import ltd.ontsol.core.util.TranslationUtils;
import ltd.ontsol.web.viewer.PaginationViewer;

@RestController
@RequestMapping("/app/rest/news/")
public class NewsWebService {
    private static final Log log = LogFactory.getLog(NewsWebService.class);

    @Inject
    private ArticleService service;

    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST, produces = {MediaType.ALL_VALUE})
    @Timed
    public ResponseEntity<ArticleDTO> saveOrUpdate(@RequestBody ArticleDTO dto, Principal principal) throws Exception {
        dto.setCreatedBy(principal.getName());

        TranslationUtils.putTranslation(dto.getShortContent());
        TranslationUtils.putTranslation(dto.getTitle());
        TranslationUtils.putTranslation(dto.getContent());

        service.saveOrUpdate(dto);
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

    @RequestMapping(value = "pagination/{pageCount}/{pageSize}/",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<PaginationViewer> pagination(@RequestBody ArticleDTO dto,
                                                       @PathVariable("pageCount") Integer pageCount,
                                                       @PathVariable("pageSize") Integer pageSize) {
        List<ArticleDTO> uds = service.findAll(dto);

        if (uds.size() == 0)
            return new ResponseEntity<>(new PaginationViewer(new ArrayList<>(), pageCount, pageSize), HttpStatus.OK);

        PaginationViewer page = new PaginationViewer(uds, pageCount, pageSize);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @RequestMapping(value = "pagination/front",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<Map<String, Object>> paginationForFront(@RequestParam("pagecount") Integer pageCount,
                                                                  @RequestParam("type") ArticleConstants type) {
        List<ArticleDTO> uds = service.findAllByType(type);
        Map<String, Object> result = new HashMap<>();

        if (uds.size() == 0)
            return new ResponseEntity<>(result, HttpStatus.OK);

        uds = uds.stream().skip((pageCount - 1) * PageConstants.PAGE_SIZE).limit(PageConstants.PAGE_SIZE).collect(Collectors.toList());
        result.put("items", uds.size() == 0 ? "no data" : uds);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
