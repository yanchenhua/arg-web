package ltd.ontsol.core.service;

import java.util.List;

import ltd.ontsol.core.constants.ArticleConstants;
import ltd.ontsol.core.dto.ArticleDTO;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
public interface ArticleService {
    List<ArticleDTO> findAll();

    ArticleDTO findById(Long id);

    List<ArticleDTO> findAllByType(ArticleConstants type);

    List<ArticleDTO> findAll(ArticleDTO dto);

    ArticleDTO saveOrUpdate(ArticleDTO dto);

    List<ArticleDTO> findHomeArticle();

    List<ArticleDTO> findTitleArticle();

    void delete(Long id);
}
