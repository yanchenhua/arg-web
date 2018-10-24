package ltd.ontsol.core.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ltd.ontsol.core.dto.HonorDTO;

/**
 * Created by cn40580 at 2016-10-09 10:05 AM.
 */
public interface HonorRepository extends JpaRepository<HonorDTO, Long> {
}
