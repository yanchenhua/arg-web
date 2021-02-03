package ltd.ontsol.core.service;

import java.util.List;
import java.util.Set;

import ltd.ontsol.core.dto.AddressDTO;
import ltd.ontsol.core.dto.AgrrNodeDTO;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
public interface AddressService {
    List<AddressDTO> findAll();

    Set<AddressDTO> findAllByAgrrNode(AgrrNodeDTO node);

    AddressDTO saveOrUpdate(AddressDTO dto);

    void delete(Long id);
    void deleteByAgrrId(Long id);

}
