package ltd.ontsol.core.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ltd.ontsol.core.constants.DicLevelConstants;
import ltd.ontsol.core.dto.DictionaryDTO;

/**
 * Created by cn40580 at 2016-10-09 10:05 AM.
 */
public interface DictionaryRepository extends JpaRepository<DictionaryDTO, Long> {

    List<DictionaryDTO> findAllByLevel(DicLevelConstants level);

    List<DictionaryDTO> findAllByParentDicIsNullOrderByTitleAsc();
    List<DictionaryDTO> findAllByParentDicIsNullOrderByIdAsc();
    List<DictionaryDTO> findAllByParentDicIsNullOrderBySeqAsc();

    List<DictionaryDTO> findAllByOrderByParentDicAscIdAsc();

    @Query(value = "select * from dictionary a  order by  if(isnull(parent_id),id,parent_id) ,  id", nativeQuery = true)
    List<DictionaryDTO> findAllByOrderByIdAsc();

    @Query(value = "select count(a.id) as cnt from dictionary a where a.parent_id = ?1 ", nativeQuery = true)
    int findDictionaryChildren(Long id);
}
