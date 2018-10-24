package ltd.ontsol.core.service;

import java.util.List;

import ltd.ontsol.core.dto.HonorDTO;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
public interface HonorService {
    List<HonorDTO> findAll();

    List<HonorDTO> findAll(HonorDTO dto);

    HonorDTO saveOrUpdate(HonorDTO dto);

    void delete(Long id);
}
