package ltd.ontsol.core.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ltd.ontsol.core.dto.DicSuggestionDTO;
import ltd.ontsol.core.repo.DicSuggestionRepository;
import ltd.ontsol.core.service.DicSuggestionService;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
@Component("dicSuggestionService")
public class DicSuggestionServiceImpl implements DicSuggestionService {

    private static final Log log = LogFactory.getLog(DicSuggestionServiceImpl.class);

    @Autowired
    private DicSuggestionRepository repository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public DicSuggestionDTO findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<DicSuggestionDTO> findAll() {
        return repository.findAll();
    }

    @Override
    public List<DicSuggestionDTO> findAll(DicSuggestionDTO dao) {
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Sort sort=new Sort(Sort.Direction.ASC,"created_date");
        return repository.findAll(Example.of(dao,matcher),sort);
    }
    @Override
    public List<String> findNewSug(){
        return repository.findNewSug();
    }
    public DicSuggestionDTO save(DicSuggestionDTO dto) {
        if (dto.getCheckFlag() == null) dto.setCheckFlag(false);
        return repository.save(dto);
    }

    public void delete(Long id) {
        String sql = "delete from dic_suggestion where id = " + id + "";
        System.out.println("----------->>>"+id);
        jdbcTemplate.batchUpdate(sql);
        //repository.deleteById(id);
    }
}
