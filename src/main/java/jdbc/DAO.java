package jdbc;
import lombok.SneakyThrows;
import modelDB.MappingModel;

public interface DAO  {

    @SneakyThrows
    boolean delete(Integer key);
    boolean getMap(Integer key);
    boolean deleteMapping(Integer key);
    boolean createMapping(MappingModel model);



}