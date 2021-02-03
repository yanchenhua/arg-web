package ltd.ontsol.core.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import ltd.ontsol.core.dto.StaffDTO;
import ltd.ontsol.web.soap.staff.Staff;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_251 (Oracle Corporation)"
)
@Component
public class StaffMapperImpl implements StaffMapper {

    @Override
    public StaffDTO resourceToDTO(Staff entity) {
        if ( entity == null ) {
            return null;
        }

        StaffDTO staffDTO = new StaffDTO();

        staffDTO.setStaffCode( entity.getStaffCode() );
        staffDTO.setName( entity.getName() );

        return staffDTO;
    }

    @Override
    public List<StaffDTO> resourcesToDTOs(List<Staff> entity) {
        if ( entity == null ) {
            return null;
        }

        List<StaffDTO> list = new ArrayList<StaffDTO>( entity.size() );
        for ( Staff staff : entity ) {
            list.add( resourceToDTO( staff ) );
        }

        return list;
    }
}
