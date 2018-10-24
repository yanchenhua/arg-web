package ltd.ontsol.core.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ltd.ontsol.core.constants.ArticleConstants;
import ltd.ontsol.core.dto.ArticleDTO;

/**
 * Created by cn40580 at 2016-10-09 10:05 AM.
 */
public interface ArticleRepository extends JpaRepository<ArticleDTO, Long> {

    List<ArticleDTO> findAllByArticleTypeOrderByCreatedDateDesc(ArticleConstants type);

    List<ArticleDTO> findAllByArticleTypeOrderBySeq(ArticleConstants type);

    List<ArticleDTO> findAllByHomeFlagOrderByCreatedDateDesc(Boolean flag);

    List<ArticleDTO> findAllByHomeFlagOrderBySeq(Boolean flag);
    List<ArticleDTO> findAllByTitleFlagOrderBySeq(Boolean flag);
}
