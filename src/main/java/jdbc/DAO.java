package jdbc;
import lombok.SneakyThrows;
import model.CategoryMappingModel;
import model.CategoryModel;

public interface DAO  {

    @SneakyThrows
    boolean delete(Integer key);
    boolean getMap(Integer key);
    boolean deleteMapping(Integer key);
    boolean create(CategoryMappingModel model);


}