package ltd.ontsol.core.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ltd.ontsol.core.constants.AttachmentConstants;
import ltd.ontsol.core.dto.AttachmentDTO;

/**
 * Created by cn40580 at 2016-10-09 10:05 AM.
 */
public interface AttachmentRepository extends JpaRepository<AttachmentDTO, Long> {

    List<AttachmentDTO> findAllByType(AttachmentConstants constants);

}
