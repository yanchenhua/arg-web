package ltd.ontsol.core.mapper;

import java.util.Locale;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import ltd.ontsol.core.dto.GlobalDTO;
import ltd.ontsol.web.resource.GlobalShop;

/**
 * Created by cn40580 at 2018-06-29 8:52 AM.
 */
@Mapper()
public interface GlobalMapper {
    @Mappings({
            @Mapping(target = "shopname",
                    expression = "java(ltd.ontsol.core.util.TranslationUtils.convertToString(entity.getShopname(), locale))"),
            @Mapping(target = "shopaddress",
                    expression = "java(ltd.ontsol.core.util.TranslationUtils.convertToString(entity.getShopaddress(), locale))"),
            @Mapping(target = "shopcontact",
                    expression = "java(ltd.ontsol.core.util.TranslationUtils.convertToString(entity.getShopcontact(), locale))")

    })
    GlobalShop DTOtoResource(GlobalDTO entity, Locale locale);
}
