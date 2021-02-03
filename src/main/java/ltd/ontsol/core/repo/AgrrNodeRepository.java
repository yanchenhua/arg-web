package ltd.ontsol.core.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ltd.ontsol.core.dto.AgrrNodeDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by cn40580 at 2016-10-09 10:05 AM.
 */
public interface AgrrNodeRepository extends JpaRepository<AgrrNodeDTO, Long> {

    List<AgrrNodeDTO> findAllByGoodProvider(Boolean bool);
    @Query(value = "select count(l.username) as cnt from agrr_node l where l.username = :username and l.password = :password", nativeQuery = true)
    Integer countByLoginInfo(@Param("username") String username,@Param("password") String password);
    @Query(value = "select count(l.username) as cnt from agrr_node l where l.username = :username", nativeQuery = true)
    Integer countByUsername(@Param("username") String username);

    @Query(value = "select * from agrr_node l where l.username = :username and l.password = :password", nativeQuery = true)
    List<AgrrNodeDTO> findByLoginInfo(@Param("username") String username,@Param("password") String password);
    @Query(value = "select * from agrr_node l where l.verstatus = :keyword", nativeQuery = true)
    List<AgrrNodeDTO> findByVerStatus(@Param("keyword") String keyword);
}
