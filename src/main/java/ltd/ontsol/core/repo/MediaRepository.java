package ltd.ontsol.core.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ltd.ontsol.core.constants.MediaConstants;
import ltd.ontsol.core.dto.MediaDTO;

/**
 * Created by cn40580 at 2016-10-09 10:05 AM.
 */
public interface MediaRepository extends JpaRepository<MediaDTO, Long> {

    List<MediaDTO> findAllByType(MediaConstants type);
}
