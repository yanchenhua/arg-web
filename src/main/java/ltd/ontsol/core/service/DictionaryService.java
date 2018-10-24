package ltd.ontsol.core.service;

import java.util.List;

import ltd.ontsol.core.dto.DictionaryDTO;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
public interface DictionaryService {
    DictionaryDTO saveOrUpdate(DictionaryDTO dto);

    DictionaryDTO findById(Long id);

    void delete(Long id);

    List<DictionaryDTO> findRoot();

    List<DictionaryDTO> findAll(DictionaryDTO dto);

    List<DictionaryDTO> findAllByOrderById();

    int findDictionaryChildren(Long id);

}
