package ltd.ontsol.core.service.impl;

import ltd.ontsol.core.dto.DistributorDTO;
import ltd.ontsol.core.repo.AddressRepository;
import ltd.ontsol.core.repo.DistributorRepository;
import ltd.ontsol.core.service.DistributorService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component("distributorService")
public class DistributorServiceImpl implements DistributorService {

    @Autowired
    private static final Log log = LogFactory.getLog(AddressServiceImpl.class);

    @Autowired
    private DistributorRepository repository;
    @Override
    public List<DistributorDTO> findAll(){
      return repository.findAllDistributor();
    }
}
