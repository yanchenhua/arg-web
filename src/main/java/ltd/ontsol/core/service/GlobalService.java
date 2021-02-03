package ltd.ontsol.core.service;

import java.util.List;
import java.util.Locale;

import ltd.ontsol.core.constants.GlobalConstants;
import ltd.ontsol.core.dto.GlobalDTO;
import ltd.ontsol.web.resource.GlobalResource;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
public interface GlobalService {

    List<GlobalResource> findAllGlobalResource(Locale locale);

    List<GlobalDTO> findAll();

    List<GlobalDTO> findAll(GlobalDTO dto);

    List<GlobalDTO> findAllByType(GlobalConstants type);

    GlobalDTO saveOrUpdate(GlobalDTO dto);

    GlobalDTO findById(Long id);

    void delete(Long id);

    List<GlobalResource> findAllGlobalResourceByType(GlobalConstants type, Locale locale);
    List<GlobalResource> findAllGlobalResourceByType2(GlobalConstants type, Locale locale);
}
