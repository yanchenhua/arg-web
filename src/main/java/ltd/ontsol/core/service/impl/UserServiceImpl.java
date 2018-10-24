package ltd.ontsol.core.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import ltd.ontsol.core.dto.UserDTO;
import ltd.ontsol.core.repo.RoleRepository;
import ltd.ontsol.core.repo.UserRepository;
import ltd.ontsol.core.service.UserService;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
@Component("userService")
public class UserServiceImpl implements UserService {

    private static final Log log = LogFactory.getLog(UserServiceImpl.class);

    @Value("${argweb.defaultUser}")
    private String defaultUser;

    @Value("${argweb.defaultUserPswd}")
    private String defaultUserPswd;


    @Autowired
    private UserRepository repo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDTO findById(Long id) {
        return repo.findById(id).get();
    }

    public UserDTO saveOrUpdate(UserDTO user) {
        if (user.getId() > 0 && StringUtils.isEmpty(user.getPassword())) {
            UserDTO tempDTO = findById(user.getId());
            user.setPassword(tempDTO.getPassword());
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setRoles(new HashSet<>(roleRepo.findAll()));
        }
        return repo.save(user);
    }

    @Override
    public List<UserDTO> findAll() {
        return IterableUtils.toList(repo.findAll());
    }

    @Override
    public List<UserDTO> findAll(UserDTO dto) {
        return IterableUtils.toList(repo.findAll(Example.of(dto)));
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public void plusLoginCount(String userName) {
        List<UserDTO> dtos = repo.findAllByUserName(userName);
        dtos.forEach(u -> {
            u.setLoginCount(u.getLoginCount() == null ? 0 : (u.getLoginCount() + 1));
            u.setLastLoginDate(new Date());
            repo.save(u);
        });
    }

    @Override
    public UserDTO findByUserName(String userName) {
        return repo.findByUserName(userName);
    }

    @Override
    public UserDTO getDefaultUser() {
        return new UserDTO(defaultUser, defaultUserPswd);
    }

}
