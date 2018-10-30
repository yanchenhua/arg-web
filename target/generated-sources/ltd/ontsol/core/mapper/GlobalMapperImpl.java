package ltd.ontsol.core.mapper;

import java.util.Locale;
import javax.annotation.Generated;
import ltd.ontsol.core.dto.GlobalDTO;
import ltd.ontsol.web.resource.GlobalShop;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_172 (Oracle Corporation)"
)
@Component
public class GlobalMapperImpl implements GlobalMapper {

    @Override
    public GlobalShop DTOtoResource(GlobalDTO entity, Locale locale) {
        if ( entity == null && locale == null ) {
            return null;
        }

        GlobalShop globalShop = new GlobalShop();

        if ( entity != null ) {
            globalShop.setShoptel( entity.getShoptel() );
            globalShop.setShoptype( entity.getShoptype() );
            globalShop.setShoplongitude( entity.getShoplongitude() );
            globalShop.setShoplatitude( entity.getShoplatitude() );
            globalShop.setGlobalBannerAttachment( entity.getGlobalBannerAttachment() );
        }
        globalShop.setShopcontact( ltd.ontsol.core.util.TranslationUtils.convertToString(entity.getShopcontact(), locale) );
        globalShop.setShopname( ltd.ontsol.core.util.TranslationUtils.convertToString(entity.getShopname(), locale) );
        globalShop.setShopaddress( ltd.ontsol.core.util.TranslationUtils.convertToString(entity.getShopaddress(), locale) );

        return globalShop;
    }
}
