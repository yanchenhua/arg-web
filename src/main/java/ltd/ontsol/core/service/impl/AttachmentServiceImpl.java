package ltd.ontsol.core.service.impl;

import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ltd.ontsol.core.constants.AttachmentConstants;
import ltd.ontsol.core.dto.AttachmentDTO;
import ltd.ontsol.core.repo.AttachmentRepository;
import ltd.ontsol.core.service.AttachmentService;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
@Component("attachmentService")
public class AttachmentServiceImpl implements AttachmentService {

    private static final Log log = LogFactory.getLog(AttachmentServiceImpl.class);

    @Value("${argweb.upload}")
    private String uploadFolder;

    @Autowired
    private AttachmentRepository repository;

    public String getUploadFolder() {
        return uploadFolder;
    }

    @Override
    public List<AttachmentDTO> findAllByType(AttachmentConstants type) {
        return repository.findAllByType(type);
    }

    public List<AttachmentDTO> findAll() {
        return IterableUtils.toList(repository.findAll());
    }

    public AttachmentDTO findById(Long id) {
        return repository.findById(id).get();
    }

    public AttachmentDTO saveOrUpdate(AttachmentDTO dto) {

        return repository.save(dto);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
