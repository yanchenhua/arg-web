package ltd.ontsol.core.service.impl;

import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import ltd.ontsol.core.dto.LeaderDTO;
import ltd.ontsol.core.repo.LeaderRepository;
import ltd.ontsol.core.service.LeaderService;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
@Component("leaderService")
public class LeaderServiceImpl implements LeaderService {

    private static final Log log = LogFactory.getLog(LeaderServiceImpl.class);

    @Autowired
    private LeaderRepository repo;

    public LeaderDTO findById(Long id) {
        return repo.findById(id).get();
    }

    public LeaderDTO saveOrUpdate(LeaderDTO dto) {
        return repo.save(dto);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<LeaderDTO> findAll(LeaderDTO dto) {

        return IterableUtils.toList(repo.findAll(Example.of(dto)));
    }

    @Override
    public List<LeaderDTO> findAll() {

        return IterableUtils.toList(repo.findAll());
    }

}
