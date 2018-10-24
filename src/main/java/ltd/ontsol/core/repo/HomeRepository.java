package ltd.ontsol.core.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ltd.ontsol.core.dto.HomeDTO;

/**
 * Created by cn40580 at 2016-10-09 10:05 AM.
 * Home
 */
public interface HomeRepository extends JpaRepository<HomeDTO, Long> {

    HomeDTO findDistinctFirstByIdIsNotNull();
}
