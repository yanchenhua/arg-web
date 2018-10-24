package ltd.ontsol.core.repo;

import ltd.ontsol.core.dto.LongText;
import org.springframework.data.jpa.repository.JpaRepository;

import ltd.ontsol.core.dto.DicSuggestionDTO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by cn40580 at 2016-10-09 10:05 AM.
 */
public interface DicSuggestionRepository extends JpaRepository<DicSuggestionDTO, Long> {
    @Query(value = "SELECT LTT.text,S.created_date,S.staff_name,S.text1 FROM long_text_trans LTT  RIGHT JOIN long_text LT on LTT.long_text_id = LT.id RIGHT JOIN dictionary D on LT.id = D.title_text_id RIGHT JOIN dic_suggestion S ON S.dictionary_id = D.id WHERE lang = 'zh_CN' AND (S.reply IS NULL OR S.reply='') ORDER BY S.created_date DESC", nativeQuery = true)
    List<String> findNewSug();
}
