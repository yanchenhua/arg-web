package ltd.ontsol.core.service.impl;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ltd.ontsol.core.dto.AddressDTO;
import ltd.ontsol.core.dto.AgrrNodeDTO;
import ltd.ontsol.core.repo.AddressRepository;
import ltd.ontsol.core.repo.AgrrNodeRepository;
import ltd.ontsol.core.service.AgrrNodeService;

/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
@Component("agrrNodeService")
public class AgrrNodeServiceImpl implements AgrrNodeService {

    private static final Log log = LogFactory.getLog(AgrrNodeServiceImpl.class);

    @Autowired
    private AgrrNodeRepository repository;

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<AgrrNodeDTO> findAll() {
        return repository.findAll();
    }

    public List<AgrrNodeDTO> findAll(AgrrNodeDTO dto) {
        return repository.findAll(Example.of(dto));
    }

    public List<AgrrNodeDTO> findAllGoodProvider() {
        return repository.findAllByGoodProvider(true);
    }

    public AgrrNodeDTO findById(Long id) {
        return repository.findById(id).get();
    }
    public Integer countByLoginInfo(String username,String password){
        //return repository.findAllByGoodProvider(true);
        return repository.countByLoginInfo(username,password);
    }
    public Integer countByUsername(String username){
        return repository.countByUsername(username);
    }
   public List<AgrrNodeDTO> findByLoginInfo(String username,String password) {
        return repository.findByLoginInfo(username,password);
    }
    public List<AgrrNodeDTO> findByVerStatus(String keyword){
        return repository.findByVerStatus(keyword);
    }
//    @Override
//    public void upload(Long id,String url){
//        String sql = "update login_node set status = '"+url+"' where id = " + id + "";
//        System.out.println("----------->>>"+id);
//        jdbcTemplate.batchUpdate(sql);
//    }
    public AgrrNodeDTO save(AgrrNodeDTO dto) {
        Set<AddressDTO> addressDTOSet = dto.getAddrs();
        if (addressDTOSet != null) {
            addressDTOSet = addressDTOSet.stream().map(n -> {
                n.setAgrrNode(dto);
                return n;
            }).collect(Collectors.toSet());
        }
        return repository.save(dto);
    }


    public void delete(Long id) {
        repository.deleteById(id);
    }

    public AgrrNodeDTO saveOrUpdate(AgrrNodeDTO dto) {

        repository.save(dto);
        //dto.setAddrs(addressRepository.findAllByAgrrNode(dto));

//         AddressDTO addressDTO = dto.getAddress() ;
//         addressDTO.setAgrrNode(dto);
//         addRepository.save(addressDTO);
//        return dto;
        return dto;
    }
}
