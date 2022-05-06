package jdbc;

import config.ConnectionFactory;
import lombok.SneakyThrows;
import modelDB.CategoryModel;
import modelDB.MappingModel;
import modelDB.SportModel;
import modelDB.TournamentModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TournamentDAO implements DAO {

    ConnectionFactory connectionFactory = new ConnectionFactory();

    public TournamentDAO() throws SQLException {
    }


    @Override
    @SneakyThrows
    public boolean delete(Integer key) {

        boolean result = false;

        PreparedStatement ps = connectionFactory.getPreparedStatement(TournamentDAO.TournamentQuerySQL.DELETE_TOURNAMENT.QUERY);
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

    public boolean createTournament(TournamentModel model) {
        boolean result = false;
        PreparedStatement ps = connectionFactory.getPreparedStatement(TournamentDAO.TournamentQuerySQL.CREATE_TOURNAMENT.QUERY);

        try{
            ps.setInt(1,model.getId());
            ps.setInt(2,model.getCategory_id());
            ps.setString(3, model.getName());
            ps.setInt(4,model.getSport_form_id());

            result = ps.execute();
        }
        catch (SQLException e) {

            e.printStackTrace();
        } finally {
            connectionFactory.closePrepareStatement(ps);
        }


        return result;

    }


    public boolean getTournamentInDB(Integer key) {

        boolean result = false;

        PreparedStatement ps = connectionFactory.getPreparedStatement(TournamentDAO.TournamentQuerySQL.GET_TOURNAMENT.QUERY);
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

    public Integer getTournamentByIdPresentInCategory(Integer key) {

        Integer result = null;

        PreparedStatement ps = connectionFactory.getPreparedStatement(TournamentDAO.TournamentQuerySQL.GET_TOURNAMENT_PRESENT_IN_CATEGORY.QUERY);
        try {

            ps.setInt(1, key);


            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()) {

                result = resultSet.getInt("id");

            }

        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            connectionFactory.closePrepareStatement(ps);
        }
        return result;
    }



    @Override
    public boolean getMap(Integer key) {
        boolean result = false;
        PreparedStatement ps = connectionFactory.getPreparedStatement(TournamentDAO.TournamentQuerySQL.GET_MAP_TOURNAMENT.QUERY);
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

    public MappingModel getMapTournament(Integer key) {

        final MappingModel result = new MappingModel();
        result.setMappedId(key);
        PreparedStatement ps = connectionFactory.getPreparedStatement(TournamentDAO.TournamentQuerySQL.GET_MAP_TOURNAMENT.QUERY);
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
        PreparedStatement ps = connectionFactory.getPreparedStatement(TournamentDAO.TournamentQuerySQL.DELETE_TOURNAMENT_MAPPING.QUERY);
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
        PreparedStatement ps = connectionFactory.getPreparedStatement(TournamentDAO.TournamentQuerySQL.CREATE_TOURNAMENT_MAPPING.QUERY);

        try{
            ps.setString(1,model.getExternalId());
            ps.setInt(2,model.getMappedId());
            ps.setString(3,model.getProvider());
            ps.setString(4,model.getName());

            result = ps.execute();
        }
        catch (SQLException e) {

            e.printStackTrace();
        } finally {
            connectionFactory.closePrepareStatement(ps);
        }

        return result;

    }

    enum TournamentQuerySQL {
        DELETE_TOURNAMENT("DELETE FROM tournament WHERE category_id = (?)"),
        GET_MAP_TOURNAMENT("SELECT* FROM tournament_mapping WHERE mapped_id = (?)"),
        DELETE_TOURNAMENT_MAPPING("DELETE FROM tournament_mapping WHERE mapped_id = (?)"),
        CREATE_TOURNAMENT_MAPPING("INSERT INTO tournament_mapping (external_id, mapped_id, provider, name) VALUES ((?),(?),(?),(?))"),
        CREATE_SPORT("INSERT INTO sport (id, name) VALUES ((?),(?))"),
        GET_TOURNAMENT_PRESENT_IN_CATEGORY("SELECT c.id FROM category c JOIN tournament t on t.category_id = c.id WHERE t.id = (?)"),
        GET_TOURNAMENT("SELECT* FROM tournament WHERE id = (?)"),
        CREATE_TOURNAMENT("INSERT INTO tournament (id, category_id, name, sport_form_id) VALUES ((?),(?),(?),(?))");


        String QUERY;

        TournamentQuerySQL(String QUERY) {
            this.QUERY = QUERY;
        }
    }



}
