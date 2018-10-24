package ltd.ontsol.core.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ltd.ontsol.core.constants.ContactCardConstants;
import ltd.ontsol.core.dto.ContactCardDTO;

/**
 * Created by cn40580 at 2016-10-09 10:05 AM.
 */
public interface ContactCardRepository extends JpaRepository<ContactCardDTO, Long> {

    List<ContactCardDTO> findAllByType(ContactCardConstants type);
}
