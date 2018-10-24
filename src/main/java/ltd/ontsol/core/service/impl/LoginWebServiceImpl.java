package ltd.ontsol.core.service.impl;

import ltd.ontsol.core.dto.LoginDTO;
import ltd.ontsol.core.repo.LoginRepository;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import ltd.ontsol.core.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("LoginService")
public class LoginWebServiceImpl implements LoginService {
    @Autowired
    private LoginRepository repository;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<LoginDTO> findAll() {
        return repository.findAll();
    }

    @Override
    public List<LoginDTO> findAll(LoginDTO dto) {
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Sort sort=new Sort(Sort.Direction.ASC,"status");
        return repository.findAll(Example.of(dto,matcher),sort);
    }

    @Override
    public LoginDTO saveOrUpdate(LoginDTO dto) {
        LoginDTO newDto =  repository.save(dto);
        return newDto;
    }

    @Override
    public void delete(Long id) {
        String sql = "delete from login_node where id = " + id + "";
        System.out.println("----------->>>"+id);
        jdbcTemplate.batchUpdate(sql);
    }
    @Override
    public void pass(Long id,Long status){
        String sql = "update login_node set status = '"+status+"' where id = " + id + "";
        System.out.println("----------->>>"+id);
        jdbcTemplate.batchUpdate(sql);
    }
    @Override
    public Integer countByStaffName(String staffCode, String staffName) {
        return repository.countByStaffName(staffCode, staffName);
    }
    @Override
    public Integer countByUserid(String userid) {
        return repository.countByUserid(userid);
    }
    @Override
    public Integer countByAll(String staffCode, String staffName,String staffOrg,String apartment) {
        return repository.countByAll(staffCode, staffName,staffOrg,apartment);
    }
}
