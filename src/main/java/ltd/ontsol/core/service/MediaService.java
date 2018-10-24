package ltd.ontsol.core.service;

import java.util.List;

import ltd.ontsol.core.constants.MediaConstants;
import ltd.ontsol.core.dto.MediaDTO;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
public interface MediaService {
    List<MediaDTO> findAll();

    List<MediaDTO> findAll(MediaDTO dto);

    List<MediaDTO> findAllByType(MediaConstants type);

    MediaDTO saveOrUpdate(MediaDTO dto);

    void delete(Long id);
}
