package ltd.ontsol.core.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ltd.ontsol.core.dto.StaffDTO;
import ltd.ontsol.core.repo.StaffRepository;
import ltd.ontsol.core.service.StaffService;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
@Component("staffService")
public class StaffServiceImpl implements StaffService {

    private static final Log log = LogFactory.getLog(StaffServiceImpl.class);

    @Autowired
    private StaffRepository repository;

    public StaffDTO findById(Long id) {
        return repository.findById(id).get();
    }


    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public void saveAll(List<StaffDTO> staffs) {
        repository.saveAll(staffs);
    }

    @Override
    public Integer countByStaffCodeAndName(String staffCode, String name) {
        return repository.countByStaffCodeAndName(staffCode, name);
    }
}
