package ltd.ontsol.core.service;

import java.util.List;

import ltd.ontsol.core.dto.AgrrNodeDTO;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
public interface AgrrNodeService {
    List<AgrrNodeDTO> findAll();

    List<AgrrNodeDTO> findAll(AgrrNodeDTO dto);

    List<AgrrNodeDTO> findAllGoodProvider();

    AgrrNodeDTO save(AgrrNodeDTO dto);

    void delete(Long id);

    AgrrNodeDTO saveOrUpdate(AgrrNodeDTO dto);
}
