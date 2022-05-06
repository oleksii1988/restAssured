package jdbc;

import config.ConnectionFactory;
import lombok.SneakyThrows;
import modelDB.MappingModel;
import modelDB.ParticipantModel;
import modelDB.TournamentModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParticipantDAO implements DAO {

    ConnectionFactory connectionFactory = new ConnectionFactory();

    public ParticipantDAO() throws SQLException {
    }

    @Override
    @SneakyThrows
    public boolean delete(Integer key) {

        boolean result = false;

        PreparedStatement ps = connectionFactory.getPreparedStatement(ParticipantDAO.ParticipantQuerySQL.DELETE_PARTICIPANT.QUERY);
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

    public Integer getParticipantByIdPresentInCategory(Integer key) {

        Integer result = null;

        PreparedStatement ps = connectionFactory.getPreparedStatement(ParticipantDAO.ParticipantQuerySQL.GET_PARTICIPANT_PRESENT_IN_CATEGORY.QUERY);
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


    public boolean getParticipantInDB(Integer key) {

        boolean result = false;

        PreparedStatement ps = connectionFactory.getPreparedStatement(ParticipantDAO.ParticipantQuerySQL.GET_PARTICIPANT.QUERY);
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

    public boolean createParticipant(ParticipantModel model) {
        boolean result = false;
        PreparedStatement ps = connectionFactory.getPreparedStatement(ParticipantDAO.ParticipantQuerySQL.CREATE_PARTICIPANT.QUERY);

        try{
            ps.setInt(1,model.getId());
            ps.setString(2, model.getCountry());
            ps.setString(3, model.getFull_name());
            ps.setInt(4, model.getSport_id());
            ps.setInt(5,model.getCategory_id());



            result = ps.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();

        } finally {
            connectionFactory.closePrepareStatement(ps);
        }

        return result;

    }

    public MappingModel getMapParticipant(Integer key) {

        final MappingModel result = new MappingModel();
        result.setMappedId(key);
        PreparedStatement ps = connectionFactory.getPreparedStatement(ParticipantDAO.ParticipantQuerySQL.GET_MAP_PARTICIPANT.QUERY);
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
        PreparedStatement ps = connectionFactory.getPreparedStatement(ParticipantDAO.ParticipantQuerySQL.DELETE_PARTICIPANT_MAPPING.QUERY);
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
        PreparedStatement ps = connectionFactory.getPreparedStatement(ParticipantDAO.ParticipantQuerySQL.CREATE_PARTICIPANT_MAPPING.QUERY);

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

    @Override
    @SneakyThrows
    public boolean getMap(Integer key) {
        boolean result = false;
        PreparedStatement ps = connectionFactory.getPreparedStatement(ParticipantDAO.ParticipantQuerySQL.GET_MAP_PARTICIPANT.QUERY);
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

    enum ParticipantQuerySQL {
        DELETE_PARTICIPANT("DELETE FROM participant WHERE id = (?)"),
        GET_MAP_PARTICIPANT("SELECT* FROM participant_mapping WHERE mapped_id = (?)"),
        DELETE_PARTICIPANT_MAPPING("DELETE FROM participant_mapping WHERE mapped_id = (?)"),
        CREATE_PARTICIPANT_MAPPING("INSERT INTO participant_mapping (external_id, mapped_id, provider, name) VALUES ((?),(?),(?),(?))"),
        CREATE_PARTICIPANT("INSERT INTO participant (id, country, full_name, sport_id, category_id) VALUES ((?),(?),(?),(?),(?))"),
        GET_PARTICIPANT_PRESENT_IN_CATEGORY("SELECT c.id FROM category c JOIN participant p on p.category_id = c.id WHERE p.id = (?)"),
        GET_PARTICIPANT("SELECT* FROM participant WHERE id = (?)");



        String QUERY;

        ParticipantQuerySQL(String QUERY) {
            this.QUERY = QUERY;
        }
    }


}
