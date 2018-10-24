package ltd.ontsol.core.service;

import java.util.List;

import ltd.ontsol.core.dto.LeaderDTO;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
public interface LeaderService {
    List<LeaderDTO> findAll(LeaderDTO dto);

    List<LeaderDTO> findAll();

    LeaderDTO saveOrUpdate(LeaderDTO dto);

    void delete(Long id);

    LeaderDTO findById(Long id);
}
