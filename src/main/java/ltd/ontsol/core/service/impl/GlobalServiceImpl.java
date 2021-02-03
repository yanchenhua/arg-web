package ltd.ontsol.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.inject.Inject;

import ltd.ontsol.web.resource.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import ltd.ontsol.core.constants.GlobalConstants;
import ltd.ontsol.core.dto.GlobalDTO;
import ltd.ontsol.core.dto.LongText;
import ltd.ontsol.core.mapper.GlobalMapper;

import ltd.ontsol.core.repo.GlobalRepository;
import ltd.ontsol.core.repo.LongTextRepository;
import ltd.ontsol.core.service.GlobalService;
import ltd.ontsol.core.util.TranslationUtils;


/**
 * Created by cn40580 at 2016-10-10 10:05 AM.
 */
@Component("globalService")
public class GlobalServiceImpl implements GlobalService {

    private static final Log log = LogFactory.getLog(GlobalServiceImpl.class);

    @Inject
    GlobalRepository repository;

    @Inject
    LongTextRepository longTextRepository;

    @Inject
    GlobalMapper mapper;


    @Override
    public List<GlobalResource> findAllGlobalResource(Locale locale) {
        List<GlobalResource> result = new ArrayList<>();

        List<LongText> globalNames = repository.findAllGlobalNames();
        globalNames.forEach(global -> {
            GlobalResource globalItem = new GlobalResource();
            List<GlobalCityResource> cities = new ArrayList<>();
            List<LongText> cityNames = repository.findAllCityNamesByGlobalName(global);

            cityNames.forEach(city -> {
                GlobalCityResource cityItem = new GlobalCityResource();
                List<GlobalShop> shops = repository.findAllByCityName(city).stream().map(dto -> mapper.DTOtoResource(dto, locale)).collect(Collectors.toList());
                cityItem.setName(TranslationUtils.convertToString(city, locale));
                cityItem.setShops(shops);
                cities.add(cityItem);
            });
            globalItem.setName(TranslationUtils.convertToString(global, locale));
            globalItem.setList(cities);
            result.add(globalItem);
        });


        return result;
    }

    @Override
    public List<GlobalResource> findAllGlobalResourceByType(GlobalConstants type, Locale locale) {
        List<GlobalResource> result = new ArrayList<>();
        System.out.println("type.name() =====>"+type.name());
        List<String> globalNames = repository.findAllGlobalNamesWithType(type.name(), locale.toString());


        globalNames.forEach(global -> {
            GlobalResource globalItem = new GlobalResource();
            List<GlobalCityAreaResource> cities = new ArrayList<>();
            List<String> cityNames = repository.findAllCityNamesWithGlobalNameAndType(global, type.name(), locale.toString());

            cityNames.forEach(city -> {

                GlobalCityAreaResource cityItem = new GlobalCityAreaResource();
                List<GlobalAreaResource> areas = new ArrayList<>();
                List<String> areaNames = repository.findAllAreaNamesWithCityNameAndType(global,city,type.name(),locale.toString());
                areaNames.forEach(area -> {
                    GlobalAreaResource areaItem = new GlobalAreaResource();
                    List<GlobalShop> shops = repository.findAllByCityNameAndType(global, city, area, type.name()).stream()
                            .map(dto -> mapper.DTOtoResource(dto, locale)).collect(Collectors.toList());
                    areaItem.setShops(shops);
                    areaItem.setName(area);
                    areas.add(areaItem);
                });


                cityItem.setName(city);
                cityItem.setArea(areas);
                cities.add(cityItem);
            });
            globalItem.setName(global);
            globalItem.setCity(cities);
            result.add(globalItem);
        });


        return result;
    }
    @Override
    public List<GlobalResource> findAllGlobalResourceByType2(GlobalConstants type, Locale locale) {
        List<GlobalResource> result = new ArrayList<>();
        System.out.println("type.name() =====>"+type.name());
        List<String> globalNames = repository.findAllGlobalNamesWithType(type.name(), locale.toString());


        globalNames.forEach(global -> {
            GlobalResource globalItem = new GlobalResource();
            List<GlobalCityResource> cities = new ArrayList<>();
            List<String> cityNames = repository.findAllCityNamesWithGlobalNameAndType(global, type.name(), locale.toString());

            cityNames.forEach(city -> {

                GlobalCityResource cityItem = new GlobalCityResource();

                List<GlobalShop> shops = repository.findAllByCityNameAndType2(global, city ,type.name()).stream()
                        .map(dto -> mapper.DTOtoResource(dto, locale)).collect(Collectors.toList());

                cityItem.setName(city);
                cityItem.setShops(shops);
                cities.add(cityItem);
            });
            globalItem.setName(global);
            globalItem.setList(cities);
            result.add(globalItem);
        });


        return result;
    }

    public List<GlobalDTO> findAll() {
        return repository.findAll();
    }

    @Override
    public List<GlobalDTO> findAll(GlobalDTO dto) {
        return repository.findAll(Example.of(dto));
    }

    public List<GlobalDTO> findAllByType(GlobalConstants type) {
        return repository.findAllByType(type);
    }

    public GlobalDTO saveOrUpdate(GlobalDTO dto) {
        return repository.save(dto);
    }

    @Override
    public GlobalDTO findById(Long id) {
        return repository.findById(id).get();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
