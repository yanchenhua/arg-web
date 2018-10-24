package ltd.ontsol.core.service;

import java.util.List;

import ltd.ontsol.core.dto.BusinessDTO;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
public interface BusinessService {
    List<BusinessDTO> findAll();

    List<BusinessDTO> findAll(BusinessDTO dto);

    BusinessDTO saveOrUpdate(BusinessDTO dto);

    void delete(Long id);

}
