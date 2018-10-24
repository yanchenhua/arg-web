package ltd.ontsol.core.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ltd.ontsol.core.dto.DictionaryDTO;
import ltd.ontsol.core.repo.DictionaryRepository;
import ltd.ontsol.core.service.DictionaryService;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
@Component("dictionaryService")
public class DictionaryServiceImpl implements DictionaryService {

    private static final Log log = LogFactory.getLog(DictionaryServiceImpl.class);

    @Autowired
    private DictionaryRepository repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<DictionaryDTO> findRoot() {
       // return repository.findAllByParentDicIsNullOrderByTitleAsc();
        return repository.findAllByParentDicIsNullOrderBySeqAsc();
    }

    public List<DictionaryDTO> findAll(DictionaryDTO dto) {
        return repository.findAll(Example.of(dto));
    }

    public DictionaryDTO findById(Long id) {
        return repository.findById(id).get();
    }

    public DictionaryDTO saveOrUpdate(DictionaryDTO dto) {
        return repository.save(dto);
    }

    public void delete(Long id) {

//        DictionaryDTO dto = repository.findById(id).get();
//        LongText titleLongText = dto.getTitle();
//        Set<LongTextTranslation> titleTranslations = titleLongText.getTranslations();
//        for (LongTextTranslation translation : titleTranslations){
//            longTextTranslationRepository.delete(translation);
//        }
//        LongText descLongText = dto.getDesc();
//        //删除翻译内容
//        Set<LongTextTranslation> descTranslations = descLongText.getTranslations();
//        for (LongTextTranslation translation : descTranslations){
//            longTextTranslationRepository.delete(translation);
//        }
//        Long  descID = descLongText.getId();
//        Long  titleID = titleLongText.getId();
        //先删除字典
//        repository.deleteById(id);
        //删除 longText
//        longTextRepository.deleteById(descID);
//        longTextRepository.deleteById(titleID);

        DictionaryDTO dto = repository.findById(id).get();
        Long titleId = dto.getTitle().getId();
        Long descId = dto.getDesc().getId();
        String sql1 = "delete from long_text_trans where long_text_id in  (select id from long_text where id in (" + titleId + "," + descId + "))";
        String sql2 = "delete from dictionary where id = " + id + "";
        String sql3 = "delete from long_text where id in (" + titleId + "," + descId + ")";
        jdbcTemplate.batchUpdate(sql1, sql2, sql3);

    }

    public List<DictionaryDTO> findAllByOrderById() {
        return repository.findAllByOrderByIdAsc();
    }

    public int findDictionaryChildren(Long id) {
        return repository.findDictionaryChildren(id);
    }

}
