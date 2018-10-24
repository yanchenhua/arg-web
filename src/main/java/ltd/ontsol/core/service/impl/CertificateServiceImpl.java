package ltd.ontsol.core.service.impl;

import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ltd.ontsol.core.dto.CertificateDTO;
import ltd.ontsol.core.repo.CertificateRepository;
import ltd.ontsol.core.service.CertificateService;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
@Component("certificateService")
public class CertificateServiceImpl implements CertificateService {

    private static final Log log = LogFactory.getLog(CertificateServiceImpl.class);

    @Autowired
    private CertificateRepository repository;

    public List<CertificateDTO> findAll() {
        return IterableUtils.toList(repository.findAll());
    }

    public CertificateDTO saveOrUpdate(CertificateDTO dto) {
        return repository.save(dto);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
