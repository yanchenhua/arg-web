package ltd.ontsol.core.service.impl;

import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import ltd.ontsol.core.dto.HonorDTO;
import ltd.ontsol.core.repo.HonorRepository;
import ltd.ontsol.core.service.HonorService;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
@Component("honorService")
public class HonorServiceImpl implements HonorService {

    private static final Log log = LogFactory.getLog(HonorServiceImpl.class);

    @Autowired
    private HonorRepository honorRepository;

    public List<HonorDTO> findAll() {
        return IterableUtils.toList(honorRepository.findAll());
    }

    public List<HonorDTO> findAll(HonorDTO dto) {

        return IterableUtils.toList(honorRepository.findAll(Example.of(dto)));
    }

    public HonorDTO saveOrUpdate(HonorDTO dto) {
        return honorRepository.save(dto);
    }

    public void delete(Long id) {
        honorRepository.deleteById(id);
    }
}
