package ltd.ontsol.core.service;

import java.util.List;

import ltd.ontsol.core.dto.AgrrNodeDTO;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
public interface AgrrNodeService {
    List<AgrrNodeDTO> findAll();
    List<AgrrNodeDTO> findByVerStatus(String keyword);
    AgrrNodeDTO findById( Long id);

    List<AgrrNodeDTO> findAll(AgrrNodeDTO dto);

    List<AgrrNodeDTO> findAllGoodProvider();
   // List<AgrrNodeDTO> findById();
   List<AgrrNodeDTO> findByLoginInfo(String username,String password);
    AgrrNodeDTO save(AgrrNodeDTO dto);
    //void upload(Long id,String url);
    void delete(Long id);

    AgrrNodeDTO saveOrUpdate(AgrrNodeDTO dto);

    Integer countByLoginInfo(String username,String password);
    Integer countByUsername(String username);
}
