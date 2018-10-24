package ltd.ontsol.core.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import ltd.ontsol.core.constants.ContactCardConstants;
import ltd.ontsol.core.dto.ContactCardDTO;
import ltd.ontsol.core.repo.ContactCardRepository;
import ltd.ontsol.core.service.ContactCardService;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
@Component("contactCardService")
public class ContactCardServiceImpl implements ContactCardService {

    private static final Log log = LogFactory.getLog(ContactCardServiceImpl.class);

    @Autowired
    private ContactCardRepository repository;

    public List<ContactCardDTO> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ContactCardDTO> findAll(ContactCardDTO dto) {
        return repository.findAll(Example.of(dto));
    }

    public List<ContactCardDTO> findAllByType(ContactCardConstants type) {
        return repository.findAllByType(type);
    }

    public ContactCardDTO saveOrUpdate(ContactCardDTO dto) {
        return repository.save(dto);
    }

    @Override
    public ContactCardDTO findById(Long id) {
        return repository.findById(id).get();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
