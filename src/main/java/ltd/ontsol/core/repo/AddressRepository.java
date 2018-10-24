package ltd.ontsol.core.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import ltd.ontsol.core.dto.AddressDTO;
import ltd.ontsol.core.dto.AgrrNodeDTO;

/**
 * Created by cn40580 at 2016-10-09 10:05 AM.
 */
public interface AddressRepository extends JpaRepository<AddressDTO, Long> {
    Set<AddressDTO> findAllByAgrrNode(AgrrNodeDTO dto);
}
