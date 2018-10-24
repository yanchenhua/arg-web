package ltd.ontsol.core.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import ltd.ontsol.core.constants.ArticleConstants;
import ltd.ontsol.core.dto.ArticleDTO;
import ltd.ontsol.core.repo.ArticleRepository;
import ltd.ontsol.core.service.ArticleService;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
@Component("articleService")
public class ArticleServiceImpl implements ArticleService {

    private static final Log log = LogFactory.getLog(ArticleServiceImpl.class);

    @Autowired
    private ArticleRepository repo;

    public List<ArticleDTO> findAll() {
        return IterableUtils.toList(repo.findAll());
    }

    public List<ArticleDTO> findHomeArticle() {
        return repo.findAllByHomeFlagOrderBySeq(true);
    }

    public List<ArticleDTO> findTitleArticle() {
        return repo.findAllByTitleFlagOrderBySeq(true);
    }

    @Override
    public ArticleDTO findById(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public List<ArticleDTO> findAllByType(ArticleConstants type) {
        return repo.findAllByArticleTypeOrderBySeq(type);
    }

    public List<ArticleDTO> findAll(ArticleDTO dto) {

        return IterableUtils.toList(repo.findAll(Example.of(dto)));
    }

    public ArticleDTO saveOrUpdate(ArticleDTO dto) {
        dto.setCreatedDate(new Date());
        return repo.save(dto);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
