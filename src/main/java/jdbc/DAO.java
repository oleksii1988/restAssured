package jdbc;

public interface DAO <Entity, Key> {

    boolean delete(Entity key);

}
