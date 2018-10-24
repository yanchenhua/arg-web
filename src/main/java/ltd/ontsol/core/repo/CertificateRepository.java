package ltd.ontsol.core.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ltd.ontsol.core.dto.CertificateDTO;

/**
 * Created by cn40580 at 2016-10-09 10:05 AM.
 */
public interface CertificateRepository extends JpaRepository<CertificateDTO, Long> {

}
