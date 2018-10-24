package ltd.ontsol.core.service;

import java.util.List;

import ltd.ontsol.core.dto.DicSuggestionDTO;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
public interface DicSuggestionService {

    List<DicSuggestionDTO> findAll();

    DicSuggestionDTO findById(Long id);
    List<String> findNewSug();

    List<DicSuggestionDTO> findAll(DicSuggestionDTO dao);

    DicSuggestionDTO save(DicSuggestionDTO dto);

    void delete(Long id);

}
