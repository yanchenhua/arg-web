package ltd.ontsol.core.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import ltd.ontsol.core.constants.MediaConstants;
import ltd.ontsol.core.dto.MediaDTO;
import ltd.ontsol.core.repo.MediaRepository;
import ltd.ontsol.core.service.MediaService;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
@Component("mediaService")
public class MediaServiceImpl implements MediaService {

    private static final Log log = LogFactory.getLog(MediaServiceImpl.class);

    @Autowired
    private MediaRepository repository;

    public List<MediaDTO> findAll() {
        return repository.findAll();
    }

    public List<MediaDTO> findAll(MediaDTO dto) {
        return repository.findAll(Example.of(dto));
    }

    public List<MediaDTO> findAllByType(MediaConstants type) {
        return repository.findAllByType(type);
    }

    public MediaDTO saveOrUpdate(MediaDTO dto) {
        if (dto.getAttachment() == null) return null;
        if (StringUtils.isEmpty(dto.getAttachment().getFilePath())) return null;
        String suffix = StringUtils.unqualify(dto.getAttachment().getFilePath());
        if (StringUtils.isEmpty(suffix)) return null;
        dto.setType(MediaConstants.myValueOf(suffix));
        return repository.save(dto);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
