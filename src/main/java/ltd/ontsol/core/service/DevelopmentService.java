package ltd.ontsol.core.service;

import java.util.List;

import ltd.ontsol.core.dto.DevelopmentDTO;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
public interface DevelopmentService {
    List<DevelopmentDTO> findAll();

    DevelopmentDTO saveOrUpdate(DevelopmentDTO dto);

    void delete(Long id);

}
