package ltd.ontsol.core.service;

import java.util.List;

import ltd.ontsol.core.constants.AttachmentConstants;
import ltd.ontsol.core.dto.AttachmentDTO;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
public interface AttachmentService {

    AttachmentDTO findById(Long id);

    List<AttachmentDTO> findAll();

    AttachmentDTO saveOrUpdate(AttachmentDTO dto);

    void delete(Long id);

    String getUploadFolder();

    List<AttachmentDTO> findAllByType(AttachmentConstants type);
}
