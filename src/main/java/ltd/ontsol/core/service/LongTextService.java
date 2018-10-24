package ltd.ontsol.core.service;

import java.util.List;

import ltd.ontsol.core.dto.LongText;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
public interface LongTextService {
    List<LongText> findAll();

    LongText save(LongText dto);

    LongText update(LongText dto);

    void delete(Long id);

}
