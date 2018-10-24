package ltd.ontsol.core.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import ltd.ontsol.core.constants.QuestionTypeConstants;
import ltd.ontsol.core.dto.QuestionDTO;
import ltd.ontsol.core.repo.QuestionRepository;
import ltd.ontsol.core.service.QuestionService;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
@Component("questionService")
public class QuestionServiceImpl implements QuestionService {

    private static final Log log = LogFactory.getLog(QuestionServiceImpl.class);

    @Autowired
    private QuestionRepository repository;

    public List<QuestionDTO> findAll() {
        return repository.findAll();
    }

    public List<QuestionDTO> findAll(QuestionDTO dto) {
        return repository.findAll(Example.of(dto));
    }

    @Override
    public List<QuestionDTO> findAllByType(QuestionTypeConstants type) {
        return repository.findAllByType(type);
    }

    public QuestionDTO findById(Long id) {
        return repository.findById(id).get();
    }

    public QuestionDTO saveOrUpdate(QuestionDTO dto) {
        QuestionDTO newDto = repository.save(dto);
        return newDto;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
