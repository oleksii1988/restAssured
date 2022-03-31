package jdbc;
import lombok.SneakyThrows;

public interface DAO  {

    @SneakyThrows
    boolean delete(Integer key);
    boolean getMap(Integer key);
    boolean deleteMapping(Integer key);



}