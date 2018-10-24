package ltd.ontsol.core.repo;

import ltd.ontsol.core.dto.LoginDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoginRepository extends JpaRepository<LoginDTO,Long> {
    //@Query(value = "select count(l.staff_name) from login_node l where l.password = :staffCode and l.staff_name = :staffName")
    @Query(value = "select count(l.staff_name) as cnt from login_node l where l.password = :staffCode and l.userid = :staffName and l.status = 1 ", nativeQuery = true)
    Integer countByStaffName(@Param("staffCode") String staffCode, @Param("staffName") String name);

    @Query(value = "select count(l.staff_name) as cnt from login_node l where l.userid = :userid", nativeQuery = true)
    Integer countByUserid(@Param("userid") String userid);

    @Query(value = "select count(l.staff_name) as cnt from login_node l where l.staff_name = :staffName and l.staff_org = :staffOrg and l.staff_code = :staffCode and l.apartment = :apartment  and l.status = 1", nativeQuery = true)
    Integer countByAll(@Param("staffCode") String staffCode, @Param("staffName") String name,@Param("staffOrg") String staffOrg ,@Param("apartment") String apartment);
}
