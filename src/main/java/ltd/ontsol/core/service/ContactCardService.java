package ltd.ontsol.core.service;

import java.util.List;

import ltd.ontsol.core.constants.ContactCardConstants;
import ltd.ontsol.core.dto.ContactCardDTO;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
public interface ContactCardService {
    List<ContactCardDTO> findAll();

    List<ContactCardDTO> findAll(ContactCardDTO dto);

    List<ContactCardDTO> findAllByType(ContactCardConstants type);

    ContactCardDTO saveOrUpdate(ContactCardDTO dto);

    ContactCardDTO findById(Long id);

    void delete(Long id);

}
