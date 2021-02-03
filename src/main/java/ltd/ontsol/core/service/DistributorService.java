package ltd.ontsol.core.service;

import ltd.ontsol.core.dto.AddressDTO;
import ltd.ontsol.core.dto.DistributorDTO;

import java.util.List;

public interface DistributorService {
    List<DistributorDTO> findAll();
}
