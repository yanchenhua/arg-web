package ltd.ontsol.core.service;

import java.util.List;

import ltd.ontsol.core.dto.UserDTO;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
public interface UserService {

    UserDTO findById(Long id);

    UserDTO saveOrUpdate(UserDTO dto);

    List<UserDTO> findAll();

    List<UserDTO> findAll(UserDTO dto);

    void delete(Long id);

    void plusLoginCount(String userName);

    UserDTO findByUserName(String userName);

    UserDTO getDefaultUser();
}
