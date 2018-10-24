package ltd.ontsol.core.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import ltd.ontsol.core.dto.StaffDTO;
import ltd.ontsol.web.soap.staff.Staff;

/**
 * Created by cn40580 at 2018-06-29 8:52 AM.
 */
@Mapper()
public interface StaffMapper {
    @Mappings({
            @Mapping(target = "type", ignore = true),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "errors", ignore = true),
            @Mapping(target = "code", ignore = true),
            @Mapping(target = "suggestions", ignore = true)
    })
    StaffDTO resourceToDTO(Staff entity);

    List<StaffDTO> resourcesToDTOs(List<Staff> entity);
}
