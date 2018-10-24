package ltd.ontsol.core.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ltd.ontsol.core.dto.LeaderDTO;

/**
 * Created by cn40580 at 2016-10-09 10:05 AM.
 */
public interface LeaderRepository extends JpaRepository<LeaderDTO, Long> {

}
