package ltd.ontsol.core.service;

import ltd.ontsol.core.dto.HomeDTO;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
public interface HomeService {
    HomeDTO findOne();

    Long countHome();

    HomeDTO saveOrUpdate(HomeDTO dto);

    void delete(Long id);
}
