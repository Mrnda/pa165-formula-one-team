package cz.muni.fi.pa165.exceptions;

public class EntityNotFoundException extends RuntimeException {
    private long id;
    private String entityName;

    public EntityNotFoundException(long id, String entityName) {
        this.id = id;
        this.entityName = entityName;
    }

    public long getId() {
        return id;
    }

    public String getEntityName() {
        return entityName;
    }

    @Override
    public String getMessage() {
        return String.format("Entity of name %s with id %d was not found", getEntityName(), getId());
    }
}
