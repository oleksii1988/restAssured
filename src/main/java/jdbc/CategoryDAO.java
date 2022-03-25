package jdbc;
import config.ConnectionFactory;
import lombok.SneakyThrows;
import model.CategoryMappingModel;
import model.CategoryModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class CategoryDAO implements DAO {


    CategoryModel categoryModel = new CategoryModel();
    ConnectionFactory connectionFactory = new ConnectionFactory();

    @SneakyThrows
    public CategoryDAO() throws SQLException {

    }


 /*   @Override
    public boolean delete(Integer key) throws SQLException {

        boolean result = false;

        try (PreparedStatement statement = databaseConfig.getConnection().prepareStatement(CategoryQuerySQL.DELETE.QUERY)) {

            statement.setInt(1, key);

            result = statement.executeQuery().next();


        } catch (SQLException e) {

            System.out.println("Entity does not exist");
        } finally {
            databaseConfig.getConnection().close();
        }

        return result;
    }*/

    @Override
    @SneakyThrows
    public boolean delete(Integer key) {

        boolean result = false;

        PreparedStatement ps = connectionFactory.getPreparedStatement(CategoryQuerySQL.DELETE_CATEGORY.QUERY);
        try {

            ps.setInt(1, key);


            result = ps.executeQuery().next();


        } catch (SQLException e) {

            System.out.println("Entity does not exist");
        } finally {
            connectionFactory.closePrepareStatement(ps);
        }

        return result;
    }

    @Override
    @SneakyThrows
    public boolean getMap(Integer key) {
        boolean result = false;
        PreparedStatement ps = connectionFactory.getPreparedStatement(CategoryQuerySQL.GET_MAP_CATEGORY.QUERY);
        try {

            ps.setInt(1, key);

            result = ps.executeQuery().next();


        } catch (SQLException e) {

            System.out.println("Entity does not exist");
        } finally {
            connectionFactory.closePrepareStatement(ps);
        }

        return result;
    }

    @SneakyThrows
    @Override
    public boolean deleteMapping(Integer key) {
        boolean result = false;
        PreparedStatement ps = connectionFactory.getPreparedStatement(CategoryQuerySQL.DELETE_MAPPING.QUERY);
        try{
            ps.setInt(1,key);

            result =ps.executeQuery().next();
        }
     catch (SQLException e) {

        System.out.println("Entity does not exist");
    } finally {
        connectionFactory.closePrepareStatement(ps);
    }

        return result;

    }


    public CategoryMappingModel getMapCategory(Integer key) {

        final CategoryMappingModel result = new CategoryMappingModel();
        result.setMappedId(key);
        PreparedStatement ps = connectionFactory.getPreparedStatement(CategoryQuerySQL.GET_MAP_CATEGORY.QUERY);
        try {

            ps.setInt(1, key);

            final ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()) {

                result.setExternalId(resultSet.getString("external_id"));
                result.setProvider(resultSet.getString("provider"));
                result.setName(resultSet.getString("name"));

            }


        } catch (SQLException e) {

            System.out.println("Entity does not exist");
        } finally {
            connectionFactory.closePrepareStatement(ps);
        }


        System.out.println(result);
        return result;

    }


    enum CategoryQuerySQL {
        DELETE_CATEGORY("DELETE FROM category WHERE id = (?)"),
        GET_MAP_CATEGORY("SELECT* FROM category_mapping WHERE mapped_id = (?)"),
        DELETE_MAPPING("DELETE FROM category_mapping WHERE mapped_id = (?)");


        String QUERY;

        CategoryQuerySQL(String QUERY) {
            this.QUERY = QUERY;
        }
    }

}
