package ltd.ontsol.core.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ltd.ontsol.core.dto.AboutDTO;

/**
 * Created by cn40580 at 2016-10-09 10:05 AM.
 */
public interface AboutRepository extends JpaRepository<AboutDTO, Long> {

    AboutDTO findDistinctFirstByIdIsNotNull();
}
