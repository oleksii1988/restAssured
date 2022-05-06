package jdbc;

import connectionDB.ConnectionConfig;
import modelDB.EventModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EventDAO {

    ConnectionConfig connectionFactory = new ConnectionConfig();

    public EventDAO() throws SQLException {
    }



    public EventModel getLsportMappedEventsWithAllStatusFromDB() {

        final EventModel result = new EventModel();

        PreparedStatement ps = connectionFactory.getPreparedStatement(EventQuerySQL.GET_MAPPED_EVENT_ALL_STATUS_LSPORT.QUERY);
        try {



            final ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()) {

                result.setSport_id(resultSet.getInt("sport_id"));
                result.setCategory_id(resultSet.getInt("category_id"));
                result.setTournament_id(resultSet.getInt("tournament_id"));
                result.setProvider(resultSet.getString("provider"));
                result.setStatus(resultSet.getString("status"));
                result.setName(resultSet.getString("name"));
                result.setOutright(resultSet.getBoolean("outright"));
                result.setMapped_id(resultSet.getInt("mapped_id"));

            }


        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            connectionFactory.closePrepareStatement(ps);
        }

        return result;

    }

    public EventModel getLsportMappedEventsWithNotStartedStatusWithoutOutrightFromDB() {

        final EventModel result = new EventModel();

        PreparedStatement ps = connectionFactory.getPreparedStatement(EventQuerySQL.GET_EVENT_NOT_STARTED_STATUS_LSPORT.QUERY);
        try {



            final ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()) {

                result.setSport_id(resultSet.getInt("sport_id"));
                result.setCategory_id(resultSet.getInt("category_id"));
                result.setTournament_id(resultSet.getInt("tournament_id"));
                result.setProvider(resultSet.getString("provider"));
                result.setStatus(resultSet.getString("status"));
                result.setName(resultSet.getString("name"));
                result.setOutright(resultSet.getBoolean("outright"));
                result.setMapped_id(resultSet.getInt("mapped_id"));
                result.setExternal_id(resultSet.getString("external_id"));

            }


        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            connectionFactory.closePrepareStatement(ps);
        }

        return result;

    }

    public EventModel getLsportMappedEventsWithNotStartedStatusWithOutrightFromDB() {

        final EventModel result = new EventModel();

        PreparedStatement ps = connectionFactory.getPreparedStatement(EventQuerySQL.GET_OUTRIGHT_EVENT_NOT_STARTED_STATUS_LSPORT.QUERY);
        try {



            final ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()) {

                result.setSport_id(resultSet.getInt("sport_id"));
                result.setCategory_id(resultSet.getInt("category_id"));
                result.setTournament_id(resultSet.getInt("tournament_id"));
                result.setProvider(resultSet.getString("provider"));
                result.setStatus(resultSet.getString("status"));
                result.setName(resultSet.getString("name"));
                result.setOutright(resultSet.getBoolean("outright"));
                result.setMapped_id(resultSet.getInt("mapped_id"));

            }


        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            connectionFactory.closePrepareStatement(ps);
        }

        return result;

    }

    public EventModel getLsportMappedEventsWithLiveStatusFromDB() {

        final EventModel result = new EventModel();

        PreparedStatement ps = connectionFactory.getPreparedStatement(EventQuerySQL.GET_EVENT_LIVE_STATUS_LSPORT.QUERY);
        try {



            final ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()) {

                result.setSport_id(resultSet.getInt("sport_id"));
                result.setCategory_id(resultSet.getInt("category_id"));
                result.setTournament_id(resultSet.getInt("tournament_id"));
                result.setProvider(resultSet.getString("provider"));
                result.setStatus(resultSet.getString("status"));
                result.setName(resultSet.getString("name"));
                result.setOutright(resultSet.getBoolean("outright"));
                result.setMapped_id(resultSet.getInt("mapped_id"));
                result.setExternal_id(resultSet.getString("external_id"));

            }


        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            connectionFactory.closePrepareStatement(ps);
        }

        return result;

    }

    public EventModel getLsportMappedEventsWithFinishedStatusWithoutOutrightFromDB() {

        final EventModel result = new EventModel();

        PreparedStatement ps = connectionFactory.getPreparedStatement(EventQuerySQL.GET_EVENT_FINISHED_STATUS_LSPORT.QUERY);
        try {



            final ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()) {

                result.setSport_id(resultSet.getInt("sport_id"));
                result.setCategory_id(resultSet.getInt("category_id"));
                result.setTournament_id(resultSet.getInt("tournament_id"));
                result.setProvider(resultSet.getString("provider"));
                result.setStatus(resultSet.getString("status"));
                result.setName(resultSet.getString("name"));
                result.setOutright(resultSet.getBoolean("outright"));
                result.setMapped_id(resultSet.getInt("mapped_id"));
                result.setExternal_id(resultSet.getString("external_id"));

            }


        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            connectionFactory.closePrepareStatement(ps);
        }

        return result;

    }

    public EventModel getLsportMappedEventsWithFinishedStatusWithOutrightFromDB() {

        final EventModel result = new EventModel();

        PreparedStatement ps = connectionFactory.getPreparedStatement(EventQuerySQL.GET_OUTRIGHT_EVENT_FINISHED_STATUS_LSPORT.QUERY);
        try {



            final ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()) {

                result.setSport_id(resultSet.getInt("sport_id"));
                result.setCategory_id(resultSet.getInt("category_id"));
                result.setTournament_id(resultSet.getInt("tournament_id"));
                result.setProvider(resultSet.getString("provider"));
                result.setStatus(resultSet.getString("status"));
                result.setName(resultSet.getString("name"));
                result.setOutright(resultSet.getBoolean("outright"));
                result.setMapped_id(resultSet.getInt("mapped_id"));

            }


        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            connectionFactory.closePrepareStatement(ps);
        }

        return result;

    }

    public EventModel getLsportUnmappedEventsWithAllStatusFromDB() {

        final EventModel result = new EventModel();

        PreparedStatement ps = connectionFactory.getPreparedStatement(EventQuerySQL.GET_UNMAPPED_EVENT_ALL_STATUS_LSPORT.QUERY);
        try {



            final ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()) {

                result.setSport_id(resultSet.getInt("sport_id"));
                result.setCategory_id(resultSet.getInt("category_id"));
                result.setTournament_id(resultSet.getInt("tournament_id"));
                result.setProvider(resultSet.getString("provider"));
                result.setStatus(resultSet.getString("status"));
                result.setName(resultSet.getString("name"));
                result.setOutright(resultSet.getBoolean("outright"));
                result.setMapped_id(resultSet.getInt("mapped_id"));

            }


        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            connectionFactory.closePrepareStatement(ps);
        }

        return result;

    }

    public EventModel getLsportUnmappedEventsWithNotStartedStatusWithoutOutrightFromDB() {

        final EventModel result = new EventModel();

        PreparedStatement ps = connectionFactory.getPreparedStatement(EventQuerySQL.GET_UNMAPPED_EVENT_NOT_STARTED_STATUS_LSPORT.QUERY);
        try {



            final ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()) {

                result.setSport_id(resultSet.getInt("sport_id"));
                result.setCategory_id(resultSet.getInt("category_id"));
                result.setTournament_id(resultSet.getInt("tournament_id"));
                result.setProvider(resultSet.getString("provider"));
                result.setStatus(resultSet.getString("status"));
                result.setName(resultSet.getString("name"));
                result.setOutright(resultSet.getBoolean("outright"));
                result.setMapped_id(resultSet.getInt("mapped_id"));

            }


        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            connectionFactory.closePrepareStatement(ps);
        }

        return result;

    }

    public EventModel getLsportUnmappedEventsWithNotStartedStatusWithOutrightFromDB() {

        final EventModel result = new EventModel();

        PreparedStatement ps = connectionFactory.getPreparedStatement(EventQuerySQL.GET_OUTRIGHT_UNMAPPED_EVENT_NOT_STARTED_STATUS_LSPORT.QUERY);
        try {



            final ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()) {

                result.setSport_id(resultSet.getInt("sport_id"));
                result.setCategory_id(resultSet.getInt("category_id"));
                result.setTournament_id(resultSet.getInt("tournament_id"));
                result.setProvider(resultSet.getString("provider"));
                result.setStatus(resultSet.getString("status"));
                result.setName(resultSet.getString("name"));
                result.setOutright(resultSet.getBoolean("outright"));
                result.setMapped_id(resultSet.getInt("mapped_id"));

            }


        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            connectionFactory.closePrepareStatement(ps);
        }

        return result;

    }

    public EventModel getLsportUnmappedEventsWithLiveStatusFromDB() {

        final EventModel result = new EventModel();

        PreparedStatement ps = connectionFactory.getPreparedStatement(EventQuerySQL.GET_UNMAPPED_EVENT_LIVE_STATUS_LSPORT.QUERY);
        try {



            final ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()) {

                result.setSport_id(resultSet.getInt("sport_id"));
                result.setCategory_id(resultSet.getInt("category_id"));
                result.setTournament_id(resultSet.getInt("tournament_id"));
                result.setProvider(resultSet.getString("provider"));
                result.setStatus(resultSet.getString("status"));
                result.setName(resultSet.getString("name"));
                result.setOutright(resultSet.getBoolean("outright"));
                result.setMapped_id(resultSet.getInt("mapped_id"));

            }


        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            connectionFactory.closePrepareStatement(ps);
        }

        return result;

    }

    public EventModel getLsportUnmappedEventsWithFinishedStatusWithoutOutrightFromDB() {

        final EventModel result = new EventModel();

        PreparedStatement ps = connectionFactory.getPreparedStatement(EventQuerySQL.GET_UNMAPPED_EVENT_FINISHED_STATUS_LSPORT.QUERY);
        try {



            final ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()) {

                result.setSport_id(resultSet.getInt("sport_id"));
                result.setCategory_id(resultSet.getInt("category_id"));
                result.setTournament_id(resultSet.getInt("tournament_id"));
                result.setProvider(resultSet.getString("provider"));
                result.setStatus(resultSet.getString("status"));
                result.setName(resultSet.getString("name"));
                result.setOutright(resultSet.getBoolean("outright"));
                result.setMapped_id(resultSet.getInt("mapped_id"));

            }


        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            connectionFactory.closePrepareStatement(ps);
        }

        return result;

    }

    public EventModel getLsportUnmappedEventsWithFinishedStatusWithOutrightFromDB() {

        final EventModel result = new EventModel();

        PreparedStatement ps = connectionFactory.getPreparedStatement(EventQuerySQL.GET_OUTRIGHT_UNMAPPED_EVENT_FINISHED_STATUS_LSPORT.QUERY);
        try {



            final ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()) {

                result.setSport_id(resultSet.getInt("sport_id"));
                result.setCategory_id(resultSet.getInt("category_id"));
                result.setTournament_id(resultSet.getInt("tournament_id"));
                result.setProvider(resultSet.getString("provider"));
                result.setStatus(resultSet.getString("status"));
                result.setName(resultSet.getString("name"));
                result.setOutright(resultSet.getBoolean("outright"));
                result.setMapped_id(resultSet.getInt("mapped_id"));

            }


        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            connectionFactory.closePrepareStatement(ps);
        }

        return result;

    }




    public EventModel getLongShotMappedEventsWithAllStatusFromDB() {

        final EventModel result = new EventModel();

        PreparedStatement ps = connectionFactory.getPreparedStatement(EventQuerySQL.GET_EVENT_ALL_STATUS_LONGSHOT.QUERY);
        try {



            final ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()) {

                result.setSport_id(resultSet.getInt("sport_id"));
                result.setCategory_id(resultSet.getInt("category_id"));
                result.setTournament_id(resultSet.getInt("tournament_id"));
                result.setProvider(resultSet.getString("provider"));
                result.setStatus(resultSet.getString("status"));
                result.setName(resultSet.getString("name"));
                result.setOutright(resultSet.getBoolean("outright"));
                result.setMapped_id(resultSet.getInt("mapped_id"));

            }


        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            connectionFactory.closePrepareStatement(ps);
        }

        return result;

    }

    public EventModel getLongShotMappedEventsWithNotStartedStatusFromDB() {

        final EventModel result = new EventModel();

        PreparedStatement ps = connectionFactory.getPreparedStatement(EventQuerySQL.GET_EVENT_NOT_STARTED_STATUS_LONGSHOT.QUERY);
        try {



            final ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()) {

                result.setSport_id(resultSet.getInt("sport_id"));
                result.setCategory_id(resultSet.getInt("category_id"));
                result.setTournament_id(resultSet.getInt("tournament_id"));
                result.setProvider(resultSet.getString("provider"));
                result.setStatus(resultSet.getString("status"));
                result.setName(resultSet.getString("name"));
                result.setOutright(resultSet.getBoolean("outright"));
                result.setMapped_id(resultSet.getInt("mapped_id"));
                result.setExternal_id(resultSet.getString("external_id"));

            }


        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            connectionFactory.closePrepareStatement(ps);
        }

        return result;

    }

    public EventModel getLongShotMappedEventsWithLiveStatusFromDB() {

        final EventModel result = new EventModel();

        PreparedStatement ps = connectionFactory.getPreparedStatement(EventQuerySQL.GET_EVENT_LIVE_STATUS_LONGSHOT.QUERY);
        try {



            final ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()) {

                result.setSport_id(resultSet.getInt("sport_id"));
                result.setCategory_id(resultSet.getInt("category_id"));
                result.setTournament_id(resultSet.getInt("tournament_id"));
                result.setProvider(resultSet.getString("provider"));
                result.setStatus(resultSet.getString("status"));
                result.setName(resultSet.getString("name"));
                result.setOutright(resultSet.getBoolean("outright"));
                result.setMapped_id(resultSet.getInt("mapped_id"));
                result.setExternal_id(resultSet.getString("external_id"));

            }


        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            connectionFactory.closePrepareStatement(ps);
        }

        return result;

    }

    public EventModel getLongShotMappedEventsWithFinishedStatusFromDB() {

        final EventModel result = new EventModel();

        PreparedStatement ps = connectionFactory.getPreparedStatement(EventQuerySQL.GET_EVENT_FINISHED_STATUS_LONGSHOT.QUERY);
        try {



            final ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()) {

                result.setSport_id(resultSet.getInt("sport_id"));
                result.setCategory_id(resultSet.getInt("category_id"));
                result.setTournament_id(resultSet.getInt("tournament_id"));
                result.setProvider(resultSet.getString("provider"));
                result.setStatus(resultSet.getString("status"));
                result.setName(resultSet.getString("name"));
                result.setOutright(resultSet.getBoolean("outright"));
                result.setMapped_id(resultSet.getInt("mapped_id"));
                result.setExternal_id(resultSet.getString("external_id"));

            }


        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            connectionFactory.closePrepareStatement(ps);
        }

        return result;

    }


    public EventModel getLongShotUnmappedEventsWithAllStatusFromDB() {

        final EventModel result = new EventModel();

        PreparedStatement ps = connectionFactory.getPreparedStatement(EventQuerySQL.GET_UNMAPPED_EVENT_ALL_STATUS_LONGSHOT.QUERY);
        try {



            final ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()) {

                result.setSport_id(resultSet.getInt("sport_id"));
                result.setCategory_id(resultSet.getInt("category_id"));
                result.setTournament_id(resultSet.getInt("tournament_id"));
                result.setProvider(resultSet.getString("provider"));
                result.setStatus(resultSet.getString("status"));
                result.setName(resultSet.getString("name"));
                result.setOutright(resultSet.getBoolean("outright"));
                result.setMapped_id(resultSet.getInt("mapped_id"));

            }


        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            connectionFactory.closePrepareStatement(ps);
        }

        return result;

    }

    public EventModel getLongShotUnmappedEventsWithNotStartedStatusFromDB() {

        final EventModel result = new EventModel();

        PreparedStatement ps = connectionFactory.getPreparedStatement(EventQuerySQL.GET_UNMAPPED_EVENT_NOT_STARTED_STATUS_LONGSHOT.QUERY);
        try {



            final ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()) {

                result.setSport_id(resultSet.getInt("sport_id"));
                result.setCategory_id(resultSet.getInt("category_id"));
                result.setTournament_id(resultSet.getInt("tournament_id"));
                result.setProvider(resultSet.getString("provider"));
                result.setStatus(resultSet.getString("status"));
                result.setName(resultSet.getString("name"));
                result.setOutright(resultSet.getBoolean("outright"));
                result.setMapped_id(resultSet.getInt("mapped_id"));

            }


        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            connectionFactory.closePrepareStatement(ps);
        }

        return result;

    }

    public EventModel getLongShotUnmappedEventsWithLiveStatusFromDB() {

        final EventModel result = new EventModel();

        PreparedStatement ps = connectionFactory.getPreparedStatement(EventQuerySQL.GET_UNMAPPED_EVENT_LIVE_STATUS_LONGSHOT.QUERY);
        try {



            final ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()) {

                result.setSport_id(resultSet.getInt("sport_id"));
                result.setCategory_id(resultSet.getInt("category_id"));
                result.setTournament_id(resultSet.getInt("tournament_id"));
                result.setProvider(resultSet.getString("provider"));
                result.setStatus(resultSet.getString("status"));
                result.setName(resultSet.getString("name"));
                result.setOutright(resultSet.getBoolean("outright"));
                result.setMapped_id(resultSet.getInt("mapped_id"));
                result.setExternal_id(resultSet.getString("external_id"));

            }


        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            connectionFactory.closePrepareStatement(ps);
        }

        return result;

    }

    public EventModel getLongShotUnmappedEventsWithFinishedStatusFromDB() {

        final EventModel result = new EventModel();

        PreparedStatement ps = connectionFactory.getPreparedStatement(EventQuerySQL.GET_UNMAPPED_EVENT_FINISHED_STATUS_LONGSHOT.QUERY);
        try {



            final ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()) {

                result.setSport_id(resultSet.getInt("sport_id"));
                result.setCategory_id(resultSet.getInt("category_id"));
                result.setTournament_id(resultSet.getInt("tournament_id"));
                result.setProvider(resultSet.getString("provider"));
                result.setStatus(resultSet.getString("status"));
                result.setName(resultSet.getString("name"));
                result.setOutright(resultSet.getBoolean("outright"));
                result.setMapped_id(resultSet.getInt("mapped_id"));

            }


        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            connectionFactory.closePrepareStatement(ps);
        }

        return result;

    }

    public EventModel getLsportMappedEventsWithoutLiveAndNotStartedStatusFromDB() {

        final EventModel result = new EventModel();

        PreparedStatement ps = connectionFactory.getPreparedStatement(EventQuerySQL.GET_EVENT_WITHOUT_LIVE_AND_PREMATCH_STATUS_UNSUSPENDED.QUERY);
        try {



            final ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()) {

                result.setSport_id(resultSet.getInt("sport_id"));
                result.setId(resultSet.getLong("id"));
                result.setTournament_id(resultSet.getInt("tournament_id"));
                result.setProvider(resultSet.getString("provider"));
                result.setStatus(resultSet.getString("status"));
                result.setName(resultSet.getString("name"));
                result.setOutright(resultSet.getBoolean("outright"));
                result.setMapped_id(resultSet.getInt("mapped_id"));
                result.setSuspended(resultSet.getBoolean("suspended"));

            }


        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            connectionFactory.closePrepareStatement(ps);
        }

        return result;

    }

    public EventModel getEventByIdFromDB(Long id) {

        final EventModel result = new EventModel();

        PreparedStatement ps = connectionFactory.getPreparedStatement(EventQuerySQL.GET_EVENT_BY_ID.QUERY);
        try {



            ps.setLong(1, id);
            final ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()) {

                result.setId(resultSet.getLong("id"));
                result.setSuspended(resultSet.getBoolean("suspended"));

            }


        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            connectionFactory.closePrepareStatement(ps);
        }

        return result;

    }



/*    public String getNameEvent() {

        return getEventModelInDB().getName();

    }

    public String getProvider() {

        return getEventModelInDB().getProvider();

    }

    public String getStatus() {

        return getEventModelInDB().getStatus();

    }

    public Integer getSport() {

        return getEventModelInDB().getSport_id();

    }

    public Integer getCategory() {

        return getEventModelInDB().getCategory_id();

    }

    public Integer getTournament() {

        return getEventModelInDB().getTournament_id();

    }

    public Boolean getOutright() {

        return getEventModelInDB().getOutright();

    }

    public Integer getMappedId() {

        return getEventModelInDB().getMapped_id();

    }*/


    enum EventQuerySQL {
        GET_MAPPED_EVENT_ALL_STATUS_LSPORT("SELECT sport_id,category_id, tournament_id,provider,status,name,outright,mapped_id FROM event WHERE mapped_id is not null and provider = 'lsport' order by create_time desc limit (1)"),
        GET_EVENT_NOT_STARTED_STATUS_LSPORT("SELECT sport_id,category_id, tournament_id,provider,status,name,outright,mapped_id,external_id FROM event WHERE mapped_id is not null and status = 'NOT_STARTED' and provider = 'lsport' and outright = false order by create_time desc limit (1)"),
        GET_OUTRIGHT_EVENT_NOT_STARTED_STATUS_LSPORT("SELECT sport_id,category_id, tournament_id,provider,status,name,outright,mapped_id FROM event WHERE mapped_id is not null and status = 'NOT_STARTED' and provider = 'lsport' and outright = true order by create_time desc limit (1)"),
        GET_EVENT_LIVE_STATUS_LSPORT("SELECT sport_id,category_id, tournament_id,provider,status,name,outright,mapped_id, external_id FROM event WHERE mapped_id is not null and status = 'LIVE' and provider = 'lsport' order by create_time desc limit (1)"),
        GET_EVENT_FINISHED_STATUS_LSPORT("SELECT sport_id,category_id, tournament_id,provider,status,name,outright,mapped_id, external_id FROM event WHERE mapped_id is not null and status = 'FINISHED' and provider = 'lsport' and outright = false order by create_time desc limit (1)"),
        GET_OUTRIGHT_EVENT_FINISHED_STATUS_LSPORT("SELECT sport_id,category_id, tournament_id,provider,status,name,outright,mapped_id FROM event WHERE mapped_id is not null and status = 'FINISHED' and provider = 'lsport' and outright = true order by create_time desc limit (1)"),

        GET_UNMAPPED_EVENT_ALL_STATUS_LSPORT("SELECT sport_id,category_id, tournament_id,provider,status,name,outright,mapped_id FROM event WHERE mapped_id is null and provider = 'lsport' order by create_time desc limit (1)"),
        GET_UNMAPPED_EVENT_NOT_STARTED_STATUS_LSPORT("SELECT sport_id,category_id, tournament_id,provider,status,name,outright,mapped_id FROM event WHERE mapped_id is null and status = 'NOT_STARTED' and provider = 'lsport' and outright = false order by create_time desc limit (1)"),
        GET_OUTRIGHT_UNMAPPED_EVENT_NOT_STARTED_STATUS_LSPORT("SELECT sport_id,category_id, tournament_id,provider,status,name,outright,mapped_id FROM event WHERE mapped_id is null and status = 'NOT_STARTED' and provider = 'lsport' and outright = true order by create_time desc limit (1)"),
        GET_UNMAPPED_EVENT_LIVE_STATUS_LSPORT("SELECT sport_id,category_id, tournament_id,provider,status,name,outright,mapped_id FROM event WHERE mapped_id is null and status = 'LIVE' and provider = 'lsport' order by create_time desc limit (1)"),
        GET_UNMAPPED_EVENT_FINISHED_STATUS_LSPORT("SELECT sport_id,category_id, tournament_id,provider,status,name,outright,mapped_id FROM event WHERE mapped_id is null and status = 'FINISHED' and provider = 'lsport' and outright = false order by create_time desc limit (1)"),
        GET_OUTRIGHT_UNMAPPED_EVENT_FINISHED_STATUS_LSPORT("SELECT sport_id,category_id, tournament_id,provider,status,name,outright,mapped_id FROM event WHERE mapped_id is null and status = 'FINISHED' and provider = 'lsport' and outright = true order by create_time desc limit (1)"),

        GET_EVENT_ALL_STATUS_LONGSHOT("SELECT sport_id,category_id, tournament_id,provider,status,name,outright,mapped_id FROM event WHERE mapped_id is not null and provider = 'longshot' order by create_time desc limit (1)"),
        GET_EVENT_NOT_STARTED_STATUS_LONGSHOT("SELECT sport_id,category_id, tournament_id,provider,status,name,outright,mapped_id, external_id FROM event WHERE mapped_id is not null and status = 'NOT_STARTED' and provider = 'longshot' order by create_time desc limit (1)"),
        GET_EVENT_LIVE_STATUS_LONGSHOT("SELECT sport_id,category_id, tournament_id,provider,status,name,outright,mapped_id, external_id FROM event WHERE mapped_id is not null and status = 'LIVE' and provider = 'longshot' order by create_time desc limit (1)"),
        GET_EVENT_FINISHED_STATUS_LONGSHOT("SELECT sport_id,category_id, tournament_id,provider,status,name,outright,mapped_id, external_id FROM event WHERE mapped_id is not null and status = 'FINISHED' and provider = 'longshot' order by create_time desc limit (1)"),

        GET_UNMAPPED_EVENT_ALL_STATUS_LONGSHOT("SELECT sport_id,category_id, tournament_id,provider,status,name,outright,mapped_id FROM event WHERE mapped_id is null and provider = 'longshot' order by create_time desc limit (1)"),
        GET_UNMAPPED_EVENT_NOT_STARTED_STATUS_LONGSHOT("SELECT sport_id,category_id, tournament_id,provider,status,name,outright,mapped_id FROM event WHERE mapped_id is null and status = 'NOT_STARTED' and provider = 'longshot' order by create_time desc limit (1)"),
        GET_UNMAPPED_EVENT_LIVE_STATUS_LONGSHOT("SELECT sport_id,category_id, tournament_id,provider,status,name,outright,mapped_id, external_id FROM event WHERE mapped_id is null and status = 'LIVE' and provider = 'longshot' order by create_time desc limit (1)"),
        GET_UNMAPPED_EVENT_FINISHED_STATUS_LONGSHOT("SELECT sport_id,category_id, tournament_id,provider,status,name,outright,mapped_id FROM event WHERE mapped_id is null and status = 'FINISHED' and provider = 'longshot' order by create_time desc limit (1)"),

        GET_EVENT_WITHOUT_LIVE_AND_PREMATCH_STATUS_UNSUSPENDED("SELECT sport_id,category_id, tournament_id,provider,status,name,outright,mapped_id, id, suspended FROM event WHERE  mapped_id is not  null and provider = 'lsport' and suspended is null and status not in ('LIVE','NOT_STARTED') limit (1)"),
        GET_EVENT_BY_ID("SELECT id, suspended FROM event WHERE id = (?)");

        String QUERY;

        EventQuerySQL(String QUERY) {
            this.QUERY = QUERY;
        }
    }

}
