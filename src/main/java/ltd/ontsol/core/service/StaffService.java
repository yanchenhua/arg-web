package ltd.ontsol.core.service;

import java.util.List;

import ltd.ontsol.core.dto.StaffDTO;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
public interface StaffService {
    Integer countByStaffCodeAndName(String staffCode, String name);

    void deleteAll();

    void saveAll(List<StaffDTO> staffs);
}
