package ltd.ontsol.core.service.impl;

import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ltd.ontsol.core.dto.AddressDTO;
import ltd.ontsol.core.dto.AgrrNodeDTO;
import ltd.ontsol.core.repo.AddressRepository;
import ltd.ontsol.core.service.AddressService;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
@Component("addressService")
public class AddressServiceImpl implements AddressService {

    private static final Log log = LogFactory.getLog(AddressServiceImpl.class);

    @Autowired
    private AddressRepository repository;

    public List<AddressDTO> findAll() {
        return IterableUtils.toList(repository.findAll());
    }

    public Set<AddressDTO> findAllByAgrrNode(AgrrNodeDTO node) {
        return repository.findAllByAgrrNode(node);
    }

    public AddressDTO findById(Long id) {
        return repository.findById(id).get();
    }

    public AddressDTO saveOrUpdate(AddressDTO dto) {
        return repository.save(dto);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
