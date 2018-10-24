package ltd.ontsol.core.service;

import ltd.ontsol.core.dto.AboutDTO;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
public interface AboutService {
    AboutDTO findOne();

    AboutDTO saveOrUpdate(AboutDTO dto);

    void delete(Long id);

}
