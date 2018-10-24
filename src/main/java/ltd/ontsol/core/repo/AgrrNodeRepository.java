package ltd.ontsol.core.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ltd.ontsol.core.dto.AgrrNodeDTO;

/**
 * Created by cn40580 at 2016-10-09 10:05 AM.
 */
public interface AgrrNodeRepository extends JpaRepository<AgrrNodeDTO, Long> {

    List<AgrrNodeDTO> findAllByGoodProvider(Boolean bool);
}
