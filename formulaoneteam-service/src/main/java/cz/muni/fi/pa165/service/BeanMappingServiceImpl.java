package cz.muni.fi.pa165.service;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
@Service
public class BeanMappingServiceImpl implements BeanMappingService {

    private Mapper dozer = new DozerBeanMapper();

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

    public Mapper getMapper() {
        return dozer;
    }
}
