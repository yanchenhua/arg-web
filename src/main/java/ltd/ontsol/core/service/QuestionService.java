package ltd.ontsol.core.service;

import java.util.List;

import ltd.ontsol.core.constants.QuestionTypeConstants;
import ltd.ontsol.core.dto.QuestionDTO;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
public interface QuestionService {
    QuestionDTO saveOrUpdate(QuestionDTO dto);

    void delete(Long id);

    List<QuestionDTO> findAll();

    List<QuestionDTO> findAll(QuestionDTO dto);

    List<QuestionDTO> findAllByType(QuestionTypeConstants type);
}
