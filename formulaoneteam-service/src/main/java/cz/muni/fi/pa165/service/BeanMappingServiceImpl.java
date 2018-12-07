package cz.muni.fi.pa165.service;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
@Service
public class BeanMappingServiceImpl implements BeanMappingService {

    @Inject
    private Mapper dozer;

    public <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass) {
        List<T> mappedCollection = new ArrayList<>();
        for (Object object : objects) {
            mappedCollection.add(dozer.map(object, mapToClass));
        }
        return mappedCollection;
    }

    public <T> T mapTo(Object object, Class<T> mapToClass) {
        return dozer.map(object, mapToClass);
    }
    
    public <T> Map<T, List<String>> mapTo(Map<?, List<String>> map, Class<T> mapToClass) {
        Map<T, List<String>> result = new HashMap<>();

        for (Object key : map.keySet()) {
            result.put(mapTo(key, mapToClass), map.get(key));
        }

        return result;
    }

    public Mapper getMapper() {
        return dozer;
    }
}
