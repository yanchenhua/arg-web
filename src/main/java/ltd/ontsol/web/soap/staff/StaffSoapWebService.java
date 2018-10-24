package ltd.ontsol.web.soap.staff;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ltd.ontsol.core.mapper.StaffMapper;
import ltd.ontsol.core.service.StaffService;


/**
 * Created by cn40580 at 2016-10-10 10:02 AM.
 */
@WebService(name = "soap")
public class StaffSoapWebService {

    private static final Log log = LogFactory.getLog(StaffSoapWebService.class);

    @Inject
    private StaffService service;

    @Inject
    private StaffMapper mapper;

    @WebMethod
    @WebResult(name = "addStaffs")
    public String putStaffs(@WebParam(name = "staffItem") final List<Staff> staffs) {

        service.saveAll(mapper.resourcesToDTOs(staffs));
        return "success";
    }

    @WebMethod
    @WebResult(name = "clearAllAndPutStaffs")
    public String clearAllAndPutStaffs(@WebParam(name = "staffItem") final List<Staff> staffs) {
        service.deleteAll();
        service.saveAll(mapper.resourcesToDTOs(staffs));
        return "success";
    }


}
