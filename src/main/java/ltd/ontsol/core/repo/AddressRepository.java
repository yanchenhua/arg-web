package ltd.ontsol.core.repo;

import java.util.List;
import java.util.Set;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;

import ltd.ontsol.core.dto.AddressDTO;
import ltd.ontsol.core.dto.AgrrNodeDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 * Created by cn40580 at 2016-10-09 10:05 AM.
 */
public interface AddressRepository extends JpaRepository<AddressDTO, Long> {
    Set<AddressDTO> findAllByAgrrNode(AgrrNodeDTO dto);
    @Transactional
    @Modifying
    @Query(value = "delete from address  where agrr_id = :id", nativeQuery = true)

    void deleteByAgrrId(@Param("id") Long id);
}
