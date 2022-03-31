package jdbc;

import config.ConnectionFactory;
import lombok.SneakyThrows;
import model.MappingModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SportDAO implements DAO{

    ConnectionFactory connectionFactory = new ConnectionFactory();

    @SneakyThrows
    public SportDAO() throws SQLException {
    }

    @Override
    public boolean delete(Integer key) {
        return false;
    }

    @Override
    @SneakyThrows
    public boolean getMap(Integer key) {
        boolean result = false;
        PreparedStatement ps = connectionFactory.getPreparedStatement(SportDAO.SportQuerySQL.GET_MAP_SPORT.QUERY);
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

    public MappingModel getMapSport(Integer key) {

        final MappingModel result = new MappingModel();
        result.setMappedId(key);
        PreparedStatement ps = connectionFactory.getPreparedStatement(SportDAO.SportQuerySQL.GET_MAP_SPORT.QUERY);
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

        return result;

    }

    @SneakyThrows
    @Override
    public boolean deleteMapping(Integer key) {
        boolean result = false;
        PreparedStatement ps = connectionFactory.getPreparedStatement(SportDAO.SportQuerySQL.DELETE_SPORT_MAPPING.QUERY);
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


    enum SportQuerySQL {
        DELETE_CATEGORY("DELETE FROM category WHERE id = (?)"),
        GET_MAP_SPORT("SELECT* FROM sport_mapping WHERE mapped_id = (?)"),
        DELETE_SPORT_MAPPING("DELETE FROM sport_mapping WHERE mapped_id = (?)"),
        CREATE_CATEGORY_MAPPING("INSERT INTO category_mapping (external_id, mapped_id, provider, name) VALUES ((?),(?),(?),(?))"),
        CREATE_CATEGORY("INSERT INTO category (id, name, sport_id, region_id) VALUES ((?),(?),(?),(?))"),
        GET_CATEGORY("SELECT* FROM category WHERE id = (?)");


        String QUERY;

        SportQuerySQL(String QUERY) {
            this.QUERY = QUERY;
        }
    }




}
