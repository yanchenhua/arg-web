package ltd.ontsol.core.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ltd.ontsol.core.constants.GlobalConstants;
import ltd.ontsol.core.dto.GlobalDTO;
import ltd.ontsol.core.dto.LongText;

/**
 * Created by cn40580 at 2016-10-09 10:05 AM.
 */
public interface GlobalRepository extends JpaRepository<GlobalDTO, Long> {

    @Query(value = "select globalName from GlobalDTO g group by g.globalName")
    List<LongText> findAllGlobalNames();

    @Query(value = "select cityName from GlobalDTO g where g.globalName = :name group by g.cityName")
    List<LongText> findAllCityNamesByGlobalName(@Param("name") LongText name);

    @Query(value =
            "SELECT DISTINCT LTT2.text " +
                    "FROM global G LEFT JOIN long_text LT2 ON G.global_name_id = LT2.id " +
                    "  LEFT JOIN long_text_trans LTT2 ON LT2.id = LTT2.long_text_id " +
                    "WHERE LTT2.lang = :locale AND G.type = :type " +
                    "GROUP BY LTT2.text", nativeQuery = true)
    //List<String> findAllGlobalNamesWithType(@Param("type") String type, @Param("locale") String locale);
    List<String> findAllGlobalNamesWithType(@Param("type") String type, @Param("locale") String locale);
    @Query(value =
            "SELECT DISTINCT LTT.text " +
                    "FROM global G LEFT JOIN long_text LT ON G.city_name_id = LT.id " +
                    "  LEFT JOIN long_text_trans LTT ON LT.id = LTT.long_text_id " +
                    "  LEFT JOIN long_text LT2 ON G.global_name_id = LT2.id " +
                    "  LEFT JOIN long_text_trans LTT2 ON LT2.id = LTT2.long_text_id " +
                    "WHERE LTT.lang = :locale AND G.type = :type AND LTT2.text = :globalName " +
                    "GROUP BY LTT.text", nativeQuery = true)
    List<String> findAllCityNamesWithGlobalNameAndType(@Param("globalName") String globalName, @Param("type") String type, @Param("locale") String locale);

    List<GlobalDTO> findAllByCityName(LongText cityName);

   /* @Query(value =
            "SELECT DISTINCT * " +
                    "FROM global G LEFT JOIN long_text LT ON G.city_name_id = LT.id " +
                    "  LEFT JOIN long_text_trans LTT ON LT.id = LTT.long_text_id " +
                    "  LEFT JOIN long_text LT2 ON LT2.id = G.global_name_id " +
                    "  LEFT JOIN long_text_trans LTT2 ON LT2.id = LTT2.long_text_id " +
                    "WHERE LTT.text = :cityName AND G.type = :type AND LTT2.text = :globalName", nativeQuery = true)*/
    @Query(value ="SELECT DISTINCT G.id,G.shoplatitude,G.shoplongitude,G.shoptel,G.shoptype,G.type,G.city_name_id,G.global_name_id,G.shop_address_id,G.shop_name_id,G.shop_contact_id,G.global_banner_att_id FROM global G LEFT JOIN long_text LT ON G.city_name_id = LT.id LEFT JOIN long_text_trans LTT ON LT.id = LTT.long_text_id LEFT JOIN long_text LT2 ON LT2.id = G.global_name_id LEFT JOIN long_text_trans LTT2 ON LT2.id = LTT2.long_text_id LEFT JOIN attahment AT1 ON AT1.id = G.global_banner_att_id WHERE LTT.text = :cityName AND G.type = :type AND LTT2.text = :globalName", nativeQuery = true)
    List<GlobalDTO> findAllByCityNameAndType(@Param("globalName") String globalName, @Param("cityName") String cityName, @Param("type") String type);

    List<GlobalDTO> findAllByType(GlobalConstants type);
}
