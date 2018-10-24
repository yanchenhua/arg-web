package ltd.ontsol.core.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ltd.ontsol.core.dto.AboutDTO;
import ltd.ontsol.core.repo.AboutRepository;
import ltd.ontsol.core.repo.LongTextTranslationRepository;
import ltd.ontsol.core.service.AboutService;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
@Component("aboutService")
public class AboutServiceImpl implements AboutService {

    private static final Log log = LogFactory.getLog(AboutServiceImpl.class);

    @Autowired
    private AboutRepository repository;

    @Autowired
    private LongTextTranslationRepository longTransRepo;

    public AboutDTO findOne() {
        return repository.findDistinctFirstByIdIsNotNull();
    }

    public AboutDTO findById(Long id) {
        return repository.findById(id).get();
    }

    public AboutDTO saveOrUpdate(AboutDTO dto) {
        AboutDTO newDto = repository.save(dto);

//        if (dto.getParam1().getTranslations() != null &&
//                dto.getParam1().getTranslations().size() > 0) {
//            dto.getParam1().getTranslations().forEach(t -> {
////                t.setLongText(newDto.getParam1());
//                t = longTransRepo.save(t);
//            });
//        }
        return newDto;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
