package jdbc;
import connectionDB.ConnectionConfig;
import lombok.SneakyThrows;
import modelDB.MappingModel;
import modelDB.CategoryModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class CategoryDAO implements DAO {

    ConnectionConfig connectionFactory = new ConnectionConfig();

    @SneakyThrows
    public CategoryDAO() throws SQLException {

    }

    @Override
    @SneakyThrows
    public boolean delete(Integer key) {

        boolean result = false;

        PreparedStatement ps = connectionFactory.getPreparedStatement(CategoryQuerySQL.DELETE_CATEGORY.QUERY);
        try {

            ps.setInt(1, key);


            result = ps.executeQuery().next();


        } catch (SQLException e) {


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


        } finally {
            connectionFactory.closePrepareStatement(ps);
        }

        return result;
    }

    public CategoryModel getCategoryInDB(Integer x) {
       final CategoryModel result = new CategoryModel();
        result.setId(x);
        PreparedStatement ps = connectionFactory.getPreparedStatement(CategoryQuerySQL.GET_CATEGORY.QUERY);
        try {

            ps.setInt(1, x);

            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()) {

                result.setId(resultSet.getInt("id"));
                result.setName(resultSet.getString("name"));
                result.setSport_id(resultSet.getInt("sport_id"));
                result.setRegion_id(resultSet.getInt("region_id"));

            }


        } catch (SQLException e) {


        } finally {
            connectionFactory.closePrepareStatement(ps);
        }

        return result;
    }




    @SneakyThrows
    @Override
    public boolean deleteMapping(Integer key) {
        boolean result = false;
        PreparedStatement ps = connectionFactory.getPreparedStatement(CategoryQuerySQL.DELETE_CATEGORY_MAPPING.QUERY);
        try{
            ps.setInt(1,key);

            result = ps.execute();
        }
     catch (SQLException e) {

         e.printStackTrace();
    } finally {
        connectionFactory.closePrepareStatement(ps);
    }

        return result;

    }


    @SneakyThrows
    @Override
    public boolean createMapping(MappingModel model) {
        boolean result = false;
        PreparedStatement ps = connectionFactory.getPreparedStatement(CategoryQuerySQL.CREATE_CATEGORY_MAPPING.QUERY);

        try{
            ps.setString(1,model.getExternalId());
            ps.setInt(2,model.getMappedId());
            ps.setString(3,model.getProvider());
            ps.setString(4,model.getName());

            result =ps.executeQuery().next();
        }
        catch (SQLException e) {


        } finally {
            connectionFactory.closePrepareStatement(ps);
        }

        return result;

    }


    public boolean createCategory(CategoryModel model) {
        boolean result = false;
        PreparedStatement ps = connectionFactory.getPreparedStatement(CategoryQuerySQL.CREATE_CATEGORY.QUERY);

        try{
            ps.setInt(1,model.getId());
            ps.setString(2,model.getName());
            ps.setInt(3,model.getSport_id());
            ps.setInt(4,model.getRegion_id());

            result = ps.execute();
        }
        catch (SQLException e) {

            e.printStackTrace();
        } finally {
            connectionFactory.closePrepareStatement(ps);
        }

        return result;

    }


    public MappingModel getMapCategory(Integer key) {

        final MappingModel result = new MappingModel();
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


        } finally {
            connectionFactory.closePrepareStatement(ps);
        }

        return result;

    }


    enum CategoryQuerySQL {
        DELETE_CATEGORY("DELETE FROM category WHERE id = (?)"),
        GET_MAP_CATEGORY("SELECT* FROM category_mapping WHERE mapped_id = (?)"),
        DELETE_CATEGORY_MAPPING("DELETE FROM category_mapping WHERE mapped_id = (?)"),
        CREATE_CATEGORY_MAPPING("INSERT INTO category_mapping (external_id, mapped_id, provider, name) VALUES ((?),(?),(?),(?))"),
        CREATE_CATEGORY("INSERT INTO category (id, name, sport_id, region_id) VALUES ((?),(?),(?),(?))"),
        GET_CATEGORY("SELECT* FROM category WHERE id = (?)");


        String QUERY;

        CategoryQuerySQL(String QUERY) {
            this.QUERY = QUERY;
        }
    }

}
