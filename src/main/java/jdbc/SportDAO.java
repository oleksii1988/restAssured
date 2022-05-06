package jdbc;

import config.ConnectionFactory;
import lombok.SneakyThrows;
import modelDB.MappingModel;
import modelDB.SportModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SportDAO implements DAO{

    ConnectionFactory connectionFactory = new ConnectionFactory();

    @SneakyThrows
    public SportDAO() throws SQLException {
    }

    @Override
    @SneakyThrows
    public boolean delete(Integer key) {

        boolean result = false;

        PreparedStatement ps = connectionFactory.getPreparedStatement(SportDAO.SportQuerySQL.DELETE_SPORT.QUERY);
        try {

            ps.setInt(1, key);


            result = ps.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionFactory.closePrepareStatement(ps);
        }

        return result;
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
            e.printStackTrace();
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
            e.printStackTrace();
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

            result =ps.execute();
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
        PreparedStatement ps = connectionFactory.getPreparedStatement(SportDAO.SportQuerySQL.CREATE_SPORT_MAPPING.QUERY);

        try{
            ps.setString(1,model.getExternalId());
            ps.setInt(2,model.getMappedId());
            ps.setString(3,model.getProvider());
            ps.setString(4,model.getName());

            result =ps.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionFactory.closePrepareStatement(ps);
        }

        return result;

    }

    public SportModel getSportById(Integer key) {

        final SportModel result = new SportModel();
        result.setId(key);
        PreparedStatement ps = connectionFactory.getPreparedStatement(SportDAO.SportQuerySQL.GET_SPORT.QUERY);
        try {

            ps.setInt(1, key);

            final ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()) {

                result.setId(resultSet.getInt("id"));
                result.setName(resultSet.getString("name"));
                /*result.setTranslations(resultSet.getObject("translations",));*/

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionFactory.closePrepareStatement(ps);
        }


        return result;

    }

    public boolean createNewSport(SportModel model) {
        boolean result = false;
        PreparedStatement ps = connectionFactory.getPreparedStatement(SportDAO.SportQuerySQL.CREATE_SPORT.QUERY);

        try{
            ps.setInt(1,model.getId());
            ps.setString(2,model.getName());


            result = ps.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionFactory.closePrepareStatement(ps);
        }

        return result;

    }


    enum SportQuerySQL {
        DELETE_SPORT("DELETE FROM sport WHERE id = (?)"),
        GET_MAP_SPORT("SELECT* FROM sport_mapping WHERE mapped_id = (?)"),
        DELETE_SPORT_MAPPING("DELETE FROM sport_mapping WHERE mapped_id = (?)"),
        CREATE_SPORT_MAPPING("INSERT INTO sport_mapping (external_id, mapped_id, provider, name) VALUES ((?),(?),(?),(?))"),
        CREATE_SPORT("INSERT INTO sport (id, name) VALUES ((?),(?))"),
        GET_SPORT("SELECT* FROM sport WHERE id = (?)");


        String QUERY;

        SportQuerySQL(String QUERY) {
            this.QUERY = QUERY;
        }
    }




}
