package ltd.ontsol.core.service;

import ltd.ontsol.core.dto.LoginDTO;

import java.util.List;

public interface LoginService {
    List<LoginDTO> findAll();
    List<LoginDTO> findAll(LoginDTO dto);
    LoginDTO saveOrUpdate(LoginDTO dto);
    void delete(Long id);
    void pass(Long id,Long status);
    Integer countByStaffName(String staffCode, String staffName);
    Integer countByAll(String staffCode, String staffName,String staffOrg,String apartment);
    Integer countByUserid(String userid);
}
