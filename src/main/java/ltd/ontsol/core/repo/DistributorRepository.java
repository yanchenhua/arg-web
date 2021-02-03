package ltd.ontsol.core.repo;

import ltd.ontsol.core.dto.AddressDTO;
import ltd.ontsol.core.dto.AgrrNodeDTO;
import ltd.ontsol.core.dto.DistributorDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DistributorRepository extends JpaRepository<DistributorDTO, Long> {
    @Query(value = "select * from distributor  ", nativeQuery = true)
    List<DistributorDTO> findAllDistributor();

}
