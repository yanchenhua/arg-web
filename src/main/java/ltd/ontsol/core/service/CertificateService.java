package ltd.ontsol.core.service;

import java.util.List;

import ltd.ontsol.core.dto.CertificateDTO;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
public interface CertificateService {
    List<CertificateDTO> findAll();

    CertificateDTO saveOrUpdate(CertificateDTO dto);

    void delete(Long id);
}
