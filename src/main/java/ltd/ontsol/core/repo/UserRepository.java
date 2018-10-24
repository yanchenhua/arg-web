package ltd.ontsol.core.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ltd.ontsol.core.dto.UserDTO;

/**
 * Created by cn40580 at 2016-10-09 10:05 AM.
 */
public interface UserRepository extends JpaRepository<UserDTO, Long> {

    UserDTO findUserById(Long id);

    List<UserDTO> findAllByUserName(String userName);

    UserDTO findByUserName(String userName);

}
