package ltd.ontsol.core.service.impl;

import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import ltd.ontsol.core.dto.BusinessDTO;
import ltd.ontsol.core.repo.BusinessRepository;
import ltd.ontsol.core.service.BusinessService;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
@Component("businessService")
public class BusinessServiceImpl implements BusinessService {

    private static final Log log = LogFactory.getLog(BusinessServiceImpl.class);

    @Autowired
    private BusinessRepository repository;

    public List<BusinessDTO> findAll() {
        return IterableUtils.toList(repository.findAll());
    }

    public List<BusinessDTO> findAll(BusinessDTO dto) {
        return repository.findAll(Example.of(dto));
    }

    public BusinessDTO findById(Long id) {
        return repository.findById(id).get();
    }

    public BusinessDTO saveOrUpdate(BusinessDTO dto) {
        return repository.save(dto);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
