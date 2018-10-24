package ltd.ontsol.core.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ltd.ontsol.core.constants.AttachmentConstants;
import ltd.ontsol.core.dto.AttachmentDTO;
import ltd.ontsol.core.dto.HomeDTO;
import ltd.ontsol.core.repo.AttachmentRepository;
import ltd.ontsol.core.repo.HomeRepository;
import ltd.ontsol.core.service.HomeService;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
@Component("homeService")
public class HomeServiceImpl implements HomeService {

    private static final Log log = LogFactory.getLog(HomeServiceImpl.class);

    @Autowired
    private HomeRepository repository;

    @Autowired
    private AttachmentRepository attachementRepository;

    public HomeDTO findOne() {
        HomeDTO dto = repository.findDistinctFirstByIdIsNotNull();
        if (dto == null) return new HomeDTO();
        List<AttachmentDTO> attachmentDTOList = attachementRepository.findAllByType(AttachmentConstants.HOME);
        dto.setAttachment(attachmentDTOList);
        return dto;
    }

    @Override
    public Long countHome() {
        return repository.count();
    }

    public HomeDTO findById(Long id) {
        return repository.findById(id).get();
    }

    public HomeDTO saveOrUpdate(HomeDTO dto) {
        return repository.save(dto);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
