package ltd.ontsol.core.service.impl;

import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ltd.ontsol.core.dto.DevelopmentDTO;
import ltd.ontsol.core.repo.DevelopmentRepository;
import ltd.ontsol.core.service.DevelopmentService;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
@Component("developmentService")
public class DevelopmentServiceImpl implements DevelopmentService {

    private static final Log log = LogFactory.getLog(DevelopmentServiceImpl.class);

    @Autowired
    private DevelopmentRepository repository;

    public List<DevelopmentDTO> findAll() {
        return IterableUtils.toList(repository.findAll());
    }

    public DevelopmentDTO findById(Long id) {
        return repository.findById(id).get();
    }

    public DevelopmentDTO saveOrUpdate(DevelopmentDTO dto) {
        return repository.save(dto);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
