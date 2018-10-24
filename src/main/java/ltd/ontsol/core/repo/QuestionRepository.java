package ltd.ontsol.core.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ltd.ontsol.core.constants.QuestionTypeConstants;
import ltd.ontsol.core.dto.QuestionDTO;

/**
 * Created by cn40580 at 2016-10-09 10:05 AM.
 */
public interface QuestionRepository extends JpaRepository<QuestionDTO, Long> {

    List<QuestionDTO> findAllByType(QuestionTypeConstants type);
}
