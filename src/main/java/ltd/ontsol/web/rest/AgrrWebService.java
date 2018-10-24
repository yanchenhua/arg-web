package ltd.ontsol.web.rest;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ltd.ontsol.core.service.AddressService;
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

import ltd.ontsol.core.constants.AgrrConstants;
import ltd.ontsol.core.constants.PageConstants;
import ltd.ontsol.core.dto.AgrrNodeDTO;
import ltd.ontsol.core.service.AgrrNodeService;
import ltd.ontsol.core.util.TranslationUtils;
import ltd.ontsol.web.viewer.PaginationViewer;

@RestController
@RequestMapping("/app/rest/agrr/")
public class AgrrWebService {
    private static final Log log = LogFactory.getLog(AgrrWebService.class);
    @Inject
    AgrrNodeService service;

    @Inject
    AddressService addressService;

    @RequestMapping(
            value = "saveOrUpdate",
            method = RequestMethod.POST,
            produces = {MediaType.ALL_VALUE})
    @Timed
    public ResponseEntity<AgrrNodeDTO> saveOrUpdate(@RequestBody AgrrNodeDTO dto) throws Exception {
        TranslationUtils.putTranslation(dto.getGoodText());
        TranslationUtils.putTranslation(dto.getGoodAddr());
        TranslationUtils.putTranslation(dto.getGoodCompanyName());
        TranslationUtils.putTranslation(dto.getGoodName());
        TranslationUtils.putTranslation(dto.getGoodTel());
        service.saveOrUpdate(dto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping(value = "pagination/{pageCount}/{pageSize}/",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<PaginationViewer> pagination(@RequestBody AgrrNodeDTO dto,
                                                       @PathVariable("pageCount") Integer pageCount,
                                                       @PathVariable("pageSize") Integer pageSize) {
        List<AgrrNodeDTO> uds = service.findAll(dto);

        uds.forEach(o -> {
            o.setAddrs(addressService.findAllByAgrrNode(o));
        });
        if (uds.size() == 0)
            return new ResponseEntity<>(new PaginationViewer(uds, pageCount, pageSize), HttpStatus.OK);

        PaginationViewer page = new PaginationViewer(uds, pageCount, pageSize);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @RequestMapping(value = "pagination/front",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<Map<String, Object>> paginationForFront(@RequestParam("pagecount") Integer pageCount,
                                                                  @RequestParam("type") AgrrConstants type) {
        List<AgrrNodeDTO> uds = service.findAllGoodProvider();
        Map<String, Object> result = new HashMap<>();

        if (uds.size() == 0)
            return new ResponseEntity<>(result, HttpStatus.OK);

        uds = uds.stream().skip((pageCount - 1) * PageConstants.PAGE_SIZE).limit(PageConstants.PAGE_SIZE).collect(Collectors.toList());
        result.put("items", uds.size() == 0 ? "no data" : uds);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "delete/{id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    @Timed
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @RequestMapping(value = "download",
                    method = RequestMethod.POST,
                        produces = {MediaType.APPLICATION_ATOM_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})

    @Timed
    public HttpServletResponse download(HttpServletRequest servletRequest, HttpServletResponse response) throws IOException {
        String path = servletRequest.getParameter("url");
        File file = new File(path);
        String filename = file.getName();
        String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
        InputStream fis = new BufferedInputStream(new FileInputStream(path));
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        response.reset();
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
        response.addHeader("Content-Length", "" + file.length());
        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");
        toClient.write(buffer);
        toClient.flush();
        toClient.close();
        return (HttpServletResponse) response;
    }
}
