package ltd.ontsol.core.service.impl;

import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ltd.ontsol.core.dto.LongText;
import ltd.ontsol.core.exception.CustomException;
import ltd.ontsol.core.repo.LongTextRepository;
import ltd.ontsol.core.service.LongTextService;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
@Component("longTextService")
public class LongTextServiceImpl implements LongTextService {

    private static final Log log = LogFactory.getLog(LongTextServiceImpl.class);

    @Autowired
    private LongTextRepository repository;

    public List<LongText> findAll() {
        return IterableUtils.toList(repository.findAll());
    }

    public LongText findById(Long id) {
        return repository.findById(id).get();
    }

    public LongText save(LongText dto) {
        return repository.save(dto);
    }

    public LongText update(LongText dto) {
        if (!dto.isNew()) return repository.save(dto);
        else {
            throw new CustomException(200, "not a new obj cannot update, please call save.");
        }
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
