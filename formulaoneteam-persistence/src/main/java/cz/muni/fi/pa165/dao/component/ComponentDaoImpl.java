package cz.muni.fi.pa165.dao.component;

import cz.muni.fi.pa165.entity.Component;
import cz.muni.fi.pa165.dao.base.DaoImpl;
import org.springframework.stereotype.Repository;

/**
 * @author Théo Desjardins
 */
@Repository
public class ComponentDaoImpl extends DaoImpl<Component> implements ComponentDao {

    @Override
    protected Class<Component> getClassType() {
        return Component.class;
    }

    protected void validateEntity(Component component) {
        if (component == null) {
            throw new IllegalArgumentException("Component is null.");
        }
    }
}
